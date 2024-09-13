package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	//@Test(enabled = false)
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	//@Test
	public void ValidationLoginError() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		landingPage.loginApplication("anshika@gmail.com", "Iamking00");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
	}
	
	@Test
	public void ValidationProductError() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("trupti@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		//System.out.println(match);
		Assert.assertFalse(match);
	}

}
