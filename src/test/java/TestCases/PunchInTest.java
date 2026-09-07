package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseTest;
import pages.LoginPage;
import pages.PunchInPop;

public class PunchInTest extends BaseTest {

    @Test(priority = 1)
    public void verifyPunchInToastMessage() throws InterruptedException {
        // १. लॉगिन करा
        LoginPage lp = new LoginPage(Driver);
        lp.username("moreprath1999@gmail.com");
        lp.password("prathmesh702");
        lp.signin();

       
        PunchInPop punchPage = new PunchInPop(Driver);

       
        punchPage.navigateToAttendance();

        
        punchPage.AddNew();

        
        punchPage.selectDate("7");
        punchPage.setPunchInTime("09:30 AM");
        punchPage.setPunchOutTime("06:30 PM");

      
        punchPage.clickSubmit();

      
        String actualToast = punchPage.getToastMessage();
        System.out.println("CAPTURED TOAST / STATUS MESSAGE: " + actualToast);

        
        boolean isSuccess = !actualToast.equals("No Toast Displayed");
        
        Assert.assertTrue(isSuccess, "Attendance Validation Failed! Status: " + actualToast);
    }
}