package com.training.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.training.Utilities.ExcelReader;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExcelReader excelReader;
	
	public BaseTest()
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/training"
					+ "/Config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	@BeforeMethod
	public void setUp()
	{
		browserSetUp();
	}
	
	@AfterMethod
	public void TarDown()
	{
		driver.quit();
	}
	
	public static void browserSetUp(){
			String browserName = prop.getProperty("browser");
			
			if(browserName.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/test/resources/Drivers/chromedriver.exe");	
				driver = new ChromeDriver(); 
			}
			else if(browserName.equals("FF")){
				System.setProperty("webdriver.gecko.driver", "geckodriver");	
				driver = new FirefoxDriver(); 
			}
			driver.manage().window().maximize();
			//driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
				}

}
