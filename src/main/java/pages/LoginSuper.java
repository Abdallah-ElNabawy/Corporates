package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.models.LoginSuperDM;
import org.openqa.selenium.interactions.Actions;

public class LoginSuper {

	
	//-------------Initialize web driver-------------
	
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public LoginSuper(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,60);
			PageFactory.initElements(wDriver, this);	
		}
		
		public void initializeWebDriver(WebDriver wDriver)
		{
			this.wDriver = wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		

	//------------------Selectors----------
	
	
	@FindBy(xpath = "//button[@id='adminLogin-loginSubmit']/span")
	WebElement Login_Button;
	
	@FindBy(xpath = "//input[@id='adminLogin-email']")
	WebElement Email_Textfield;
	
	@FindBy(xpath = "//input[@id='adminLogin-password']")
	WebElement Password_Textfield;
	
	
	
	//------------Methods-----------------------
	
	public void loginBySuperAdmin(LoginSuperDM loginSuperDM) throws Exception
	{
		wait.until(ExpectedConditions.elementToBeClickable(Login_Button));

		Email_Textfield.sendKeys(loginSuperDM.getUserMail());
		Password_Textfield.sendKeys(loginSuperDM.getPassword());
		Login_Button.click();
	}
	

	
}
