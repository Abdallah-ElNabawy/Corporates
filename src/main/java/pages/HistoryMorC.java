package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryMorC {
	
	
	//-------------Initialize web driver-------------
	
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public HistoryMorC(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
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
		
		@FindBy(xpath = "//mat-row")
		WebElement First_Row;
		
		@FindBy(xpath = "//mat-cell[2]/p")
		WebElement SheetName_First_Cell;
		
		@FindBy(css = ".user-button")
		WebElement Profile_Droplist;
		
		@FindBy(xpath = "//span[contains(.,'Logout')]")
		WebElement Logout_button;
		
		//------------------Methods-------------
		
		
		public void searchBySheetName(String value)
		{
			Search_Box.click();
			wait.until(ExpectedConditions.visibilityOf(Sheet_Name_Textfield));
			Sheet_Name_Textfield.sendKeys(value);
			Search_button.click();
		}
		
		public void clickonFirstRow()
		{
			First_Row.click();
		}
		
		public String sheetNameFirstCellValue()
		{
			return SheetName_First_Cell.getText().toString();
		}

		public void logout()
		{
			 Profile_Droplist.click();
			 wait.until(ExpectedConditions.visibilityOf(Logout_button));
			 Logout_button.click();
		}
		
}
