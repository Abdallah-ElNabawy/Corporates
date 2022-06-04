package corporateModuleTestCases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import bases.CorporateModuleBase;
import data.models.CorporateCreationDM;
import data.models.LoginSuperDM;
import pages.CorporateBasicInfoSuper;
import pages.HomeSuper;
import pages.LoginSuper;

public class CorporateUpdating extends CorporateModuleBase{
	
	
	
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
	
	  
	  //----------Validate that updating confirmation message be shown after update SME 
	  //----------Validate that updated SME be shown with updated data in corporate list in home screeb
	  
	  @Test(dataProvider = "CreateCorporateTDProvider", priority = 2) 
	  public void corporateUpdating(CorporateCreationDM corporateCreationDM) throws Exception 
	  {
	  String corName= corporateCreationDM.getCorporateName();
	  
	  HomeSuper homePage = new HomeSuper(wDriver);
	  homePage.useSearchBox(corporateCreationDM.getCorporateName());
	  homePage.clickonFirstRow();
	  
	  CorporateBasicInfoSuper corBasicInfo = new CorporateBasicInfoSuper(wDriver);
	  corBasicInfo.updateCorporateName(corporateCreationDM);
	  corBasicInfo.clickOnEnterKey();
	  
	  
	  if(corBasicInfo.contentofUpdatingConfirmationMesssage().contains("The info is saved successfully")) 
	  {
	  eTest.log(LogStatus.PASS,"Corporate Code: " + corName);
	  eTest.log(LogStatus.PASS, "Successful Updating Message was shown ");
	  addScreenshots("Successful Updating Message of "+corName+" ","Success");
	  }
	  else 
	  { 
	  eTest.log(LogStatus.FAIL, "Corporate Code: " + corName);
	  eTest.log(LogStatus.FAIL, "Successful Updating Message Wasn't Shown");
	  addScreenshots("No Successful Updating Message of "+corName+" ","Fail");
	  }
	  
	  corBasicInfo.clickonMainLogo();
	  
	  homePage = new HomeSuper(wDriver);
	  homePage.useSearchBox(corporateCreationDM.getCorporateName());
	  
	  if(homePage.firstNameCellValue().contains("Update"))
	  {
	  eTest.log(LogStatus.PASS,"Corporate Code: " + corName);
	  eTest.log(LogStatus.PASS, "Corporate Be Shown with New Update");
	  addScreenshots("Updating of "+corName+" in Corporates List","Success");
	  }
	  else 
	  { 
	  eTest.log(LogStatus.FAIL, "Corporate Code: " + corName);
	  eTest.log(LogStatus.FAIL, "Corporate Wasn't Shown with New Update");
	  addScreenshots("No Update of "+corName+" in Corporates List","Fail");
	  }
	  
	  }
	  

}
