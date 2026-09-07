package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest 
{
    @Test(priority = 1, dataProvider = "LoginData", dataProviderClass = Utilities.DataProviders.class)
    public void verifyLogin(String USERNAME, String PASSWORD, String expResult) 
    {
        try 
        {
            LoginPage lp = new LoginPage(Driver);

            // 1. Enter Credentials & Sign In
            lp.username(USERNAME);
            lp.password(PASSWORD);
            lp.signin();

            // 2. Validate Dashboard User Name
            String expectedName = "Prathamesh";
            String actualName = lp.validateDashboardPage();
            Assert.assertEquals(actualName, expectedName, "User Name Validation Failed!");

            // 3. Validate Email in Profile
            lp.clickProfile();
            String expectedEmail = "moreprath1999@gmail.com";
            String actualEmail = lp.ValidateMail();
            Assert.assertEquals(actualEmail, expectedEmail, "Email Validation Failed!");

            System.out.println("Login Test Passed successfully for: " + USERNAME);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            Assert.fail("Login Test Failed: " + e.getMessage());
        }
    }
}