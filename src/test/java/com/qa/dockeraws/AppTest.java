package com.qa.dockeraws;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	static WebDriver driver;


	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "chrome");
			try {
				driver = new RemoteWebDriver(new URL("ec2-3-145-193-133.us-east-2.compute.amazonaws.com:4444/wd/hub"),cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "firefox");
			try {
				driver = new RemoteWebDriver(new URL("ec2-3-145-193-133.us-east-2.compute.amazonaws.com:4444/wd/hub"),cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
	}
	
	
    @Test
    public void getTitleTest() {
    	System.out.println(driver.getTitle());
    }
    
    @Test
    public void getFooterLinks() {
    	List<WebElement> footer = driver.findElements(By.xpath("//table[@class='navFooterMoreOnAmazon']/tbody/tr/td/a"));
    	footer.forEach(e->System.out.println(e.getText()));
    	
    }
    
    @AfterMethod
    public void tearDown() {
    	driver.quit();
    }
   
}
