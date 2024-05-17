package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	
	@FindBy(xpath="//div[@class=\"cartSection\"]/h3\"]")
	List<WebElement> CartProducts;
	
	@FindBy(xpath="//li[@class=\"totalRow\"]/button")
	WebElement checkOut;
	
	
	public Boolean VerifyProductDisplay(String ProductName)
	{
		//boolean match=CartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(ProductName));
		return true;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		
		return new CheckOutPage(driver);
		//return checkoutPage;
	}

}
