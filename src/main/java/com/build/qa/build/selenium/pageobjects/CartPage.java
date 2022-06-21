package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends HomePage {
    By cartItems= By.cssSelector("data-automation='cart-item-description-link']>div");
    By expandCartTools= By.cssSelector("[data-automation=\"cart-tools-dropdown\"]");
    By emailCartLink= By.cssSelector("[data-automation=\"email-cart-button\"]");

    public CartPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
    //https://www.build.com

    public void navigateToCart(){
        driver.navigate().to(getBaseUrl()+"/checkout/cart");
    }

    public void viewCartTools(){
      click(getWebElement(expandCartTools));
    }

    public void openEmailCartModal(){
        click(getWebElement(emailCartLink));
    }

    public List<String> getCartItemNames(){
        return getWebElements(cartItems).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void emailCart(String fromName,String fromEmail,String toName, String toEmail, String otherEmailList,String message){
         new CartPage.EmailCart().sendEmailWith(fromName,fromEmail,toName,toEmail,otherEmailList,message);
    }
    public boolean isCartSentMessageDisplayed(){
       return new CartPage.EmailCart().isCartSentMessageDisplayed();
    }
    
    public  class EmailCart{

      By senderName= By.cssSelector("input[data-automation=\"your-name-input\"]");
      By senderEmail= By.cssSelector("[data-automation=\"your-email-input\"]");
      By recipientName= By.cssSelector("[data-automation=\"recipient-name-input\"]");
      By recipientEmail= By.cssSelector("[data-automation=\"recipient-email-input\"]");
      By othersEmails= By.cssSelector("[data-automation=\"other-recipients-input\"]");
      By sendButton= By.cssSelector("[data-automation=\"confirm-email-cart-button\"]");

      By msg=By.cssSelector("[data-automation=\"email-cart-message-textarea\"]");

      By carSentMsg=By.cssSelector("");
      public void sendEmailWith(String fromName,String fromEmail,String toName, String toEmail, String otherEmailList,String message){
          type(getWebElement(senderName),fromName);
          type(getWebElement(senderEmail),fromEmail);
          type(getWebElement(recipientName),toName);
          type(getWebElement(recipientEmail),toEmail);
          type(getWebElement(othersEmails),otherEmailList);
          type(getWebElement(msg),message);
          click(getWebElement(sendButton));
        }

        public boolean isCartSentMessageDisplayed(){
            return getWebElement(carSentMsg).getText().equalsIgnoreCase("Cart Sent");
        }
    }
}
