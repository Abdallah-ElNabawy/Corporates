package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeSuper {

	//-------------Initialize web driver-------------
	
	   protected WebDriver wDriver;
	   WebDriverWait wait = null;
	   
		public HomeSuper(WebDriver wDriver)
		{		
			this.wDriver = wDriver;	
			wait = new WebDriverWait(wDriver,30);		   
			PageFactory.initElements(wDriver,this);	
		}
	
		
	//---------------selectors---------------
		
	
	    @FindBy(css  = ".headerLogo")
	    public WebElement Main_logo;
	    
	    @FindBy(xpath  = "//button/img")
	    public WebElement Plus_Button;
	    
	    
	    @FindBy(css  = ".mat-select-value")
	    public WebElement Pagination_Droplist;
	    
	    @FindBy(xpath  = "//div[@id='products']/div/div[2]/div/input")
	    public WebElement Search_Box;
	    
	    @FindBy(xpath  = "//mat-row[1]/mat-cell")
	    public WebElement First_Code_Cell;
	    
	    @FindBy(xpath  = "//mat-row[1]/mat-cell[2]")
	    public WebElement First_Name_Cell;
	    
	    @FindBy(xpath  = "//mat-row[1]/mat-cell[3]")
	    public WebElement First_Pool_Acct_Cell;
	    
	    @FindBy(xpath  = "//mat-row")
	    public WebElement First_Row;
	   	      
	    @FindBy(css  = ".mat-simple-snackbar")
	    public WebElement Creation_Confirmation_Message;
	    
	    
	  
	    
	    
	//-------------- Actions Methods----------
	    
	    public void clickonPlusButton()
	    {    	
	    	Plus_Button.click();
	    }
	    
	    
	    public void useSearchBox(String searchKey)
	    {
	    	wait.until(ExpectedConditions.visibilityOf(Search_Box));
	    	Search_Box.clear();
	    	Search_Box.sendKeys(searchKey);
	    	Search_Box.sendKeys(Keys.ENTER);
	    	wait.until(ExpectedConditions.visibilityOf(First_Row));
	    }
	    
	    
	    public void selectPageSize(String number)
	    {
	    	Pagination_Droplist.click();
	    	
	    	if(number.equals("5"))
	    	{
	    		wDriver.findElement(By.xpath("//mat-option[1]/span")).click();
	    	}
	    	else if(number.equals("10"))
	    	{
	    		wDriver.findElement(By.xpath("//mat-option[2]/span")).click();
	    	}
	    	else if(number.equals("25"))
	    	{
	    		wDriver.findElement(By.xpath("//mat-option[3]/span")).click();
	    	}
	    	else if(number.equals("100"))
	    	{
	    		wDriver.findElement(By.xpath("//mat-option[4]/span")).click();
	    	}    	
	    }
	    
	    
	    public String firstCodeCellValue()
	    {
	    	return First_Code_Cell.getText().toString();
	    }
	    
	    public String firstNameCellValue()
	    {
	    	return First_Name_Cell.getText().toString();
	    }
	    
	    public String firstPoolAcctCellValue()
	    {
	    	return First_Pool_Acct_Cell.getText().toString();
	    }
	    
	    public String contentofCreationConfirmationMesssage()
	    {
	    	wait.until(ExpectedConditions.visibilityOf(Creation_Confirmation_Message));
	    	return Creation_Confirmation_Message.getText().toString();
	    }
	    
	 
	    public void clickonFirstRow()
	    {
	    	First_Row.click();
	    }
	    
	    
	    public boolean validateMainLogoDisplayed()
	    {
	    	wait.until(ExpectedConditions.visibilityOf(Main_logo));  
	    	
	    	if(Main_logo.isDisplayed())
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    
	    
}

