package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bases.ScreensBase;
import data.models.EmployeeCreationDM;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class AccountRequestDetailsMorC  extends ScreensBase {

	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   JavascriptExecutor js = null;
	   
		public AccountRequestDetailsMorC(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			js = (JavascriptExecutor)wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		

	//------------------Selectors----------
	
	
	@FindBy(id = "employeeRequest-firstName")
	WebElement FirstName_Textfield;
	
	@FindBy(id = "employeeRequest-lastName")
	WebElement LastName_Textfield;
	
	@FindBy(id = "employeeRequest-mobileNumber")
	WebElement Mobile_Textfield;
	
	@FindBy(id = "employeeRequest-email")
	WebElement Email_Textfield;
	
	@FindBy(css = ".mat-select-value")
	WebElement Gender_Droplist;
	
	@FindBy(xpath = "//span[contains(.,'Male')]")
	WebElement Male_Option;
	
	@FindBy(xpath = "//span[contains(.,'Female')]")
	WebElement Female_Option;
	
	@FindBy(id = "employeeRequest-birthdate")
	WebElement BirthDate_Textfield;
	
	@FindBy(id = "employeeRequest-nationalId")
	WebElement NAT_Textfield;
	
	@FindBy(id = "employeeRequest-officialIdExpiryDate")
	WebElement ExpirationDate_Textfield;
	
	@FindBy(css = ".logo:nth-child(1) > .ng-tns-c40-30 > .ng-tns-c40-30")
	WebElement Frontend_ID_Button;
	
	@FindBy(css = ".logo:nth-child(2) > .ng-tns-c40-30 > .ng-tns-c40-30")
	WebElement Backend_ID_Button;
	
	@FindBy(id = "createEmployee-Submit")
	WebElement Create_Button;
	
	@FindBy(id = "viewEditEmployee-acceptSubmit")
	WebElement Accept_Button;

	@FindBy(xpath = "//span[contains(.,'OK')]")
	WebElement Ok_Button;
	
	//-----------------Methods-------------
	
	
	public void insertEmployeeData(EmployeeCreationDM employeeCreationDM) throws Exception
	{
		FirstName_Textfield.sendKeys(employeeCreationDM.getFirstName());
		LastName_Textfield.sendKeys(employeeCreationDM.getLastName());
		Mobile_Textfield.sendKeys(employeeCreationDM.getMobileNumber());
		Email_Textfield.sendKeys(employeeCreationDM.getEmail());
		
		Gender_Droplist.click();
		if(employeeCreationDM.getGender().equals("Male"))
		{	Male_Option.click(); }
		else
		{ Female_Option.click();}
		
		BirthDate_Textfield.sendKeys(employeeCreationDM.getBirthDate());
		NAT_Textfield.sendKeys(employeeCreationDM.getNAT());
		ExpirationDate_Textfield.sendKeys(employeeCreationDM.getExpirationDate());
		
		Frontend_ID_Button.click();		
		Thread.sleep(3000);
		String fullFrontIDPath = picPath(employeeCreationDM.getForntID().toString());
		uploadFile(fullFrontIDPath);
		
		Backend_ID_Button.click();
		Thread.sleep(3000);
		String fullBackIDPath = picPath(employeeCreationDM.getBackendID().toString());
		uploadFile(fullBackIDPath);
	}
	
    public void clickOnCreatebuttonn()
    {
    	js.executeScript("arguments[0].scrollIntoView();", Create_Button);
    	Create_Button.click();
    }
    
    public void acceptPendingRequest()
    {
    	js.executeScript("arguments[0].scrollIntoView();", Accept_Button);
    	Accept_Button.click();
    	wait.until(ExpectedConditions.visibilityOf(Ok_Button));
    	Ok_Button.click();
    }

	
	
}
