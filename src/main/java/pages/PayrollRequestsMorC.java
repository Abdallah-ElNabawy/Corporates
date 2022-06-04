package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PayrollRequestsMorC {
	
	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public PayrollRequestsMorC(WebDriver wDriver)
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
	
		@FindBy(linkText = "History")
		WebElement Payroll_History_Screen;
		
		@FindBy(xpath = "//a[contains(@href, '/corporate-wallets/orange/payroll/review')]")
		WebElement Payroll_Requests_Screen;
		
		@FindBy(xpath = "//a[contains(@href, '/corporate-wallets/orange/payroll/upload')]")
		WebElement Payroll_Upload_Batch_Screen;
		
		@FindBy(xpath = "//span[contains(.,'Payroll')]")
		WebElement Payroll_List;
		
		@FindBy(xpath = "//mat-panel-title[contains(.,'Search')]")
		WebElement Search_Box;
		
		@FindBy(xpath = "//div/div/input")
		WebElement Sheet_Name_Textfield;
		
		@FindBy(css = ".mat-primary > .mat-button-wrapper")
		WebElement Search_button;
		
		@FindBy(xpath = "//mat-cell[2]/p")
		WebElement SheetName_First_Cell;
		
		@FindBy(xpath = "//mat-cell[6]/p")
		WebElement Status_First_Cell;
		
		@FindBy(xpath = "//mat-row")
		WebElement First_Row;
		
		@FindBy(xpath = "//span[contains(.,'ACCEPT')]")
		WebElement Accept_Request_Button;
		
		@FindBy(xpath = "//textarea")
		WebElement OTP_Textfield;
		
		@FindBy(xpath = "//button[contains(.,'Accept')]")
		WebElement Accept_OTP_Button;
		
		@FindBy(css = ".user-button")
		WebElement Profile_Droplist;
		
		@FindBy(xpath = "//span[contains(.,'Logout')]")
		WebElement Logout_button;
		
		//--------------Methods-------------
		
		public void openPayrollHistoryScreen()
		{
			if(Payroll_History_Screen.isDisplayed())
			{
				Payroll_History_Screen.click();
			}
			else
			{
				Payroll_List.click();
				Payroll_History_Screen.click();
			}
		}
		
		public void searchBySheetName(String value)
		{
			Search_Box.click();
			wait.until(ExpectedConditions.visibilityOf(Sheet_Name_Textfield));
			Sheet_Name_Textfield.sendKeys(value);
			Search_button.click();
		}
		
		public String sheetNameFirstCellValue()
		{
			return SheetName_First_Cell.getText().toString();
		}
		
		public String statusFirstCellValue()
		{
			return Status_First_Cell.getText().toString();
		}

		public void clickonFirstRow()
		{
			First_Row.click();
		}
		
		public void acceptPendingRequest(String OTP)
		{
			Accept_Request_Button.click();
			wait.until(ExpectedConditions.visibilityOf(OTP_Textfield));
			OTP_Textfield.sendKeys(OTP);
			Accept_OTP_Button.click();			
		}
		
				  
	    public void logout()
		{
			  Profile_Droplist.click();
			  wait.until(ExpectedConditions.visibilityOf(Logout_button));
			  Logout_button.click();
		}
	    
}
