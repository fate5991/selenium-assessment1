package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.CartPage;
import com.build.qa.build.selenium.pageobjects.CategoryPage;
import com.build.qa.build.selenium.pageobjects.ProductDetailsPage;
import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BuildTest extends BaseFramework {

	HomePage homePage;
	CartPage cartPage;
	ProductDetailsPage productDetailsPage;
	CategoryPage categoryPage;

	@Before
	public void setup1(){

		 homePage = new HomePage(driver, wait);
		 cartPage= new CartPage(driver, wait);
		 categoryPage=new CategoryPage(driver,wait);
		 productDetailsPage= new ProductDetailsPage(driver, wait);
	}
	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test @Ignore
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));

		homePage.closeWelcomePopUp();
		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		// TODO: Implement this test
	
		driver.navigate().to(getConfiguration("HOMEPAGE")+"/search?term=Quoizel"+"MY1613");
		homePage.closeWelcomePopUp();
		
		boolean state = false;
		
		if (driver.findElement(By.xpath("//h1[@class='fw7']")) != null) {
			System.out.println("searchForProductLandsOnCorrectProduct");
			
		}
		
		else {
			
			System.out.println("searchForProductDoesn'tLandOnCorrectProduct");
		}
		
	}

	/**
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {

		int productIndex=1;
		driver.navigate().to(getConfiguration("HOMEPAGE")+"/bathroom-sinks/c108504");
		homePage.closeWelcomePopUp();
		String productName=categoryPage.getProductName(productIndex);// actual product in the  second index
		categoryPage.viewProductDetails(productIndex);// take s to PDP page
		productDetailsPage.addToCart();
		productDetailsPage.proceedToCart();
		List<String> productsInCart=cartPage.getCartItemNames(); // get all product names prsent in cart
		assertThat(productsInCart.indexOf(productName)!=-1).isTrue(); 
	}

	/**
	 * Add a product to the cart and email the cart to yourself, also to my email address: test.automation+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() {

		driver.get(getConfiguration("HOMEPAGE")+"/product/s1235218?uid=2955969");
		homePage.closeWelcomePopUp();
		productDetailsPage.waitForPdpPageToLoad();
		productDetailsPage.addToCart();
		productDetailsPage.proceedToCart();
		cartPage.viewCartTools();
		cartPage.openEmailCartModal();
		cartPage.emailCart("test","begumfatema5991@gmailcom","test","test2@gmailcom","test.automation+SeleniumTest@build.com","This is Fatema, sending you a cart from my automation!");
		assertThat(cartPage.isCartSentMessageDisplayed()).isTrue();

	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		// TODO: Implement this test
		//https://www.build.com/single-hole-sink-faucet/c109954
		
		driver.get(getConfiguration("HOMEPAGE")+"/single-hole-sink-faucet/c109954");
		homePage.closeWelcomePopUp();
		String finishing="Chromes";
		
		categoryPage.filterByFinishingType(finishing);
		
		categoryPage.filterByTheme();
		
		categoryPage.applySubFilter_Modern();
		
		String minPrice="3500";
		String maxPrice="3937";
		
		categoryPage.filterByPrice(minPrice,maxPrice);
		
		int resultCount=categoryPage.getResultCount();
		
		int displayedProductCount=categoryPage.navigateToAllPagesAndGetProductCount();

		assertThat(displayedProductCount==resultCount).isTrue();
		
	}
}
