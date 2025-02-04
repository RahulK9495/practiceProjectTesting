package TestSuit;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pageObject.LandingPage;

public class originaltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Rahul\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		
		//ChromeOptions op=new ChromeOptions();
		//op.addArguments("--remote-allow-origins");
		String ProductName= "ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage lp=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("rahulkhot94@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@94");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));	
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		for (WebElement product : products)
		{
			if(product.findElement(By.cssSelector("b")).getText().equals(ProductName))
			{
				driver.findElement(By.xpath("//div[@class=\"card-body\"]/button[2]")).click();
				break;
			}
			else
			{
				System.out.println("Product not found");
			}
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();
		
		List <WebElement> CartProducts=driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));
		boolean match=CartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//li[@class=\"totalRow\"]/button")).click();
		
		WebElement selectCountry=driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]"));
		
		Actions a=new Actions(driver);
		a.sendKeys(selectCountry, "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
				
		
	}

}
