package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
//Page Object class for Home Page
public class HomePage {

    WebDriver driver;
    // Constructor
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }
    // Locators
    By fromCity = By.name("fromPort");
    By toCity = By.name("toPort");
    By findFlights = By.xpath("//input[@value='Find Flights']");
    // Select departure city from dropdown
    public void selectDepartureCity(String city)
    {
        Select select = new Select(driver.findElement(fromCity));
        select.selectByVisibleText(city);
    }
    // Select destination city from dropdown
    public void selectDestinationCity(String city)
    {
        Select select = new Select(driver.findElement(toCity));
        select.selectByVisibleText(city);
    }
    // Click Find Flights button
    public void clickFindFlights()
    {
        driver.findElement(findFlights).click();
    }
}
