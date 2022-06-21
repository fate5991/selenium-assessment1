package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class ProductDetailsPage extends HomePage {
    By addToCart=By.cssSelector("button[data-automation=\"add-to-cart-button\"]");
    By compareButton=By.cssSelector("button[data-automation=\"compare-button\"]");
    By proceedToCart=By.cssSelector("[data-automation=\"proceed-to-cart-button\"]");
    public ProductDetailsPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
    public void waitForPdpPageToLoad(){
       waitForElementVisibility(getWebElement(compareButton));
    }
    public void addToCart(){
        click(getWebElement(addToCart));
        waitForProceedToCartToAppear();
    }
    public void waitForProceedToCartToAppear(){
        waitForElementVisibility(getWebElement(proceedToCart));
    }
    public void proceedToCart(){
        click(getWebElement(proceedToCart));
    }
}
