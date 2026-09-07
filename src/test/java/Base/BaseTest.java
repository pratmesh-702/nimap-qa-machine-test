package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public static WebDriver Driver;

	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver();
		Driver.get("https://test.fieldforceconnect.com/auth/login");
		Driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void teardown()
	{
		if(Driver != null)
		{
			Driver.quit();
		}
	}

}
