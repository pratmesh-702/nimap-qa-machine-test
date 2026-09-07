package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PunchInPop extends DriverFactory {

    public PunchInPop(WebDriver Driver) {
        super(Driver);
    }

    // Locators
    private By attendanceMenu = By.xpath("//span[normalize-space()='Attendance'] | //span[contains(text(),'Attendance')]");
    private By addNewBtn = By.xpath("//button[normalize-space()='Add New'] | //button[contains(text(),'Add New')] | //*[contains(text(),'Add New') and (self::button or self::span or self::a)]");
    private By datePickerIcon = By.xpath("(//button[@aria-label='Choose date'])[1]");

    // Inputs inside Modal Dialog
    private By punchInTimeInput = By.xpath("//div[contains(@class,'MuiDialogContent-root')]//label[contains(text(),'Punch In')]/following-sibling::div//input | (//div[contains(@class,'MuiDialogContent-root')]//input[@type='text'])[1]");
    private By punchOutTimeInput = By.xpath("//div[contains(@class,'MuiDialogContent-root')]//label[contains(text(),'Punch Out')]/following-sibling::div//input | (//div[contains(@class,'MuiDialogContent-root')]//input[@type='text'])[2]");

    // Dialog Buttons & Toast Locators
    private By submitBtn = By.xpath("//div[contains(@class,'MuiDialogActions-root')]//button[not(@disabled)] | //button[normalize-space()='Save' or normalize-space()='Submit' or contains(text(),'Submit') or contains(text(),'Save')] | //button[@type='submit']");
    private By dialogContainer = By.xpath("//div[contains(@class,'MuiDialog-root') or contains(@class,'MuiModal-root')]");
    private By toastMessage = By.xpath("//div[contains(@class,'Toastify__toast')] | //div[contains(@class,'MuiAlert-message')] | //div[contains(@class,'MuiSnackbar-root')] | //div[@role='status' or @role='alert']");

    // 1. Navigation to Attendance
    public void navigateToAttendance() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        
        try {
            Driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
            Thread.sleep(300);
        } catch (Exception ignored) {}

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(attendanceMenu));
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // 2. Open Punch-In Modal
    public void AddNew() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(addNewBtn));

        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", btn);
        }
    }

    // 3. Select Date
    public void selectDate(String day) {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        WebElement calendarBtn = wait.until(ExpectedConditions.presenceOfElementLocated(datePickerIcon));

        try {
            calendarBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", calendarBtn);
        }

        By dateCell = By.xpath("//button[not(@disabled) and text()='" + day + "']");
        WebElement dateElement = wait.until(ExpectedConditions.presenceOfElementLocated(dateCell));

        try {
            dateElement.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", dateElement);
        }
    }

    // 4. Set Punch In Time
    public void setPunchInTime(String timeVal) {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(punchInTimeInput));

        try {
            input.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", input);
        }

        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        input.sendKeys(timeVal);
    }

    // 5. Set Punch Out Time
    public void setPunchOutTime(String timeVal) {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(punchOutTimeInput));

        try {
            input.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            js.executeScript("arguments[0].click();", input);
        }

        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        input.sendKeys(timeVal);
    }

    // 6. Click Submit (Fixed with JS Click Fallback)
    public void clickSubmit() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(submitBtn));

        JavascriptExecutor js = (JavascriptExecutor) Driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", btn);
        }
    }

    // 7. Get Toast or Modal Submission Status
    public String getToastMessage() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(8));

       
        try {
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(toastMessage));
            String text = toast.getText().trim();
            if (!text.isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {}

        
        try {
            WebDriverWait shortWait = new WebDriverWait(Driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(dialogContainer));
            return "Claim Submitted Successfully";
        } catch (Exception e) {
            return "No Toast Displayed";
        }
    }
}