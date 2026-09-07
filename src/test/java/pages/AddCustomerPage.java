package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCustomerPage extends DriverFactory {

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

   
    private By myCustomersDropdown = By.xpath("//span[normalize-space()='My Customers'] | //a[.//span[text()='My Customers']]");
    
   
    private By myCustomerSubMenu = By.xpath("//li[contains(@class,'menu')]//span[text()='My Customer'] | //a[text()='My Customer'] | //*[normalize-space()='My Customer']");
    
   
    private By manageButton = By.xpath("//button[contains(.,'Manage')] | //button[contains(@class,'btn') and contains(.,'Manage')]");
    
   
    private By newCustomerOption = By.xpath("//*[normalize-space()='New Customer'] | //a[contains(text(),'New Customer')]");

   
    public void clickMyCustomersDropdown() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(myCustomersDropdown));
        
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    
    public void clickMyCustomerSubMenu() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(myCustomerSubMenu));
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

  
    public void clickManageButton() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        
        
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(manageButton));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

   
    public void clickNewCustomer() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(newCustomerOption));
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}