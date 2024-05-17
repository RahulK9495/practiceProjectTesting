package TestSuit;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.ProductCatalogue;
import test.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test
	public void SubmitOrder() throws Exception
	{	
		String ProductName= "ZARA COAT 3";
				
		ProductCatalogue productCatalogpage=landingpage.loginApplication("rahulhot94@gmail.com", "Rahul@94");
		
		System.out.println(landingpage.getErrorMessage());
		
		
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
					
		
	}
	@Test
	public void productErrorValidation() throws Exception
	{	
		String ProductName= "ZARA COAT 3";
				
		ProductCatalogue productCatalogpage=landingpage.loginApplication("rahulkhot94@gmail.com", "Rahul@94");
		
		List<WebElement> products=productCatalogpage.getProductList();
		productCatalogpage.addProductToCart(ProductName);
		CartPage cartpage= productCatalogpage.goToCartPage();
		
		Boolean match=cartpage.VerifyProductDisplay(ProductName);
		Assert.assertFalse(match);
		
		
	}

}
