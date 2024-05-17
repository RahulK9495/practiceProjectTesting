package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	//@FindBy(xpath="//div[@class=\\\"card-body\\\"]/button[2]")
	//WebElement addToCart;
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToappear(productsBy);
		return products;
	}
	
	public WebElement getProductByname(String ProductName)
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws Exception
	{
		WebElement prod = getProductByname(productName);
		prod.findElement(addToCart).click();
		waitForElementToappear(toastMessage);
		waitForElementTodisappear(spinner);
		
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		return new CartPage(driver) ;
		
	}

	

}
