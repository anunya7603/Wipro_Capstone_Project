package pages;

import org.openqa.selenium.*;

//Page Object class for Booking Confirmation Page
public class ConfirmationPage {

    WebDriver driver;
    // Constructor to initialize WebDriver
    public ConfirmationPage(WebDriver driver) 
    {
        this.driver = driver;
    }
    // Locator for booking confirmation message
    By confirmationMessage = By.tagName("h1");
    // Returns confirmation message displayed after booking
    public String getConfirmationMessage() 
    {
        return driver.findElement(confirmationMessage).getText();
    }
}