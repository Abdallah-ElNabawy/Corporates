package employeeModuleTestCases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import bases.EmployeeModuleBase;
import data.models.AdminCreationDM;
import data.models.CorporateCreationDM;
import data.models.EmployeeCreationDM;
import data.models.PoolCreationDM;
import data.models.VoucherPoolCreationDM;
import pages.*;


public class EmployeeCreation extends EmployeeModuleBase {
	
	
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
	
	
	//----------Validate that can search about created employee request in account requests screen
	
	@Test(dataProvider="CreateEmployeeTDProvider", priority = 2)
	public void createEmployeeRequestByMaker(EmployeeCreationDM employeeCreationDM) throws Exception
	{
		String mobile = employeeCreationDM.getMobileNumber();
		
		AccountsMorC accountMoC = new AccountsMorC(wDriver);			
		accountMoC.openAccountsRequestsScreen();
		
		AccountsRequestsMorC accountRequests = new AccountsRequestsMorC(wDriver);		
		accountRequests.clickOnPlusIcon();
		
		AccountRequestDetailsMorC accountRequestDetails = new AccountRequestDetailsMorC(wDriver);	
		accountRequestDetails.insertEmployeeData(employeeCreationDM);
		accountRequestDetails.clickOnCreatebuttonn();
		
		accountRequests = new AccountsRequestsMorC(wDriver);
		accountRequests.searchByMobile(employeeCreationDM.getMobileNumber());
		
		if(accountRequests.nameFirstCellValue().contains(employeeCreationDM.getFirstName())
			&& accountRequests.nameFirstCellValue().contains(employeeCreationDM.getLastName())
			&& employeeCreationDM.getMobileNumber().equals(accountRequests.mobileFirstCellValue())
			&& employeeCreationDM.getNAT().equals(accountRequests.natFirstCellValue())
			&& accountRequests.statusFirstCellValue().contains("Pending"))
		{
			eTest.log(LogStatus.PASS,"Employee's Mobile: " + mobile);
			eTest.log(LogStatus.PASS, "Employee Creation Request for "+mobile+"Was Created Successfully");
			addScreenshots("Employees Requests List After Search about "+mobile+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Employee's Mobile: " + mobile);
			eTest.log(LogStatus.FAIL, "Employee Creation Request for "+mobile+"Wasn't Created Successfully");
			addScreenshots("Employees Requests List After Search about "+ mobile,"Fail");
		}	
		
		accountRequests.logout();
	}
	


	
	@Test(dataProvider="CheckerLoginTDProvider", priority = 3 )
	public void checkerLogin(AdminCreationDM adminCreationDM) throws Exception
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
	
	
	//----------------Validate that can accept pending employee creation request by checker
	//----------------Validate that creation confirmation message be shown and request status was changed to checker accepted
	//----------------Validate that new accepted employee account be shown in accounts screen
	
	@Test(dataProvider="CreateEmployeeTDProvider", priority = 4)
	public void acceptEmployeeRequestByChecker(EmployeeCreationDM employeeCreationDM) throws Exception
	{
		String mobile = employeeCreationDM.getMobileNumber();
		
		AccountsMorC accountsMoC = new AccountsMorC(wDriver);		
		accountsMoC.openAccountsRequestsScreen();
		
		
		AccountsRequestsMorC accountRequests = new AccountsRequestsMorC(wDriver);
		accountRequests.searchByMobile(employeeCreationDM.getMobileNumber());
		accountRequests.clickOnFirstRow();
		
		AccountRequestDetailsMorC accountRequestDetails = new AccountRequestDetailsMorC(wDriver);
		accountRequestDetails.acceptPendingRequest();
		
		accountRequests = new AccountsRequestsMorC(wDriver);

		
		if(accountRequests.creationConfirmationMessageValue().equals("The employee has been created successfully"))
		{
			eTest.log(LogStatus.PASS,"Employee's Mobile: " + mobile);
			eTest.log(LogStatus.PASS, "Creation Confirmation Message for "+mobile+"Was Shown successfully");
			addScreenshots("Creation Confirmation Message of "+mobile+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Employee's Mobile: " + mobile);
			eTest.log(LogStatus.FAIL, "Creation Confirmation Message for "+mobile+"Wasn't Shown");
			addScreenshots("Creation Confirmation Message "+ mobile,"Fail");
		}	
		
		accountRequests.searchByMobile(employeeCreationDM.getMobileNumber());
		
		if(accountRequests.nameFirstCellValue().contains(employeeCreationDM.getFirstName())
				&& accountRequests.nameFirstCellValue().contains(employeeCreationDM.getLastName())
				&& employeeCreationDM.getMobileNumber().equals(accountRequests.mobileFirstCellValue())
				&& employeeCreationDM.getNAT().equals(accountRequests.natFirstCellValue())
				&& accountRequests.statusFirstCellValue().contains("Checker Accepted"))
		{
			eTest.log(LogStatus.PASS,"Employee's Mobile: " + mobile);
			eTest.log(LogStatus.PASS, "Employee Creation Request for "+mobile+"Was Accepted Successfully");
			addScreenshots("Employees Requests List After Search about "+mobile+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Employee's Mobile: " + mobile);
			eTest.log(LogStatus.FAIL, "Employee Creation Request for "+mobile+"Wasn't Accepted Successfully");
			addScreenshots("Employees Requests List After Search about "+ mobile,"Fail");
		}	
		
		accountRequests.openAccountsScreen();
		
	    accountsMoC = new AccountsMorC(wDriver);		
		accountsMoC.searchByMobile(employeeCreationDM);
		
		if(employeeCreationDM.getFirstName().equals(accountsMoC.firstnameFirstCellValue())
				&& employeeCreationDM.getLastName().equals(accountsMoC.lastnameFirstCellValue())
				&& employeeCreationDM.getMobileNumber().equals(accountsMoC.mobileFirstCellValue())
				&& employeeCreationDM.getNAT().equals(accountsMoC.natFirstCellValue())
				&& employeeCreationDM.getEmail().equals(accountsMoC.emailFirstCellValue()))
		{
			eTest.log(LogStatus.PASS,"Employee's Mobile: " + mobile);
			eTest.log(LogStatus.PASS, "Account of "+mobile+"Was Created Successfully");
			addScreenshots("Accounts List After Search about "+mobile+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Employee's Mobile: " + mobile);
			eTest.log(LogStatus.FAIL, "Account of "+mobile+"Wasn't shown in Accounts list");
			addScreenshots("Accounts List After Search about "+ mobile,"Fail");
		}	
		
		accountsMoC.logout();
	}
	
	
	
	
	
	@Test(dataProvider="MakerLoginTDProvider", priority = 5)
	public void makerLogin2(AdminCreationDM adminCreationDM) throws Exception
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
	
	
	//----------Validate That Employee Batch Was Uploaded Successfully
	//----------Validate Employee Creation Request Was Created Successfully When Use Batch
	
	@Test(priority = 6)
	public void uploadEmployeesBatchByMaker() throws Exception
	{	
		AccountsMorC accountMoC = new AccountsMorC(wDriver);			
		accountMoC.openAccountsUploadBatchScreen();
		
		AccountUploadBatchMorC accountUploadBatchScreen = new AccountUploadBatchMorC(wDriver);
		accountUploadBatchScreen.uploadEmployeeslSheet("Template.csv");
		accountUploadBatchScreen.uploadFrontandBackID("Lemby.png", "Lemby.png");
		accountUploadBatchScreen.clickOnSubmitButton();
		String mobile = accountUploadBatchScreen.mobileFirstCellValue();
		accountUploadBatchScreen.scrollIntoStatusCell();
		
		if(accountUploadBatchScreen.statusFirstCellValue().contains("SUCCESS"))
		{
			eTest.log(LogStatus.PASS, "Employee Batch Was Uploaded Successfully");
			addScreenshots("Status of Upload Payroll Batch","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Upload Employees Batch Was Failed");
			addScreenshots("Status of Upload Payroll Batch ","Fail");
		}
		
		
		
		accountUploadBatchScreen.openAccountsRequestsScreen();
		AccountsRequestsMorC accountRequests = new AccountsRequestsMorC(wDriver);
		accountRequests.searchByMobile(mobile);
		
		if(accountRequests.mobileFirstCellValue().contains(mobile))
		{
			eTest.log(LogStatus.PASS,"Employee's Mobile: " + mobile);
			eTest.log(LogStatus.PASS, "Employee Creation Request for "+mobile+"Was Created Successfully When Use Batch");
			addScreenshots("Employees Requests List After Search about "+mobile+" ","Success");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Employee's Mobile: " + mobile);
			eTest.log(LogStatus.FAIL, "Employee Creation Request for "+mobile+"Was Failed When Use Batch");
			addScreenshots("Employees Requests List After Search about "+ mobile,"Fail");
		}		
			
		accountRequests.logout();
	}
	
	
		

}
