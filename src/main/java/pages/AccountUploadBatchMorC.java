package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.ScreensBase;

public class AccountUploadBatchMorC extends ScreensBase{
	
	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   JavascriptExecutor js = null;
	   
		public AccountUploadBatchMorC(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			js = (JavascriptExecutor)wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		public void initializeWebDriver(WebDriver wDriver)
		{
			this.wDriver = wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		
		//------------------Selectors----------
		
		
		@FindBy(xpath = "//button[@id='uploadSheet']/span/span")
		WebElement Upload_Sheet_Button;
		
		@FindBy(xpath = "//span[contains(.,'Submit')]")
		WebElement Submit_Button;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[21]/span/a/span")
		WebElement Upload_Front_ID_Button;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[22]/span/a/span")
		WebElement Upload_Back_ID_Button;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[22]/p")
		WebElement Status_First_Cell;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[5]/p")
		WebElement Mobile_First_Cell;
			
		@FindBy(linkText = "Requests")
		WebElement Accounts_Requests_Screen;
		
		@FindBy(linkText = "Accounts")
		WebElement Accounts_Screen;
		
		@FindBy(xpath = "//span[contains(.,'Corporate Accounts')]")
		WebElement Corporate_Accounts_List;
		
		//-----------------Methods-----------------
		
		public void uploadEmployeeslSheet(String fileName) throws Exception
		{
			Upload_Sheet_Button.click();		
			Thread.sleep(3000);
			String batPath = batchPath(fileName);
			uploadFile(batPath);
		}
		
		
		public void uploadFrontandBackID(String frontImg, String BackImg) throws Exception
		{
			Upload_Front_ID_Button.click();		
			Thread.sleep(3000);
			String picPath = picPath(frontImg);
			uploadFile(picPath);
			
			Upload_Back_ID_Button.click();		
			Thread.sleep(3000);
			String picPath2 = picPath(BackImg);
			uploadFile(picPath2);
		}
		
		public void clickOnSubmitButton()
		{
			Submit_Button.click();
		}
		
		public String statusFirstCellValue()
		{
		  wait.until(ExpectedConditions.visibilityOf(Status_First_Cell));
		  return Status_First_Cell.getText();
		}
		
		public void scrollIntoStatusCell()
		{
			wait.until(ExpectedConditions.visibilityOf(Status_First_Cell));
			js.executeScript("arguments[0].scrollIntoView();", Status_First_Cell);
		}
		
		public String mobileFirstCellValue()
		{
		  wait.until(ExpectedConditions.visibilityOf(Mobile_First_Cell));
		  return Mobile_First_Cell.getText();
		}

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
		
}
