package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObject.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initilizeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "C:\\Rahul\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		 driver=new ChromeDriver();
		
	
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		
		driver= initilizeDriver();
		landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;	
	}
	
	@AfterMethod
	public void TearDown()
	{
		driver.close();
	}

}
