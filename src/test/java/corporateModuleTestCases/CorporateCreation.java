package corporateModuleTestCases;


import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import bases.CorporateModuleBase;
import data.models.AdminCreationDM;
import data.models.CorporateCreationDM;
import data.models.LoginSuperDM;
import data.models.PoolCreationDM;
import data.models.VoucherPoolCreationDM;
import pages.*;

public class CorporateCreation extends CorporateModuleBase {
	
	
	

	
	  @Test(dataProvider="superadminLoginTDProvider", priority = 1) 
	  public void superadminLogin(LoginSuperDM loginSuperDM) throws Exception 
	  { 
		  LoginSuper loginPage = new LoginSuper(wDriver);	
	      loginPage.loginBySuperAdmin(loginSuperDM);
	      
	      HomeSuper homePage = new HomeSuper(wDriver);
	      
	      if(homePage.validateMainLogoDisplayed())
	 	  {
	 		  eTest.log(LogStatus.PASS,"Super Admin Has Logged Successfully");
	 		  addScreenshots("Super Admin Has Logged Successfully","Success");
	 	  }
	 	  else
	 	  { 
	 		  eTest.log(LogStatus.FAIL,"Super Admin Failed in Login");
	 		  addScreenshots("Super Admin Failed in Login","Fail"); 
	 	  }
	  }
	  
	  
	  //----------Validate from creation confirmation message of SME
	  //----------validate that SME be shown in corporate list in home screen
	  
	  @Test(dataProvider = "CreateCorporateTDProvider", priority = 2) 
	  public void createCorporateBySuperAdmin(CorporateCreationDM corporateCreationDM) throws Exception {
	  String corName= corporateCreationDM.getCorporateName();
	  
	  HomeSuper homePage = new HomeSuper(wDriver);
		
		  homePage.clickonPlusButton();
		  
		  CorporateBasicInfoSuper corBasicInfo = new CorporateBasicInfoSuper(wDriver);
		  corBasicInfo.insertCorporateData(corporateCreationDM);
		  corBasicInfo.clickonSaveButton();
		  
		  homePage = new HomeSuper(wDriver);
		  
		  if(homePage.contentofCreationConfirmationMesssage().contains("SME Created Successfully")) 
		  {
		  eTest.log(LogStatus.PASS,"Corporate Code: " + corName);
		  eTest.log(LogStatus.PASS,"Successful Corporate Creation Message Was Shown ");
		  addScreenshots("Successful Creation Message of "+corName+" ","Success");
		  }
		  else
		  { 
		  eTest.log(LogStatus.FAIL, "Corporate Code: " + corName);
		  eTest.log(LogStatus.FAIL,"Successful Corporate Creation Message Wasn't Shown");
		  addScreenshots("No Successful Creation Message of "+corName+" ","Fail"); 
		  }
		 
	  
	  homePage.useSearchBox(corporateCreationDM.getCorporateName());
	  
	  if(homePage.firstCodeCellValue().contains(corporateCreationDM.getCode()) )
	  {
	  eTest.log(LogStatus.PASS,"Corporate Code: " + corName);
	  eTest.log(LogStatus.PASS, "Created Corporate Be Shown in Corporates list");
	  addScreenshots("Corporate List After Search about "+corName+" ","Success");
	  }
	  else
	  { 
	  eTest.log(LogStatus.FAIL, "Corporate Code: " + corName);
	  eTest.log(LogStatus.FAIL,"Created Corporate Wasn't shown in Corporates List");
	  addScreenshots("Corporate List After Search about "+corName+" ","Fail"); } 
	  }
	  
	  
	  
	  
	  //----------validate that can search about specific SME
	  
	  @Test(dataProvider = "CreateCorporateTDProvider", priority = 3) 
	  public void searchAboutCorporateBySuperAdmin(CorporateCreationDM corporateCreationDM) throws Exception
	  { 
		  String corName= corporateCreationDM.getCorporateName();
	  
	  HomeSuper homePage = new HomeSuper(wDriver);
	  homePage.useSearchBox(corporateCreationDM.getCorporateName());
	  
	  if(homePage.firstCodeCellValue().contains(corporateCreationDM.getCode())
	  &&homePage.firstNameCellValue().contains(corporateCreationDM.getCorporateName()))
      {
		  eTest.log(LogStatus.PASS,"Corporate Code: " + corName);
	  eTest.log(LogStatus.PASS, "Filtration About Created Corporate Working Fine");
	  addScreenshots("Corporate List After Search about "+corName+" ","Success"); 
	  }
	  else 
	  { eTest.log(LogStatus.FAIL, "Corporate Code: " + corName);
	  eTest.log(LogStatus.FAIL, "Filtration About Created Corporate Doesn't Work");
	  addScreenshots("Corporate List After Search about "+corName+" ","Fail");
	  } 
	  }
	  
	  
	
	  
	  //-----------Validate that confirmation message for pool creation be shown
	  //-----------Validate that pool number be shown with SME in corporate list in home screen
	  
	  @Test(dataProvider = "CreatePoolAccountTDProvider", priority = 4) 
	  public void poolCreation(PoolCreationDM PoolCreationDM) throws Exception 
	  {
		  String poolNumber = PoolCreationDM.getAccountNumber();
	  
	  HomeSuper homePage = new HomeSuper(wDriver);
      homePage.useSearchBox(PoolCreationDM.getCorporateName());
	  homePage.clickonFirstRow();
	  
	  CorporateBasicInfoSuper corBasicInfo = new CorporateBasicInfoSuper(wDriver);  
	  corBasicInfo.clickonPoolAccountTab();
	  
	  PoolAccountSuper poolAccount = new PoolAccountSuper(wDriver);  
		
	  poolAccount.insertPoolData(PoolCreationDM); 	 
	  poolAccount.clickonSaveButton();
	  
	  if(poolAccount.confirmationMessageValue().contains("Pool account linked successfully") || poolAccount.confirmationMessageValue().contains("Pool account Updated successfully") ) 
	  {
	  eTest.log(LogStatus.PASS,"Pool Number: " + poolNumber);
	  eTest.log(LogStatus.PASS, "Successful Pool Creation Message Was Shown");
	  addScreenshots("Successful Creation Message of "+poolNumber+" ","Success");
	  }
	  else 
	  { 
	  eTest.log(LogStatus.FAIL, "Pool Number: " + poolNumber);
	  eTest.log(LogStatus.FAIL, "Successful Pool Creation Message Wasn't Shown");
	  addScreenshots("No Successful Creation Message of "+poolNumber+" ","Fail"); 
	  }
	  
	  
	  poolAccount.clickonMainLogo();
	  
	  homePage = new HomeSuper(wDriver);
	  homePage.useSearchBox(PoolCreationDM.getCorporateName());
	  
	  if(homePage.firstPoolAcctCellValue().contains(poolNumber))
	  {
	  eTest.log(LogStatus.PASS,"Pool Number: " + poolNumber);
	  eTest.log(LogStatus.PASS, "Pool Number Be Shown in Corporates List");
	  addScreenshots("Pool Number : " +poolNumber+" Be Shown in Corporates List","Success"); 
	  }
	  else
	  { 		
	  eTest.log(LogStatus.FAIL, "Pool Number: " + poolNumber);
	  eTest.log(LogStatus.FAIL, "Pool Number Wasn't Shown in Corporates List");
	  addScreenshots("Pool Number : " +poolNumber+" Wasn't Shown in Corporates List","Fail"); }
	  }
	  
	  
	  //-----------Validate that confirmation message for voucher pool creation be shown
	  
	 
	  @Test(dataProvider = "CreateVoucherPoolAccountTDProvider", priority = 5)
	  public void voucherPoolCreation(VoucherPoolCreationDM voucherPoolCreationDM) throws Exception 
	  { 
		  String voucherPoolNumber = voucherPoolCreationDM.getVoucherAccountNumber();
	  
	  HomeSuper homePage = new HomeSuper(wDriver);
	  homePage.useSearchBox(voucherPoolCreationDM.getCorporateName());
	  homePage.clickonFirstRow();
	  
	  CorporateBasicInfoSuper corBasicInfo = new CorporateBasicInfoSuper(wDriver);  
	  corBasicInfo.clickonCorporateProgramTab();
	  
	  VoucherPoolAccountSuper voucherPoolAccount = new VoucherPoolAccountSuper(wDriver);  
	  voucherPoolAccount.openAllowedPayoutDroplist();
	  voucherPoolAccount.selectPayoutOption("Main Wallet");
	  voucherPoolAccount.selectPayoutOption("Salary Wallet");
	  
	  if(voucherPoolCreationDM.getRequiredValue().equals("TRUE")) 
	  {
	  voucherPoolAccount.insertVoucherPoolData(voucherPoolCreationDM);
	  }
	  
	  voucherPoolAccount.clickOnSaveButton();
	  
	  if(voucherPoolAccount.confirmationMessageValue().contains("SME Programs are linked successfully"))
	  {
	  eTest.log(LogStatus.PASS,"Voucher Pool Number: " + voucherPoolNumber);
	  eTest.log(LogStatus.PASS,"Voucher Pool Creation Message Was Shown");
	  addScreenshots("Voucher Pool Creation Message Was Shown of "+voucherPoolNumber+" ","Success"); 
	  }
	  else
	  {
	  eTest.log(LogStatus.FAIL, "Voucher Pool Number: " + voucherPoolNumber); 
	  eTest.log(LogStatus.FAIL,"Voucher Pool Creation Message Wasn't Shown");
	  addScreenshots("Voucher Pool Creation Message Wasn't Shown of"+voucherPoolNumber+" ","Fail"); 
	  }
	    
	   voucherPoolAccount.clickonMainLogo();	  
	  }
	
	  
	  //------------Validate that Creation Confirmation Message be shown
	  //------------Validate that new created admins be shown in admins users list
	  
	  
	  @Test(dataProvider = "CreateAdminTDProvider", priority = 6)
	  public void AdminCreation(AdminCreationDM adminCreationDM) throws Exception 
	  {
		  String adminName = adminCreationDM.getAdminName();
	  
	  HomeSuper homePage = new HomeSuper(wDriver);
	  homePage.useSearchBox(adminCreationDM.getCorporateName());
	  homePage.clickonFirstRow();
	  
	  CorporateBasicInfoSuper corBasicInfo = new CorporateBasicInfoSuper(wDriver); 
	  corBasicInfo.clickonAdminUsersTab();
	  
	  AdminsListSuper adminListSuper = new AdminsListSuper(wDriver);
	  adminListSuper.clickonCreateButton();
	  
	  AdminCreationSuper adminCreationSuper = new AdminCreationSuper(wDriver);
	  adminCreationSuper.insertAdminData(adminCreationDM);
	  adminCreationSuper.clickonCreateButton();
	  adminCreationSuper.clickonPopupConfirmButton();
	  
	  if(adminCreationSuper.confirmationMessageValue().contains("User created Successfully"))
	  {
	  eTest.log(LogStatus.PASS,adminCreationDM.getAdminRole() +" Admin: " +adminName); 
	  eTest.log(LogStatus.PASS, adminCreationDM.getAdminRole()+"  Was Created Successfully");
	  addScreenshots("Creation Confirmation Message of "+adminName+" ","Success"); 
	  } 
	  else
	  { 
		  eTest.log(LogStatus.FAIL, adminCreationDM.getAdminRole() +" Admin: " +adminName);
		  eTest.log(LogStatus.FAIL, adminCreationDM.getAdminRole()+" Wasn't Created successfully");
	      addScreenshots("Creation Confirmation Message of "+adminName+" ","Fail"); 
	  }
	  
	  
	  adminListSuper = new AdminsListSuper(wDriver);
	  adminListSuper.SearchByMail(adminCreationDM);
	  
	  
	  if(adminListSuper.firstNameCellValue().equals(adminCreationDM.getAdminName())
	  &&  adminListSuper.firsEmailCellValue().equals(adminCreationDM.getAdminEmail())
	  &&  adminListSuper.firstMobileNumberCellValue().equals(adminCreationDM. getPhoneNumber())
	  && adminListSuper.firstRoleCellValue().equals(adminCreationDM.getAdminRole()))
	  {
	  eTest.log(LogStatus.PASS,adminCreationDM.getAdminRole() +" Admin: " +adminName); 
	  eTest.log(LogStatus.PASS,"Can Filter About Created Admin: "+ adminCreationDM.getAdminRole());
	  addScreenshots("Admin list Screen Contains "+adminName+" ","Success"); 
	  } 
	  else
	  { 
		  eTest.log(LogStatus.FAIL, adminCreationDM.getAdminRole() +" Admin: " +adminName);
		  eTest.log(LogStatus.FAIL,"Filtration Doesn't Work With Created Admin :" + adminCreationDM.getAdminRole());
	      addScreenshots("Admins List Screen Doesn't contain "+adminName+" ","Fail"); 
	  }
	  
	  adminListSuper.clickonMainLogo();	  
	 }
	 
	
}
