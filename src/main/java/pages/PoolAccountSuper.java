package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.models.PoolCreationDM;

public class PoolAccountSuper {
	
	//-------------Initialize web driver-------------
	
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   JavascriptExecutor js = null;
	

		
		public PoolAccountSuper(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			js = (JavascriptExecutor)wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		
 //----------selectors--------------------
		
	
		
		@FindBy(id = "poolAccount-accountNumber")
		WebElement Pool_Number_TextField;
		
		@FindBy(id = "poolAccount-thresholdAmount")
		WebElement Threshold_TextField;
		
		@FindBy(id = "poolAccount-ceilingAmount")
		WebElement Ceiling_TextField;
		
		@FindBy(id = "poolAccount-dailyCreditLimit")
		WebElement Daily_credit_Limit_TextField;
		
		@FindBy(id = "poolAccount-dailyDebitLimit")
		WebElement Daily_Debit_Limit_TextField;
		
		@FindBy(id = "poolAccount-notificationSMSMobileNumber")
		WebElement Notification_SMS_TextField;
		
		@FindBy(id = "poolAccount-notificationEmail")
		WebElement Notification_Mail_TextField;

		@FindBy(xpath = "//mat-select[@id='poolAccount-categoryCode']/div/div/span")
		WebElement Category_droplist;
		
		@FindBy(id = "poolAccountInfo-saveSubmit")
		WebElement Save_Button;
		
		@FindBy(css = ".mat-simple-snackbar")
		WebElement Confirmation_Message;
		
		@FindBy(css  = ".headerLogo")
		WebElement Main_Logo;
		
		
		
	
		
		
//---------------Methods----------------
		
		
		public void insertPoolData(PoolCreationDM poolCreationDM)
		{
			wait.until(ExpectedConditions.visibilityOf(Pool_Number_TextField));			
			Pool_Number_TextField.sendKeys(poolCreationDM.getAccountNumber());
			
			Category_droplist.click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(.,'"+poolCreationDM.getCategory()+"')]")));	
			wDriver.findElement(By.xpath("//span[contains(.,'"+poolCreationDM.getCategory()+"')]")).click();
			
			Threshold_TextField.sendKeys(poolCreationDM.getThreholdAmount());
			Ceiling_TextField.sendKeys(poolCreationDM.getCeiling());
			Notification_SMS_TextField.sendKeys(poolCreationDM.getNotificationSMS());
			Notification_Mail_TextField.sendKeys(poolCreationDM.getNotificationMail());
			Daily_credit_Limit_TextField.sendKeys(poolCreationDM.getDailyCreditLimit());
			Daily_Debit_Limit_TextField.sendKeys(poolCreationDM.getDailyDebitLimit());
		}
		
		
		public void clickonSaveButton()
		{
			js.executeScript("arguments[0].scrollIntoView();", Save_Button);
			wait.until(ExpectedConditions.elementToBeClickable(Save_Button));
			Save_Button.click();		
		}
		
		
		public String confirmationMessageValue()
		{
			wait.until(ExpectedConditions.visibilityOf(Confirmation_Message));
			return Confirmation_Message.getText().toString();
		}
		
		public void clickonMainLogo()
		{
			wait.until(ExpectedConditions.elementToBeClickable(Main_Logo));
		    Main_Logo.click();
		}
}
