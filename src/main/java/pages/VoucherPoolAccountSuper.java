package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import data.models.PoolCreationDM;
import data.models.VoucherPoolCreationDM;

	
	
	public class VoucherPoolAccountSuper {
		
		
		//-------------Initialize web driver-------------
		
		
		   protected WebDriver wDriver;
		   WebDriverWait wait = null;
		   JavascriptExecutor js = null;
	
			public VoucherPoolAccountSuper(WebDriver wDriver)
			{		
				this.wDriver = wDriver;
				wait = new WebDriverWait(wDriver,30);	
				js = (JavascriptExecutor)wDriver;			   
				PageFactory.initElements(wDriver,this);	
			}
			
			public void initializeWebDriver(WebDriver wDriver)
			{
				this.wDriver = wDriver;
	            new WebDriverWait(wDriver,30);
	            js = (JavascriptExecutor)wDriver;	
				PageFactory.initElements(wDriver,this);	
			}
			
	 //----------selectors--------------------
			
		
			
			@FindBy(id = "corporateProgram-voucherDurationInDays")
			WebElement Expiration_Days_TextField;
		
			@FindBy(id = "corporateProgram-poolAccountNumber")
			WebElement Voucher_Account_Number_TextField;
						
			@FindBy(css = "#corporateProgram-allowedPayouts .mat-form-field-infix")
			WebElement Allowed_Payout_Droblist;
			
			@FindBy(xpath = "//span[contains(.,'Main Wallet')]")
			WebElement Main_Wallet_Option;
			
			@FindBy(xpath = "//span[contains(.,'Salary Wallet')]")
			WebElement Salary_Wallet_Option;
			
			@FindBy(id = "corporateProgram-saveSubmit")
			WebElement Save_button;
			
			@FindBy(css = ".mat-simple-snackbar")
			WebElement Confirmation_Message;
			
			@FindBy(css  = ".headerLogo")
			WebElement Main_Logo;
			
			@FindBy(css  = ".headerLogo")
			WebElement Back_Button;
			
			
	//------------Methods-----------------
			
			public void openAllowedPayoutDroplist()
			{
				wait.until(ExpectedConditions.elementToBeClickable(Allowed_Payout_Droblist));
				Allowed_Payout_Droblist.click();
			}
			
	
			
			public void selectPayoutOption(String payoutOption)
			{		
				if(payoutOption.equals("Main Wallet"))
				{
					wait.until(ExpectedConditions.visibilityOf(Main_Wallet_Option));
			       	Main_Wallet_Option.click();
				}
				else if(payoutOption.equals("Salary Wallet"))
				{
					wait.until(ExpectedConditions.visibilityOf(Salary_Wallet_Option));
				    Salary_Wallet_Option.click();
				}				
			}
			
			public void insertVoucherPoolData(VoucherPoolCreationDM voucherPoolCreationDM)
			{
				Expiration_Days_TextField.clear();
				Expiration_Days_TextField.sendKeys(voucherPoolCreationDM.getExpirationDays());
				Voucher_Account_Number_TextField.clear();
				Voucher_Account_Number_TextField.sendKeys(voucherPoolCreationDM.getVoucherAccountNumber());
			}
			
			public void clickOnEnterKey()
			{
				Voucher_Account_Number_TextField.sendKeys(Keys.ENTER);
			}
			
			public void clickOnSaveButton()
			{
				js.executeScript("arguments[0].click();", Save_button);			
			}
			
			
			public String confirmationMessageValue()
			{
				wait.until(ExpectedConditions.visibilityOf(Confirmation_Message));
				return Confirmation_Message.getText().toString();
			}
			
			
			public void clickonMainLogo()
			{
				js.executeScript("arguments[0].click();", Main_Logo);
			}
			
			
			public void gotoHomeScreen()
			{
				String URL = wDriver.getCurrentUrl();
				String hScreenURL = URL.split("/detail")[0];
				System.out.print(hScreenURL);
				wDriver.get(hScreenURL);			
			}
			
			public void clickOnBackButton()
			{
				js.executeScript("arguments[0].click();", Back_Button);	
			}

}
