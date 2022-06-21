package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;

public class CategoryPage extends HomePage {

    //,
    //,

    By priceFacet=By.cssSelector("div[data-automation=\"price-facet\"]>div");
    By priceMin=By.cssSelector("input[data-automation=\"range-minimum\"]");
    By priceMax=By.cssSelector("input[data-automation=\"range-maximum\"]");
    By applyPriceFacet=By.cssSelector("div[data-automation=\"range-apply\"]>button");

    By resultCount= By.cssSelector("data-automation=\"results-count\"");
    By compareButton=By.cssSelector("button[data-automation=\"compare-button\"]");
    
    By finishing=By.cssSelector("div[role=\"tabpanel\"][class=\"db\"]>div>div>div:nth-child(2)");
    
    By filterByTheme=By.cssSelector("div[data-automation=\"theme-facet\"]");
    
    By themeSubFilters_Modern=By.cssSelector("div[data-automation=\"theme-facet\"]+ul>div");
    
    public CategoryPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
   public void navigateToCategory(String categoryPath){
        driver.navigate().to(getBaseUrl()+categoryPath);
    }
    public void viewProductDetails(int index){
        By productDesc=By.cssSelector("a[data-automation=\"product-card-description-link\"]");
        click(getWebElements(productDesc).get(index));
    }

    public String getProductName(int index){
        By productDesc=By.cssSelector("a[data-automation=\"product-card-description-link\"]");
        return getWebElements(productDesc).get(index).getText();
    }
    public void filterByPrice(String minPrice,String maxPrice){
        click(getWebElement(priceFacet));
        type(getWebElement(priceMin),minPrice);
        type(getWebElement(priceMax),maxPrice);
        click(getWebElement(applyPriceFacet));
    }
    
    
    public void filterByFinishingType(String finishingType){
    	List<WebElement> finishTypeWebElements=getWebElements(finishing);
    	 List<String> finishingTypeNames=finishTypeWebElements.stream().map(WebElement::getText).collect(Collectors.toList());
    	 int index=finishingTypeNames.indexOf(finishingType);
    	 click( finishTypeWebElements.get(index));
    	
    }
    
    
    public void applySubFilter_Modern(){
    	click(getWebElement(themeSubFilters_Modern));
    }
    
    public void filterByTheme(){
    	click(getWebElement(filterByTheme));
    }
    public int getResultCount(){
        return Integer.valueOf(getWebElement(resultCount).getText());
    }

    
    public int getDisplayedProductCount(){
        return getWebElements(compareButton).size();
    }
    By nextPageButton=By.cssSelector("[data-automation=\"next-page-button\"]");
    
    public void navigateToNextPage() {
    	click(getWebElement(nextPageButton));
    	while(getDisplayedProductCount()<0) {
    		//wait for products to display
    	}
    }
    
    public int navigateToAllPagesAndGetProductCount() {
    	
    	int count=getDisplayedProductCount();
    	while(getWebElement(nextPageButton).isEnabled()) {
    		getWebElement(nextPageButton);
    		while(getDisplayedProductCount()<0) {
        		//wait for products to display
        	}
    		count=count+getDisplayedProductCount();
    	}
    	
    	return count;
    	
    	
    }
    
    
}
