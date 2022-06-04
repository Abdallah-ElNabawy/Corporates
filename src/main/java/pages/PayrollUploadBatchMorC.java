package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.ScreensBase;

public class PayrollUploadBatchMorC extends ScreensBase {
	
	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public PayrollUploadBatchMorC(WebDriver wDriver)
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
		
		@FindBy(xpath = "//button[@id='uploadSheet']/span/span")
		WebElement Upload_Sheet_Button;
		
		@FindBy(id = "payroll-sheetName")
		WebElement Sheet_Name_Textfiled;
		
		@FindBy(id = "payroll-executionDate")
		WebElement Execution_Date_Textfiled;
		
		@FindBy(xpath = "//mat-select[@id='paroll-selectedPayoutType']/div/div/span")
		WebElement Payout_Droplist;
		
		@FindBy(xpath = "//span[contains(.,'Main Wallet')]")
		WebElement MainWallet_Option;
		
		@FindBy(xpath = "//span[contains(.,'Salary Wallet')]")
		WebElement SalaryWallet_Option;
		
		@FindBy(id = "payroll-sumbit")
		WebElement Submit_button;
		
		
		//--------------Methods-------------
		
		
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
		
		
		public void uploadPayrollSheet(String fileName) throws Exception
		{
			Upload_Sheet_Button.click();		
			Thread.sleep(3000);
			String batPath = batchPath(fileName);
			uploadFile(batPath);
		}
		
		
		public void insertSheetName(String Value)
		{
			Sheet_Name_Textfiled.sendKeys(Value);
		}
		
		
		public void selectPayoutOption(String value)
		{
			Payout_Droplist.click();
			if(value.equals("Main Wallet"))
			{
				MainWallet_Option.click();
			}
			else if(value.equals("Salary Wallet"))
			{
				SalaryWallet_Option.click();
			}
		}
		
		
		public void clickonSubmitButton()
		{
			Submit_button.click();
		}

}
