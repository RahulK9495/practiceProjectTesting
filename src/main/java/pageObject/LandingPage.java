package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{	super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordele;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordele.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogpage=new ProductCatalogue(driver);
		return productCatalogpage;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToappear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
