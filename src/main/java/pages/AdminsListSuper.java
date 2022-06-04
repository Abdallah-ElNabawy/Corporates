package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.models.AdminCreationDM;

public class AdminsListSuper {

	//-------------Initialize web driver-------------
	
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   JavascriptExecutor js = null;
	  
		
		public AdminsListSuper(WebDriver wDriver)
		{		
			this.wDriver = wDriver;
			wait = new WebDriverWait(wDriver,30);
			js = (JavascriptExecutor)wDriver;
			PageFactory.initElements(wDriver,this);	
		}
		
		
//----------selectors--------------------
		
	
		
		@FindBy(css = ".mat-accent-bg > .mat-button-wrapper")
		WebElement Create_Admin_button;
		
		@FindBy(css = ".mat-expansion-panel-header-description")
		WebElement Search_Droplist;
	
		@FindBy(xpath = "//div/div/input")
		WebElement Email_Textfield;
		
		@FindBy(css = ".mat-primary > .mat-button-wrapper")
		WebElement Search_button;
				
		@FindBy(css = ".mat-row")
		WebElement First_List_Row;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell")
		WebElement First_Name_Cell;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[2]")
		WebElement First_Email_Cell;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[3]")
		WebElement First_Mobile_Number_Cell;
		
		@FindBy(xpath = "//mat-table[@id='tableSection']/mat-row/mat-cell[4]")
		WebElement First_Role_Cell;
		
		@FindBy(css  = ".headerLogo")
		WebElement Main_Logo;
		
//-----------Methods---------------------
		
		
		public void clickonCreateButton()
		{
			wait.until(ExpectedConditions.elementToBeClickable(Create_Admin_button));
			Create_Admin_button.click();
		}
		
		public void SearchByMail(AdminCreationDM adminCreationDM)
		{
			wait.until(ExpectedConditions.elementToBeClickable(Search_Droplist));
			Search_Droplist.click();
			Email_Textfield.sendKeys(adminCreationDM.getAdminEmail());
			Search_button.click();
			wait.until(ExpectedConditions.visibilityOf(First_List_Row));
		}
		
		public String firstRowValue()
		{
			return First_List_Row.getText();
		}
		
		public String firstNameCellValue()
		{
			return First_Name_Cell.getText();
		}
		
		public String firsEmailCellValue()
		{
			return First_Email_Cell.getText();
		}
		
		public String firstMobileNumberCellValue()
		{
			return First_Mobile_Number_Cell.getText();
		}
		
		public String firstRoleCellValue()
		{
			return First_Role_Cell.getText();
		}
	
		
		public void clickonMainLogo()
		{
			js.executeScript("arguments[0].click();", Main_Logo);
		}
}
