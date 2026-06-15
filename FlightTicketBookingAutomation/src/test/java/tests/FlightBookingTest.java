package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;
import utilities.ExcelUtils;
import utilities.ScreenshotUtil;

public class FlightBookingTest {

    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setup() {
    	// Browser setup before every test execution
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void pause() {
        try {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // End-to-End Flight Booking Test
    @Test
    public void bookFlight() throws Exception {

    	// Launch application
        System.out.println("Application Launched Successfully");
        driver.get("https://blazedemo.com");
        
        // Wait for page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='fromPort']")));
        
        // Read test data from Excel
        String fromCity = ExcelUtils.getCellData(1, 0);
        String toCity = ExcelUtils.getCellData(1, 1);	
        String name = ExcelUtils.getCellData(1, 2);
        String address = ExcelUtils.getCellData(1, 3);
        String city = ExcelUtils.getCellData(1, 4);
        String state = ExcelUtils.getCellData(1, 5);
        String zip = ExcelUtils.getCellData(1, 6);
        String cardNumber = ExcelUtils.getCellData(1, 7);
        String month = ExcelUtils.getCellData(1, 8);
        String year = ExcelUtils.getCellData(1, 9);
        String cardHolder = ExcelUtils.getCellData(1, 10);

        HomePage home = new HomePage(driver);

        // Select cities and search flights
        home.selectDepartureCity(fromCity);
        System.out.println("Departure City Selected");

        home.selectDestinationCity(toCity);
        System.out.println("Destination City Selected");
        
        // Verify flights are available
        home.clickFindFlights();
        System.out.println("Find Flights Clicked");
        
        // Wait until flight table is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table']")));

        ReservePage reserve = new ReservePage(driver);

        int flights = reserve.getFlightCount();
        System.out.println("Flights Available : " + flights);

        Assert.assertTrue(flights > 0);
        reserve.selectFlight();
        System.out.println("Flight Selected");
        
        // Wait until Purchase Page loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputName")));
        
        System.out.println("Purchase Page Displayed");

        PurchasePage purchase = new PurchasePage(driver);

        // Enter passenger details
        purchase.enterPassengerDetails(name, address, city, state, zip);
        System.out.println("Passenger Details Entered");
        
        // Enter payment details
        purchase.enterPaymentDetails(cardNumber, month, year, cardHolder);
        System.out.println("Payment Details Entered");

        // Complete booking
        purchase.clickPurchaseFlight();
        System.out.println("Purchase Flight Clicked");
        
        // Wait for Confirmation Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Thank you')]")));

        // Validate confirmation message
        ConfirmationPage confirm = new ConfirmationPage(driver);
        String message = confirm.getConfirmationMessage();

        Assert.assertEquals(message, "Thank you for your purchase today!");
        System.out.println("Booking Successful");
        System.out.println("Confirmation Message : " + message);

        // Capture screenshot
        String screenshot = ScreenshotUtil.captureScreenshot(driver, "Booking_Success");
        System.out.println("Screenshot Saved : " + screenshot);
    }

    // Close browser after execution
    @AfterMethod
    public void tearDown() {
        if(driver != null)
        {
            driver.quit();
            System.out.println("Browser Closed Successfully");
        }
    }
}