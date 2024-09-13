package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> ordersNames;
	
	
	
	public Boolean VerifyOrders(String productName)
	{
		Boolean orderMatch = ordersNames.stream().anyMatch(orderName -> orderName.getText().equalsIgnoreCase(productName));
		return orderMatch;
	}
	
}
