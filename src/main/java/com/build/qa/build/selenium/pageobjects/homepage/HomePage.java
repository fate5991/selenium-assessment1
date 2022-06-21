package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {

	private By buildThemeBody;
	private By welcomePopUp;

	private By searchTextBox, searchSubmitButton;
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
		welcomePopUp=By.cssSelector("[aria-label='close modal']");
		searchTextBox= By.cssSelector("[data-automation=\"search-txt\"]");
		searchSubmitButton=By.cssSelector("button.search-site-search");

		//,
	}

	public boolean onBuildTheme() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}


	public void closeWelcomePopUp() {
		click(getWebElement(welcomePopUp));
	}

	public void search(String searchTerm){
		type(getWebElement(searchTextBox),searchTerm);
		click(getWebElement(searchSubmitButton));
	}
}
