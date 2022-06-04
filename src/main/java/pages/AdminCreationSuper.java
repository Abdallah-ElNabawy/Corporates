package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.models.AdminCreationDM;

public class AdminCreationSuper {
	
	//-------------Initialize web driver-------------
	
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		
		public AdminCreationSuper(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			PageFactory.initElements(wDriver,this);	
		}
		
		
//----------selectors--------------------
		
	
		
		@FindBy(id = "viewEditAdminUser-userName")
		WebElement UserName_TextField;
		
		@FindBy(id = "viewEditAdminUser-userEmail")
		WebElement Email_TextField;
		
		@FindBy(id = "viewEditAdminUser-userPhone")
		WebElement Mobile_TextField;
		
		@FindBy(css = ".mat-select-arrow")
		WebElement Role_DroPlist;
		
		@FindBy(xpath = "//span[contains(.,'Corporate Maker')]")
		WebElement Maker_Option;
		
		@FindBy(xpath = "//span[contains(.,'Corporate Checker')]")
		WebElement Checker_Option;
		
		@FindBy(id = "viewEditAdminUser-submit")
		WebElement Create_Button;
		
		
		@FindBy(xpath = "//button[@id='ConfirmDialogTemplate-ok']/span")
		WebElement Popup_Confirm_Button;
		
		
		@FindBy(css = ".mat-snack-bar-container")
		WebElement Confirmation_Message;
		
		
//------------Mehtods--------------------------
		
		public void insertAdminData(AdminCreationDM adminCreationDM)
		{
			wait.until(ExpectedConditions.visibilityOf(UserName_TextField));
			UserName_TextField.sendKeys(adminCreationDM.getAdminName());
			Email_TextField.sendKeys(adminCreationDM.getAdminEmail());
			Mobile_TextField.sendKeys(adminCreationDM.getPhoneNumber());
			Role_DroPlist.click();
			
			if(adminCreationDM.getAdminRole().equals("Maker"))
			{
				wait.until(ExpectedConditions.visibilityOf(Maker_Option));
				Maker_Option.click();
			}
			else
			{
				wait.until(ExpectedConditions.visibilityOf(Checker_Option));
				Checker_Option.click();
			}
		}
		
		
		public void clickonCreateButton()
		{
			wait.until(ExpectedConditions.elementToBeClickable(Create_Button));
			Create_Button.click();
		}
		
		public void clickonPopupConfirmButton()
		{
			wait.until(ExpectedConditions.elementToBeClickable(Popup_Confirm_Button));
			Popup_Confirm_Button.click();
		}
		
		public String confirmationMessageValue()
		{
			wait.until(ExpectedConditions.visibilityOf(Confirmation_Message));
			return Confirmation_Message.getText();
		}

}
