package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.models.EmployeeCreationDM;

public class AccountsMorC {
	

	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public AccountsMorC(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			PageFactory.initElements(wDriver,this);	
		}
		
		public void initializeWebDriver(WebDriver wDriver)
		{
			this.wDriver = wDriver;
			PageFactory.initElements(wDriver,this);	
		}

	//------------------Selectors----------
	
	
	@FindBy(linkText = "Requests")
	WebElement Accounts_Requests_Screen;
	
	@FindBy(linkText = "Accounts")
	WebElement Accounts_Screen;
	
	@FindBy(xpath = "//span[contains(.,'Corporate Accounts')]")
	WebElement Corporate_Accounts_List;
	
	@FindBy(css = ".mat-cell:nth-child(1)")
	WebElement FirstName_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(2)")
	WebElement LastName_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(3)")
	WebElement Email_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(4)")
	WebElement Mobile_First_Cell;
	
	@FindBy(css = ".mat-cell:nth-child(5)")
	WebElement NAT_First_Cell;
	
	@FindBy(id = "mat-expansion-panel-header-3")
	WebElement Search_Droplist;
	
	@FindBy(xpath = "//div[2]/mat-form-field[2]/div/div/div/input")
	WebElement Mobile_Searchfield;
	
	@FindBy(css = ".mat-primary > .mat-button-wrapper")
	WebElement Search_Button;
	
	@FindBy(css = ".mat-row")
	WebElement First_Row;
	
	@FindBy(css = ".user-button")
	WebElement Profile_Droplist;
	
	@FindBy(xpath = "//span[contains(.,'Logout')]")
	WebElement Logout_button;
		
	@FindBy(linkText = "History")
	WebElement Payroll_History_Screen;
	
	@FindBy(xpath = "//a[contains(@href, '/corporate-wallets/orange/payroll/review')]")
	WebElement Payroll_Requests_Screen;
	
	@FindBy(xpath = "//a[contains(@href, '/corporate-wallets/orange/payroll/upload')]")
	WebElement Payroll_Upload_Batch_Screen;
	
	@FindBy(xpath = "//span[contains(.,'Payroll')]")
	WebElement Payroll_List;
	
	@FindBy(linkText = "Upload Batch")
	WebElement Accounts_Upload_Batch_Screen;
	
	
	//----------------Methods----------------
	
	public void openAccountsRequestsScreen()
	{
		if(Accounts_Screen.isDisplayed())
		{
		  Accounts_Requests_Screen.click();
		}
		else
		{
			Corporate_Accounts_List.click();
			Accounts_Requests_Screen.click();
		}
	}
	
	public void openAccountsUploadBatchScreen()
	{
		if(Accounts_Screen.isDisplayed())
		{
			Accounts_Upload_Batch_Screen.click();
		}
		else
		{
			Corporate_Accounts_List.click();
			Accounts_Upload_Batch_Screen.click();
		}
	}
	
	
	
	 public void searchByMobile(EmployeeCreationDM employeeCreationDM) 
	  {
		  Search_Droplist.click();
		  wait.until(ExpectedConditions.visibilityOf(Mobile_Searchfield));
		  Mobile_Searchfield.sendKeys(employeeCreationDM.getMobileNumber());
		  Search_Button.click();
	  }
	 
	 
	  public String mobileFirstCellValue()
	  {
		  return Mobile_First_Cell.getText().toString();
	  }
	  
	  public String natFirstCellValue()
	  {
		  return NAT_First_Cell.getText().toString();
	  }
	  
	  public String firstnameFirstCellValue()
	  {
		  return FirstName_First_Cell.getText().toString();
	  }
	  
	  public String lastnameFirstCellValue()
	  {
		  return LastName_First_Cell.getText().toString();
	  }
	  
	  public String emailFirstCellValue()
	  {
		  return Email_First_Cell.getText().toString();
	  }
	
	  
	  public void logout()
	  {
		  Profile_Droplist.click();
		  wait.until(ExpectedConditions.visibilityOf(Logout_button));
		  Logout_button.click();
	  }
	  
		public void openPayrollUploadBatchScreen()
		{
			if(Payroll_History_Screen.isDisplayed())
			{
			  Payroll_Upload_Batch_Screen.click();
			}
			else
			{
				Payroll_List.click();
				Payroll_Upload_Batch_Screen.click();
			}
		}
		
		public void openPayrollRequestsScreen()
		{
			if(Payroll_History_Screen.isDisplayed())
			{
				Payroll_Requests_Screen.click();
			}
			else
			{
				Payroll_List.click();
				Payroll_Requests_Screen.click();
			}
		}
		
		
	    public boolean validateProfileDroplistDisplayed()
	    {
	    	wait.until(ExpectedConditions.visibilityOf(Profile_Droplist));  
	    	
	    	if(Profile_Droplist.isDisplayed())
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
		

}
