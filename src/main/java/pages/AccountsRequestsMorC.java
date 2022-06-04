package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import data.models.EmployeeCreationDM;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsRequestsMorC {
	
	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public AccountsRequestsMorC(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			PageFactory.initElements(wDriver,this);	
		}
		
		

	//------------------Selectors----------
	
	
	@FindBy(css = ".ng-tns-c39-85 > .material-icons")
	WebElement Plus_Button;
	
	@FindBy(xpath = "//div[@id='wrapper']/div/fuse-content/app-requests/div/app-emp-accounts-requests-tab/div/mat-accordion/mat-expansion-panel/mat-expansion-panel-header")
	WebElement Search_Droplist;
	
	@FindBy(xpath = "//div[2]/mat-form-field/div/div/div/input")
	WebElement Mobile_Searchfield;
	
	@FindBy(css = ".mat-primary > .mat-button-wrapper")
	WebElement Search_Button;
	
	@FindBy(css = ".mat-row")
	WebElement First_Row;
	
	
	
	
	@FindBy(css = ".mat-cell:nth-child(2)")
	WebElement Name_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(3)")
	WebElement Mobile_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(4)")
	WebElement NAT_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(10)")
	WebElement Status_First_Cell;
		
	@FindBy(css = ".user-button")
	WebElement Profile_Droplist;
	
	@FindBy(xpath = "//span[contains(.,'Logout')]")
	WebElement Logout_button;
	
	@FindBy(xpath = "//span[contains(.,'The employee has been created successfully')]")
	WebElement Creation_Confirmation_Message;
	
	@FindBy(linkText = "Requests")
	WebElement Accounts_Requests_Screen;
	
	@FindBy(linkText = "Accounts")
	WebElement Accounts_Screen;
	
	@FindBy(css = ".ng-tns-c31-17:nth-child(1)")
	WebElement Corporate_Accounts_List;
	
	
	//-----------------Methods-------------
	
	
  public void clickOnPlusIcon()
  {
	  Plus_Button.click();
  }
   
  public String nameFirstCellValue()
  {
	  return Name_First_Cell.getText().toString();
  }
  
  public String mobileFirstCellValue()
  {
	  return Mobile_First_Cell.getText().toString();
  }
  
  public String natFirstCellValue()
  {
	  return NAT_First_Cell.getText().toString();
  }
  
  public String statusFirstCellValue()
  {
	  return Status_First_Cell.getText().toString();
  }
  
  
  public void clickOnFirstRow()
  {
	  wait.until(ExpectedConditions.elementToBeClickable(Logout_button));
	  First_Row.click();
  }
  
  public String creationConfirmationMessageValue()
  {
	  wait.until(ExpectedConditions.visibilityOf(Creation_Confirmation_Message));
	  return Creation_Confirmation_Message.getText().toString();
  }
  
  
	public void openAccountsScreen()
	{
		if(Accounts_Screen.isDisplayed())
		{
			Accounts_Screen.click();
		}
		else
		{
			Corporate_Accounts_List.click();
			Accounts_Screen.click();
		}
	}
  
	  public void searchByMobile(String Mobile) 
	  {
		  Search_Droplist.click();
		  wait.until(ExpectedConditions.visibilityOf(Mobile_Searchfield));
		  Mobile_Searchfield.sendKeys(Mobile);
		  Search_Button.click();
	  }
	  
	  public void logout()
	  {
		  Profile_Droplist.click();
		  wait.until(ExpectedConditions.visibilityOf(Logout_button));
		  Logout_button.click();
	  }
	
}
