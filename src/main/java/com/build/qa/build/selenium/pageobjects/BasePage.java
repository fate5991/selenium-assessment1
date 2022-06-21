package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.framework.BaseFramework;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage extends BaseFramework {

	public Wait wait;
	public String baseUrl=getConfiguration("HOMEPAGE");
	public BasePage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;

		this.wait = wait;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
	}
	public WebElement getWebElement(By by){
		waitForElementPresence(by);
		return driver.findElement(by);
	}
	public void waitForElementPresence(By element){
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(element,0));
	}

	public void waitForElementVisibility(WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public List<WebElement> getWebElements(By by){
		return driver.findElements(by);
	}

	public void click(WebElement element){
		waitForElementVisibility(element);
		element.click();
	}

	public void type(WebElement element,String text){
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}
	public static void waitFor(long time){
		try {
			Thread.sleep(time);
		}
		catch (Exception e){}
	}

	public String getBaseUrl() {
		return baseUrl;
	}
}
