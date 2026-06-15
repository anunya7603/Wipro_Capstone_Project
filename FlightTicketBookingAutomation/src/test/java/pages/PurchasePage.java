package pages;

import org.openqa.selenium.*;
//Page Object class for Purchase Flight Page
public class PurchasePage {

    WebDriver driver;
    public PurchasePage(WebDriver driver)
    {
        this.driver = driver;
    }
    // Passenger Information Locators
    By name = By.id("inputName");
    By address = By.id("address");
    By city = By.id("city");
    By state = By.id("state");
    By zipCode = By.id("zipCode");
    // Payment Information Locators
    By cardNumber = By.id("creditCardNumber");
    By cardMonth = By.id("creditCardMonth");
    By cardYear = By.id("creditCardYear");
    By nameOnCard = By.id("nameOnCard");
    // Purchase button locator
    By purchaseButton = By.xpath("//input[@value='Purchase Flight']");
    
    // Enter passenger details
    public void enterPassengerDetails(String pname, String paddress, String pcity, String pstate, String pzip) 
    {
        driver.findElement(name).sendKeys(pname);
        driver.findElement(address).sendKeys(paddress);
        driver.findElement(city).sendKeys(pcity);
        driver.findElement(state).sendKeys(pstate);
        driver.findElement(zipCode).sendKeys(pzip);
    }
    // Enter payment details
    public void enterPaymentDetails(String cardNo, String month, String year, String cardHolder) 
    {
        driver.findElement(cardNumber).clear();
        driver.findElement(cardNumber).sendKeys(cardNo);

        driver.findElement(cardMonth).clear();
        driver.findElement(cardMonth).sendKeys(month);

        driver.findElement(cardYear).clear();
        driver.findElement(cardYear).sendKeys(year);

        driver.findElement(nameOnCard).sendKeys(cardHolder);
    }
    // Click Purchase Flight button
    public void clickPurchaseFlight() {
        driver.findElement(purchaseButton).click();
    }
}