package pages;

import java.util.List;
import org.openqa.selenium.*;

// Page Object class for Flight Search Results Page
public class ReservePage {

    WebDriver driver;
    // Constructor
    public ReservePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Locator for available flights table rows
    By flights = By.xpath("//table/tbody/tr");

    // Locator for first available flight
    By chooseFlight = By.xpath("(//input[@value='Choose This Flight'])[1]");

    // Returns number of flights available
    public int getFlightCount()
    {
        List<WebElement> rows = driver.findElements(flights);
        return rows.size();
    }

    // Select first available flight
    public void selectFlight() 
    {
        driver.findElement(chooseFlight).click();
    }
}
