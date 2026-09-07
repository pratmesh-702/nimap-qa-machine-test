package TestCases;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Base.BaseTest;
import pages.AddCustomerPage;
import pages.LoginPage;

public class AddCustomerTest extends BaseTest {

    @Test(priority = 1, dataProvider = "CustomerData", dataProviderClass = Utilities.DataProviders.class)
    public void verifyAddCustomer(String name, String email, String phone, String address) throws InterruptedException {
       
       
        LoginPage lp = new LoginPage(Driver);
        lp.username("moreprath1999@gmail.com");
        lp.password("prathmesh702");
        lp.signin();

        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

        AddCustomerPage customerPage = new AddCustomerPage(Driver);
        
      
        customerPage.clickMyCustomersDropdown();
        
       
        customerPage.clickMyCustomerSubMenu();
        
       
        customerPage.clickManageButton();
        
        
        customerPage.clickNewCustomer();
    }
}