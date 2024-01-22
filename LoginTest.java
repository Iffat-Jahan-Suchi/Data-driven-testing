package firstTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	private WebDriver driver;
	@BeforeMethod
	public void launchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		this.driver=new ChromeDriver();
		this.driver.get("https://www.saucedemo.com/");
	}
	
	@Test(dataProviderClass=UserDataProvider.class,dataProvider="userData")
	public void login(String username,String password) throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("(//input[@id='password'])[1]")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		Thread.sleep(2000);
	}
	@AfterMethod
	public void after()
	
	{
		driver.close();
	}

}
