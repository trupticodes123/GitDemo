package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	//String productName = "ZARA COAT 3";
	/*
	 * @Test(dataProvider = "getData", groups = {"Purchase"}) public void
	 * submitOrderTest(String email, String password, String productName) throws
	 * IOException, InterruptedException { // TODO Auto-generated method stub String
	 * countryName = "india"; ProductCatalogue productCatalogue =
	 * landingPage.loginApplication(email, password); List<WebElement> products =
	 * productCatalogue.getProductList(); productCatalogue.addToCart(productName);
	 * CartPage cartPage = productCatalogue.goToCartPage(); Boolean match =
	 * cartPage.VerifyProductDisplay(productName); Assert.assertTrue(match);
	 * CheckOutPage checkoutPage = cartPage.checkoutBtn();
	 * checkoutPage.selectCountry(countryName); ConfirmationPage confirmationPage =
	 * checkoutPage.submitOrder(); String confirmMessage =
	 * confirmationPage.getConfirmationMessage();
	 * Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."))
	 * ; }
	 * 
	 * @DataProvider public Object[][] getData() { return new Object[] []
	 * {{"trupti@gmail.com","Iamking@000","ZARA COAT 3"},{"anshika@gmail.com",
	 * "Iamking@000","IPHONE 13 PRO"}}; }
	 */
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String countryName = "india";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Thread.sleep(3000);
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.checkoutBtn();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
		
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data1 = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\Data\\PurchaseOrder.json");
		
		return new Object[] [] {{data1.get(0)},{data1.get(1)}};
	}
	
	
	/*
	 * @DataProvider public Object[][] getData() { HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("email", "trupti@gmail.com");
	 * map.put("password", "Iamking@000"); map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "anshika@gmail.com"); map1.put("password", "Iamking@000");
	 * map1.put("productName", "IPHONE 13 PRO");
	 * 
	 * return new Object[] [] {{map},{map1}}; }
	 */
	@Test(dependsOnMethods="submitOrderTest",retryAnalyzer = Retry.class)
	public void OrderHistoryTest()
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("trupti@gmail.com", "Iamking@000");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Boolean orderMatch = ordersPage.VerifyOrders(productName);
		Assert.assertTrue(orderMatch);
	}
}
