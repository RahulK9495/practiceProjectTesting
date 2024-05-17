package pageObject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	By results = By.cssSelector(".ta-results");
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//input[@placeholder=\"Select Country\"]")
	WebElement selectCountry;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement Country;

	public void selectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(selectCountry, countryName).build().perform();
		
		waitForElementToappear(results);
		
		Country.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
