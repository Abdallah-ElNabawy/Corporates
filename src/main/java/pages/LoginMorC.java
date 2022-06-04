package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import data.models.AdminCreationDM;

public class LoginMorC {
	
	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
		
		public LoginMorC(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		

	//------------------Selectors----------
	
	
	@FindBy(id = "login-loginSubmit")
	WebElement Login_Button;
	
	@FindBy(id = "login-smeCode")
	WebElement Corporate_Textfield;
	
	@FindBy(id = "login-email")
	WebElement Email_Textfield;
	
	@FindBy(id = "login-password")
	WebElement Password_Textfield;
	
	
	//----------------Methods---------------
	
	public void loginByMakerorChecker(AdminCreationDM adminCreationDM)
	{
		Corporate_Textfield.sendKeys(adminCreationDM.getCorporateCode());
		Email_Textfield.sendKeys(adminCreationDM.getAdminEmail());
		Password_Textfield.sendKeys(adminCreationDM.getPassword());
		Login_Button.click();
	}

}
