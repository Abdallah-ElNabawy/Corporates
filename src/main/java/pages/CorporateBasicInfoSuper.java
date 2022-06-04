package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import data.models.CorporateCreationDM;
import bases.ScreensBase;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;  

public class CorporateBasicInfoSuper extends ScreensBase {

	// -------------Initialize web driver-------------

	protected WebDriver wDriver;
	WebDriverWait wait = null;
	JavascriptExecutor js = null;

	public CorporateBasicInfoSuper(WebDriver wDriver) {
		this.wDriver = wDriver;
		wait = new WebDriverWait(wDriver, 30);
		js = (JavascriptExecutor) wDriver;
		PageFactory.initElements(wDriver, this);
	}

	public void initializeWebDriver(WebDriver wDriver) {
		this.wDriver = wDriver;
		wait = new WebDriverWait(wDriver, 30);
		js = (JavascriptExecutor) wDriver;
		PageFactory.initElements(wDriver, this);
	}

	// ----------selectors--------------------

	@FindBy(id = "corporateBasicInformation-name")
	WebElement Name_TextField;

	@FindBy(id = "corporateInformation-code")
	WebElement Code_TextField;

	@FindBy(id = "corporateInformation-address")
	WebElement Address_TextField;

	@FindBy(id = "corporateInformation-description")
	WebElement Description_TextField;

	@FindBy(id = "corporateInformation-country")
	WebElement Country_TextField;

	@FindBy(id = "corporateInformation-officialIdValue")
	WebElement OfficialID_TextField;

	@FindBy(id = "corporateInformation-personName")
	WebElement Contact_Name_TextField;

	@FindBy(id = "corporateInformation-personPhoneNumber")
	WebElement Contact_Mobile_TextField;

	@FindBy(id = "corporateInformation-personEmail")
	WebElement Contact_Mail_TextField;

	@FindBy(xpath = "//div[@id='upload']/a/span")
	WebElement Upload_button;

	@FindBy(xpath = "//span[contains(.,'SAVE')]")
	WebElement Save_button;

	@FindBy(css = ".mat-snack-bar-container")
	WebElement Updating_Confirmation_Message;

	@FindBy(css = ".headerLogo")
	WebElement Main_Logo;

	@FindBy(id = "mat-tab-label-0-2")
	WebElement Pool_Account_Tab;

	@FindBy(id = "mat-tab-label-0-1")
	WebElement Corporate_Program_Tab;

	@FindBy(id = "mat-tab-label-0-3")
	WebElement Admin_Users_Tab;

	@FindBy(xpath = "//mat-select[@id='corporateInformation-gatewayConfig']/div/div")
	WebElement GW_Configuration_Droplist;

	@FindBy(css = "#corporateInformation-officialIdType .mat-select-arrow")
	WebElement Officail_Type_Droplist;

	@FindBy(xpath = "//span[contains(.,'Commercial Registration Number')]")
	WebElement CRN_Option;

	@FindBy(xpath = "//span[contains(.,'National ID')]")
	WebElement NAT_Option;

	@FindBy(xpath = "//span[contains(.,'Governmental ID')]")
	WebElement GOV_Option;

	@FindBy(xpath = "//mat-option[contains(.,'orange')]")
	WebElement orange_Option;
	
	@FindBy(xpath = "//mat-option[contains(.,'orange')]")
	WebElement mfi_Option;
	
	@FindBy(xpath = "//mat-option[contains(.,'orange')]")
	WebElement myfawry_Option;

	// ------------Methods------------------------

	public void insertCorporateData(CorporateCreationDM corporateCreationDM) throws Exception {
		
		
		Name_TextField.sendKeys(corporateCreationDM.getCorporateName());
		Code_TextField.sendKeys(corporateCreationDM.getCode());
		Address_TextField.sendKeys(corporateCreationDM.getAddress());
		Description_TextField.sendKeys(corporateCreationDM.getDescription());

		Upload_button.click();
		Thread.sleep(10000);
		String fullLogoPath = picPath(corporateCreationDM.getlogoPath().toString());
		uploadFile(fullLogoPath);
		initializeWebDriver(this.wDriver);
		
		
		  js.executeScript("arguments[0].scrollIntoView();", Address_TextField);
		  wait.until(ExpectedConditions.elementToBeClickable(Officail_Type_Droplist));
		  Officail_Type_Droplist.click();
		  
		  if(corporateCreationDM.getOfficialID().equals("CRN")) 
		  {
			  wait.until(ExpectedConditions.visibilityOf(CRN_Option));
		      CRN_Option.click();
		      OfficialID_TextField.sendKeys(corporateCreationDM.getCRN()); 
		  }
		  else if (corporateCreationDM.getOfficialID().equals("GOV")) 
		  { 
			wait.until(ExpectedConditions.visibilityOf(GOV_Option));
			GOV_Option.click();
		    OfficialID_TextField.sendKeys(corporateCreationDM.getGOV()); 
		  } 
		  else
		  {
			  wait.until(ExpectedConditions.visibilityOf(NAT_Option));
		      NAT_Option.click();
		      OfficialID_TextField.sendKeys(corporateCreationDM.getNAT());
		  }
		
		
		
		 js.executeScript("arguments[0].scrollIntoView();", GW_Configuration_Droplist);
		 GW_Configuration_Droplist.click();
		  
		  if(corporateCreationDM.getGWConfiguration().equals("orange"))
		  {
		  wait.until(ExpectedConditions.visibilityOf(orange_Option));
		  orange_Option.click();
		  }
		  else if(corporateCreationDM.getGWConfiguration().equals("mfi"))
		  {
			  wait.until(ExpectedConditions.visibilityOf(mfi_Option));
			  mfi_Option.click();
		  }
		  else
		  {
			  myfawry_Option.click();
		  }
		  
		
		Contact_Name_TextField.sendKeys(corporateCreationDM.getContactName());
		Contact_Mail_TextField.sendKeys(corporateCreationDM.getContactMail());
		Contact_Mobile_TextField.sendKeys(corporateCreationDM.getContactMobile());
	}

	
	public void clickonSaveButton() 
	{
		js.executeScript("arguments[0].click();", Save_button);	
	}

	public String contentofUpdatingConfirmationMesssage() {
		System.out.print(Updating_Confirmation_Message.getText().toString());
		return Updating_Confirmation_Message.getText().toString();
	}

	public void updateCorporateName(CorporateCreationDM corporateCreationDM)
	{
		wait.until(ExpectedConditions.visibilityOf(Name_TextField));
		Name_TextField.clear();
		Name_TextField.sendKeys(corporateCreationDM.getCorporateName() + " Update");
	}

	public void clickonMainLogo() {
		Main_Logo.click();
	}

	public void clickonPoolAccountTab() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(Pool_Account_Tab));
		Pool_Account_Tab.click();
	}

	public void clickonCorporateProgramTab() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(Corporate_Program_Tab));
		Corporate_Program_Tab.click();
	}

	public void clickonAdminUsersTab()
	{
		wait.until(ExpectedConditions.elementToBeClickable(Admin_Users_Tab));
		Admin_Users_Tab.click();
	}
	
	public void clickOnEnterKey()
	{
		Name_TextField.sendKeys(Keys.ENTER);
	}

}
