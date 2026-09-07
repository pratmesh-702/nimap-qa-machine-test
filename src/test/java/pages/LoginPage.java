package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends DriverFactory
{
    public LoginPage(WebDriver Driver)
    {
        super(Driver);
    }
    
    private By username = By.xpath("//input[@placeholder='Enter Email or Mobile Number']");
    
    private By password = By.xpath("//input[@placeholder='Password']");
    
    private By SignIn = By.xpath("//span[text()='Sign In']/parent::button | //span[text()='Sign In']");
    
    private By dashboardUserSpan = By.xpath("//span[contains(text(),'Prathamesh')]");
    
    private By ValidateMail = By.xpath("//p[text()='moreprath1999@gmail.com']");
    
    
    public void username(String user)
    {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        WebElement element = Driver.findElement(username);
        element.clear();
        element.sendKeys(user);
    }
    
    public void password(String pass)
    {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        WebElement element = Driver.findElement(password);
        element.clear();
        element.sendKeys(pass);
    }
    
    public void signin() 
    {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(SignIn));
        
        
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
    
    public String validateDashboardPage()
    {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardUserSpan));
        
       
        return element.getText();
        
    }
    
    public void clickProfile()
    {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        
       
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'toaster')]")));
        } catch (Exception e) {
            
        }

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dashboardUserSpan));
        
        
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
    
    public String ValidateMail()
    {
    	 WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(20));
         WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ValidateMail));
         
        return element.getText();
    }

    
    
}