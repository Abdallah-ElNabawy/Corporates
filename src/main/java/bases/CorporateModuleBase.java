package bases;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import data.models.*;
import constants.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.TakesScreenshot;

import stragies.TestDataStrategy;
import stragies.ExcelData;
import constants.LoginSuperExcelIndicies;


public class CorporateModuleBase {

	protected Properties prop = new Properties();
	protected FileInputStream inputStream = null;
	protected File filePath = null;
	protected WebDriver wDriver;
	protected TestDataStrategy testDataStragy;
	protected static ExtentReports eReport;
	protected static ExtentTest eTest;

	
	@BeforeSuite
	public void prepareReport() throws Exception
	{
		Date date = new Date();
		String dateString = String.format("%tc", date );
		dateString= dateString.replaceAll(":", "-");
		dateString= dateString.replaceAll(" ", "-");
		
		inputStream = new FileInputStream("src/main/Resources/config.properties");
		prop.load(inputStream);

		eReport = new ExtentReports("reports/Corporate Module Summary Report  "+dateString+".html");

		eReport.addSystemInfo("Application Name : ", prop.getProperty("aplicationName"));
		eReport.addSystemInfo("Executer : ", prop.getProperty("softwateTester"));		
	}
	
	@AfterSuite
	public void closeReport()
	{	
		eReport.flush();
		eReport.close();
	}
	
	
	
	@BeforeMethod
	public void reportStartTest(Method method)
	{
		eTest = eReport.startTest(method.getName());
	}

	@AfterMethod
	public void reportEndTest(Method method)
	{
		eReport.endTest(eTest);
	}
	
	//@AfterMethod
	public void addScreenshots(Method method, ITestResult iTestResult) throws Exception {
	
		Date date = new Date();
		String dateString = String.format("%tc", date );
		dateString= dateString.replaceAll(":", "-");
		dateString= dateString.replaceAll(" ", "-");
							
		File sShots = ((TakesScreenshot)wDriver).getScreenshotAs(OutputType.FILE);	
		FileUtils.copyFile(sShots, new File("screenshots/" + method.getName() + dateString + ".jpg"));
		
		String fullScreenPath = "C:\\Users\\"+System.getProperty("user.name").toString()+"\\eclipse-workspace\\PayrollCorporate\\"+ "screenshots\\" + method.getName() + dateString + ".jpg";
		
		
		if (iTestResult.getStatus() == iTestResult.SUCCESS) 
		{
			eTest.log(LogStatus.PASS, eTest.addScreenCapture(fullScreenPath));	
		} 
		
		else if (iTestResult.getStatus() == iTestResult.FAILURE)
		{			
			eTest.log(LogStatus.FAIL, iTestResult.getThrowable());
			eTest.log(LogStatus.FAIL, eTest.addScreenCapture(fullScreenPath));
		}
		
		else if (iTestResult.getStatus() == iTestResult.SKIP) 
		{
			eTest.log(LogStatus.SKIP, iTestResult.getThrowable());
			eTest.log(LogStatus.SKIP, eTest.addScreenCapture(fullScreenPath));
		}
		eReport.endTest(eTest);
	}
	
	
	public void addScreenshots(String name, String Status) throws Exception 
	{
		
		Date date = new Date();
		String dateString = String.format("%tc", date );
		dateString= dateString.replaceAll(":", "-");
		dateString= dateString.replaceAll(" ", "-");
							
		File sShots = ((TakesScreenshot)wDriver).getScreenshotAs(OutputType.FILE);	
		FileUtils.copyFile(sShots, new File("screenshots/" + name + dateString + ".jpg"));
		
		String fullScreenPath = "C:\\Users\\"+System.getProperty("user.name").toString()+"\\eclipse-workspace\\PayrollCorporate\\"+ "screenshots\\" + name + dateString + ".jpg";
		
		
		if (Status.equals("Success")) 
		{
			eTest.log(LogStatus.PASS, eTest.addScreenCapture(fullScreenPath));	
		} 
		
		else
		{	
			eTest.log(LogStatus.FAIL, eTest.addScreenCapture(fullScreenPath));	
		} 				
	}
	
	
	
	
	@Parameters({"browserType","superLoginScreenURL"})
	@BeforeClass
	public void openWebsite(String browserType,String superLoginScreenURL ) throws Exception
	{	
		
			switch (browserType) 
			{
				case ("Chrome"):
					System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			        wDriver=new ChromeDriver();
				    wDriver.manage().window().maximize();
				    wDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				    wDriver.get(superLoginScreenURL);				   
					break;
					
				case ("Firefox"):
					System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		            wDriver=new FirefoxDriver();
			        wDriver.manage().window().maximize();
			        wDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			        wDriver.get(superLoginScreenURL);			      
			        break;
			}
	}
	
	
	///---------------------------Data Providers-----------------------
	
	
	
	// login to corporate by super admin
	@DataProvider(name = "superadminLoginTDProvider")
	public Object[][] provideTestDataForSuperadminLogin(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("loginBySuperExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
		
			LoginSuperDM loginSuperDM = new LoginSuperDM();
			
			loginSuperDM.setUserMail(resultArray.get(i).get(LoginSuperExcelIndicies.USER_MAIL_Corporate).toString());
			loginSuperDM.setPassword(resultArray.get(i).get(LoginSuperExcelIndicies.USER_Password_Corporate).toString());
						
			result[i][0] = loginSuperDM;		
		}
		return result;	
    }
	
	
	// Create new Corporate
	@DataProvider(name = "CreateCorporateTDProvider")
	public Object[][] provideTestDataCreateCorporate(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("CreationCorporateExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
		
		    CorporateCreationDM corporateCreationDM = new CorporateCreationDM();
			
		    corporateCreationDM.setCorporateNamel(resultArray.get(i).get(CreateCorporateExcelIndicies.Corporate_Name).toString());
		    corporateCreationDM.setLogoPath(resultArray.get(i).get(CreateCorporateExcelIndicies.Logo_Path).toString());
		    corporateCreationDM.setCountry(resultArray.get(i).get(CreateCorporateExcelIndicies.Country).toString());
		    corporateCreationDM.setCode(resultArray.get(i).get(CreateCorporateExcelIndicies.Code).toString());
		    corporateCreationDM.setAddress(resultArray.get(i).get(CreateCorporateExcelIndicies.Address).toString());
		    corporateCreationDM.setDescription(resultArray.get(i).get(CreateCorporateExcelIndicies.Description).toString());
		    corporateCreationDM.setCRN(resultArray.get(i).get(CreateCorporateExcelIndicies.CRN).toString());
		    corporateCreationDM.setGOV(resultArray.get(i).get(CreateCorporateExcelIndicies.GOV).toString());
		    corporateCreationDM.setNAT(resultArray.get(i).get(CreateCorporateExcelIndicies.NAT).toString());
		    corporateCreationDM.setContactName(resultArray.get(i).get(CreateCorporateExcelIndicies.Contact_Name).toString());
		    corporateCreationDM.setContactMail(resultArray.get(i).get(CreateCorporateExcelIndicies.Contact_Mail).toString());
		    corporateCreationDM.setContactMobile(resultArray.get(i).get(CreateCorporateExcelIndicies.Contact_Mobile).toString());
		    corporateCreationDM.setGWConfiguration(resultArray.get(i).get(CreateCorporateExcelIndicies.GW_Configuration).toString());
		    corporateCreationDM.setOfficialID(resultArray.get(i).get(CreateCorporateExcelIndicies.Officail_ID).toString());
		    
			result[i][0] = corporateCreationDM;		
		}
		return result;	
    }
	
	
	
	// Create new Pool Account)
	@DataProvider(name = "CreatePoolAccountTDProvider")
	public Object[][] provideTestDataCreatePool(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("CreationPoolExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
		
			PoolCreationDM poolCreationDM = new PoolCreationDM();
			
			poolCreationDM.setAccountNumber(resultArray.get(i).get(CreatePoolExcelIndicies.Account_Number).toString());
			poolCreationDM.setCategory(resultArray.get(i).get(CreatePoolExcelIndicies.Category).toString());
			poolCreationDM.setThreholdAmountr(resultArray.get(i).get(CreatePoolExcelIndicies.Threshold_Amount).toString());
			poolCreationDM.setCeiling(resultArray.get(i).get(CreatePoolExcelIndicies.Ceiling).toString());
			poolCreationDM.setNotificationSMS(resultArray.get(i).get(CreatePoolExcelIndicies.Notification_SMS).toString());
			poolCreationDM.setNotificationMail(resultArray.get(i).get(CreatePoolExcelIndicies.Notification_Mail).toString());
			poolCreationDM.setCorporateName(resultArray.get(i).get(CreatePoolExcelIndicies.Corporate_Name).toString());
			poolCreationDM.setDailyCreditLimit(resultArray.get(i).get(CreatePoolExcelIndicies.Daily_Credit_Limit).toString());
			poolCreationDM.setDailyDebitLimit(resultArray.get(i).get(CreatePoolExcelIndicies.Daily_Debit_Limit).toString());
			
			result[i][0] = poolCreationDM;		
		}
		return result;	
    }

	
	
	
	// Create new Voucher Pool Account
	@DataProvider(name = "CreateVoucherPoolAccountTDProvider")
	public Object[][] provideTestDataCreateVoucherPool(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("CreationVoucherPoolExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
		
			VoucherPoolCreationDM voucherPoolCreationDM = new VoucherPoolCreationDM();
			
			voucherPoolCreationDM.setVoucherAccountNumber(resultArray.get(i).get(CreateVoucherPoolExcelIndicies.Voucher_Account_Number).toString());
			voucherPoolCreationDM.setExpirationDays(resultArray.get(i).get(CreateVoucherPoolExcelIndicies.Expiration_days).toString());
			voucherPoolCreationDM.setCorporateName(resultArray.get(i).get(CreateVoucherPoolExcelIndicies.Corporate_Name).toString());
			voucherPoolCreationDM.setRequiredValue(resultArray.get(i).get(CreateVoucherPoolExcelIndicies.Required).toString());
						
			result[i][0] = voucherPoolCreationDM;		
		}
		return result;	
    }
	
	
	// Create New Admin 
	@DataProvider(name = "CreateAdminTDProvider")
	public Object[][] provideTestDataCreateAdmin(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("CreationAdminExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
		
			AdminCreationDM adminCreationDM = new AdminCreationDM();
			
			adminCreationDM.setAdminName(resultArray.get(i).get(CreateAdminExcelIndicies.Admin_Name).toString());
			adminCreationDM.setAdminEmail(resultArray.get(i).get(CreateAdminExcelIndicies.Admin_Email).toString());
			adminCreationDM.setPhoneNumber(resultArray.get(i).get(CreateAdminExcelIndicies.Phone_Number).toString());
			adminCreationDM.setAdminRole(resultArray.get(i).get(CreateAdminExcelIndicies.Role).toString());
			adminCreationDM.setCorporateName(resultArray.get(i).get(CreateAdminExcelIndicies.Corporate_Name).toString());		
			adminCreationDM.setPassword(resultArray.get(i).get(CreateAdminExcelIndicies.Password).toString());
			adminCreationDM.setCorporateCode(resultArray.get(i).get(CreateAdminExcelIndicies.Corporate_Code).toString());
			
			result[i][0] = adminCreationDM;		
		}
		return result;	
    }
	
	

	
}
