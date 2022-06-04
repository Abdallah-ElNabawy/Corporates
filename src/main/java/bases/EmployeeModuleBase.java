package bases;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import constants.CreateAdminExcelIndicies;
import constants.CreateEmployeeIndicies;
import data.models.AdminCreationDM;
import data.models.EmployeeCreationDM;
import stragies.TestDataStrategy;

public class EmployeeModuleBase {

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

		eReport = new ExtentReports("reports/Employee Module Summary Report  "+dateString+".html");

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
	public void reportStartTest(Method method) {
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
	
	
	
	
	@Parameters({"browserType","makerLoginScreenURL"})
	@BeforeClass
	public void openWebsite(String browserType,String makerLoginScreenURL ) throws Exception
	{	
		
			switch (browserType) 
			{
				case ("Chrome"):
					System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			        wDriver=new ChromeDriver();
				    wDriver.manage().window().maximize();
				    wDriver.get(makerLoginScreenURL);	   
					break;
					
				case ("Firefox"):
					System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		            wDriver=new FirefoxDriver();
			        wDriver.manage().window().maximize();
			        wDriver.get(makerLoginScreenURL);			      
			        break;
			}
	}
	
	
	///---------------------------Data Providers-----------------------
	
	
	
	// login to corporate by maker
	@DataProvider(name = "MakerLoginTDProvider")
	public Object[][] provideTestDataForMakerLogin(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("loginByMakeOrCheckerExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
			if(resultArray.get(i).get(CreateAdminExcelIndicies.Role).toString().equals("Maker"))
			{		
				AdminCreationDM adminCreationDM = new AdminCreationDM();
			
				adminCreationDM.setAdminEmail(resultArray.get(i).get(CreateAdminExcelIndicies.Admin_Email).toString());
				adminCreationDM.setPassword(resultArray.get(i).get(CreateAdminExcelIndicies.Password).toString());
				adminCreationDM.setCorporateName(resultArray.get(i).get(CreateAdminExcelIndicies.Corporate_Name).toString());
				
			    result[i][0] = adminCreationDM;
			}
		}
		return result;	
    }

	
	
	// login to corporate by Checker
	@DataProvider(name = "CheckerLoginTDProvider")
	public Object[][] provideTestDataForCheckerLogin(Method method) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String filePath= prop.getProperty("loginByMakeOrCheckerExcel");
		testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

		ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
		Object[][] result = new Object[resultArray.size()][1];
		
		for(int i=0; i<resultArray.size(); i++)
		{
			if(resultArray.get(i).get(CreateAdminExcelIndicies.Role).toString().equals("Checker"))
			{		
				AdminCreationDM adminCreationDM = new AdminCreationDM();
			
				adminCreationDM.setAdminEmail(resultArray.get(i).get(CreateAdminExcelIndicies.Admin_Email).toString());
				adminCreationDM.setPassword(resultArray.get(i).get(CreateAdminExcelIndicies.Password).toString());
				adminCreationDM.setCorporateName(resultArray.get(i).get(CreateAdminExcelIndicies.Corporate_Name).toString());
						
			    result[i][0] = adminCreationDM;
			}
		}
		return result;	
    }

	
	// Create Employee
		@DataProvider(name = "CreateEmployeeTDProvider")
		public Object[][] provideTestDataForCreateEmployee(Method method) throws Exception
		{
			filePath = new File("src/main/resources/config.properties");
			inputStream = new FileInputStream(filePath);
			prop.load(inputStream);
			String filePath= prop.getProperty("CreateEmployeeExcel");
			testDataStragy = (TestDataStrategy) Class.forName("stragies.ExcelData").newInstance();

			ArrayList<ArrayList<Object>> resultArray = testDataStragy.loadTestData(filePath);
			Object[][] result = new Object[resultArray.size()][1];
			
			for(int i=0; i<resultArray.size(); i++)
			{						
					EmployeeCreationDM employeeCreationDM = new EmployeeCreationDM();
				
					employeeCreationDM.setFirstName(resultArray.get(i).get(CreateEmployeeIndicies.First_Name).toString());
					employeeCreationDM.setLastName(resultArray.get(i).get(CreateEmployeeIndicies.Last_Name).toString());
					employeeCreationDM.setMobileNumber(resultArray.get(i).get(CreateEmployeeIndicies.Phone_Number).toString());			
					employeeCreationDM.setEmail(resultArray.get(i).get(CreateEmployeeIndicies.Email).toString());
					employeeCreationDM.setGender(resultArray.get(i).get(CreateEmployeeIndicies.Gender).toString());
					employeeCreationDM.setBirthDate(resultArray.get(i).get(CreateEmployeeIndicies.Birth_Date).toString());
					employeeCreationDM.setNAT(resultArray.get(i).get(CreateEmployeeIndicies.NAT).toString());
					employeeCreationDM.setExpirationDate(resultArray.get(i).get(CreateEmployeeIndicies.Expiration_Date).toString());
					employeeCreationDM.setForntID(resultArray.get(i).get(CreateEmployeeIndicies.Front_ID).toString());
					employeeCreationDM.setBackendID(resultArray.get(i).get(CreateEmployeeIndicies.Backend_ID).toString());
							
				    result[i][0] = employeeCreationDM;
			}
			return result;	
	    }
	
}
