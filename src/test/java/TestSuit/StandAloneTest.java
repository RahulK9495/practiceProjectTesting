package TestSuit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.OrderPage;
import pageObject.ProductCatalogue;
import test.BaseTest;

public class StandAloneTest extends BaseTest {

	String ProductName= "ZARA COAT 3";
	
	
	@Test(dataProvider="getData",groups={"purchase"})
	public void SubmitOrder(HashMap <String,String> input) throws Exception
	{	
		
				
		ProductCatalogue productCatalogpage=landingpage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalogpage.getProductList();
		productCatalogpage.addProductToCart(input.get("ProductName"));
		CartPage cartpage= productCatalogpage.goToCartPage();
		
		Boolean match=cartpage.VerifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage= cartpage.goToCheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmpage=checkoutPage.submitOrder();
		
				
		String confMsg=confirmpage.verifyConfirmMessage();
		Assert.assertTrue(confMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
					
		
	}
	
	
	@Test(dependsOnMethods={"SubmitOrder"},enabled=false)
	
	public void verifyOrder()
	{
		ProductCatalogue productCatalogpage=landingpage.loginApplication("rahulkhot94@gmail.com", "Rahul@94");
		OrderPage orderPage=productCatalogpage.goToOrderPage();
		Boolean match=orderPage.VerifyOrder(ProductName);
		Assert.assertTrue(match);
		
	}
	
	@DataProvider

	public Object[][] getData()
	{
		HashMap <String,String> map=new HashMap <String,String>();
		
		map.put("email", "rahulkhot94@gmail.com");
		map.put("password", "Rahul@94");
		map.put("ProductName", "ZARA COAT 3");
		
		HashMap <String,String> map2=new HashMap <String,String>();
		
		map2.put("email", "varadkhot@gmail.com");
		map2.put("password", "Varad@123");
		map2.put("ProductName", "ADIDAS ORIGINAL");
		
		
		return new Object[][]  {{map },{map2}};
	}
	
	public String getScreenShot(String testcaseName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	

}
