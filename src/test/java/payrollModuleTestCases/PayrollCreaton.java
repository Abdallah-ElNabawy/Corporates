package payrollModuleTestCases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import data.models.AdminCreationDM;
import pages.*;
import bases.PayrollModuleBase;

public class PayrollCreaton extends PayrollModuleBase {
	
	
		
	@Test(dataProvider="MakerLoginTDProvider", priority = 1)
	public void makerLogin(AdminCreationDM adminCreationDM) throws Exception
	{
		LoginMorC loginPage = new LoginMorC(wDriver);			
		loginPage.loginByMakerorChecker(adminCreationDM);	
		
		AccountsMorC accountMoC = new AccountsMorC(wDriver);
		
	      if(accountMoC.validateProfileDroplistDisplayed())
	 	  {
	 		  eTest.log(LogStatus.PASS,"Maker Admin Has Logged Successfully");
	 		  addScreenshots("Maker Admin Has Logged Successfully","Success");
	 	  }
	 	  else
	 	  { 
	 		  eTest.log(LogStatus.FAIL,"Maker Admin Failed in Login");
	 		  addScreenshots("Maker Admin Failed in Login","Fail"); 
	 	  }
	}
	
	
	
	//------Validate that payroll request was created by maker and can search about it
	
	@Test(priority = 2)
	public void uploadPayrollBatchByMaker() throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream); 
		String sheetName= prop.getProperty("payrollSheetName");
		
		AccountsMorC accountsScreen =  new AccountsMorC(wDriver);
		accountsScreen.openPayrollUploadBatchScreen();
		
		PayrollUploadBatchMorC payUploadBatchScreen = new PayrollUploadBatchMorC(wDriver);
		payUploadBatchScreen.uploadPayrollSheet("Payroll-Template.csv");
		
		payUploadBatchScreen.initializeWebDriver(wDriver);
		payUploadBatchScreen.insertSheetName(sheetName);
		payUploadBatchScreen.selectPayoutOption("Salary Wallet");
		payUploadBatchScreen.clickonSubmitButton();		
		payUploadBatchScreen.openPayrollRequestsScreen();
		
		PayrollRequestsMorC payrollRequestsScreen = new PayrollRequestsMorC(wDriver);
		payrollRequestsScreen.searchBySheetName(sheetName);
		
		if(payrollRequestsScreen.sheetNameFirstCellValue().equals(sheetName)
				&&payrollRequestsScreen.statusFirstCellValue().contains("Pending"))
		{
			eTest.log(LogStatus.PASS,"Payroll Sheet: " + sheetName);
			eTest.log(LogStatus.PASS, "Payroll Request Was Created Successfully");
			addScreenshots("Payroll Requests List after Search about "+sheetName+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Payroll Sheet: " + sheetName);
			eTest.log(LogStatus.FAIL, "Payroll Request Wasn't Created");
			addScreenshots("Payroll Requests List after Search about "+sheetName+" ","Fail");
		}	
		
		payrollRequestsScreen.logout();
	}
	
	

	@Test(dataProvider="CheckerLoginTDProvider", priority = 3)
	public void CheckerLogin(AdminCreationDM adminCreationDM) throws Exception
	{
		LoginMorC loginPage = new LoginMorC(wDriver);			
		loginPage.loginByMakerorChecker(adminCreationDM);	
		
		AccountsMorC accountMoC = new AccountsMorC(wDriver);
		
	      if(accountMoC.validateProfileDroplistDisplayed())
	 	  {
	 		  eTest.log(LogStatus.PASS,"Checker Admin Has Logged Successfully");
	 		  addScreenshots("checker Admin Has Logged Successfully","Success");
	 	  }
	 	  else
	 	  { 
	 		  eTest.log(LogStatus.FAIL,"Checker Admin Failed in Login");
	 		  addScreenshots("Checker Admin Failed in Login","Fail"); 
	 	  }
	}
	
	
	//-----Validate that accepted request by checker be shown in history screen and can search about it
	
	@Test(dataProvider="RetrieveTokenOTP",priority = 4)
	public void aceptPayrollBatchByChecker(String OTP) throws Exception
	{
		filePath = new File("src/main/resources/config.properties");
		inputStream = new FileInputStream(filePath);
		prop.load(inputStream); 
		String sheetName= prop.getProperty("payrollSheetName");
		
		AccountsMorC accountsScreen =  new AccountsMorC(wDriver);
		accountsScreen.openPayrollRequestsScreen();
		
		PayrollRequestsMorC payrollRequestsScreen = new PayrollRequestsMorC(wDriver);
		payrollRequestsScreen.searchBySheetName(sheetName);
		payrollRequestsScreen.clickonFirstRow();
		payrollRequestsScreen.initializeWebDriver(wDriver);
		payrollRequestsScreen.acceptPendingRequest(OTP);
		payrollRequestsScreen.initializeWebDriver(wDriver);
		payrollRequestsScreen.openPayrollHistoryScreen();
		
		HistoryMorC historyScreen = new HistoryMorC(wDriver);
		historyScreen.searchBySheetName(sheetName);
		
		if(historyScreen.sheetNameFirstCellValue().equals(sheetName))
		{
			eTest.log(LogStatus.PASS,"Payroll Sheet: " + sheetName);
			eTest.log(LogStatus.PASS, "Payroll Request Was Accepted Successfully");
			addScreenshots("Payroll History List after Search about "+sheetName+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Payroll Sheet: " + sheetName);
			eTest.log(LogStatus.FAIL, "Payroll Request Wasn't Accepted");
			addScreenshots("Payroll History List after Search about "+sheetName+" ","Fail");
		}
		
		historyScreen.logout();
	}
	
	
}
