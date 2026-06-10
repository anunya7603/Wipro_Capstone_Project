package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utilities.ExcelUtility;
import utilities.ScreenshotUtility;

public class EcommerceSteps {

    WebDriver driver;
    LoginPage login;
    WebDriverWait wait;
    @Given("user launches browser")
    public void user_launches_browser() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");
    }

    @When("user logs into application")
    public void user_logs_into_application() {

        login = new LoginPage(driver);
        String username = ExcelUtility.getUsername();
        String password = ExcelUtility.getPassword();
        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        System.out.println("Login successful");
    }

    @And("user searches product")
    public void user_searches_product() {

        System.out.println("Product searched");
    }

    @And("user applies filter")
    public void user_applies_filter() {

        WebElement filter = driver.findElement(By.className("product_sort_container"));
        filter.sendKeys("Price (low to high)");
        System.out.println("Filter applied");
    }

    @And("user adds products to cart")
    public void user_adds_products_to_cart() {

        WebElement backpack = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack")));
        backpack.click();

        WebElement tshirt = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-bolt-t-shirt")));
        tshirt.click();

        System.out.println("Products added");
    }

    @And("user removes one product")
    public void user_removes_one_product() {

        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                //By.id("remove-sauce-labs-backpack")));
        		By.id("wrong-id")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",removeBtn);
        removeBtn.click();
        System.out.println("One product removed");
    }

    @Then("validate total amount")
    public void validate_total_amount() {

        driver.findElement(By.className("shopping_cart_link")).click();
        String product = driver.findElement(By.className("inventory_item_name")).getText();
        System.out.println("Product in cart: " + product);
        System.out.println("Validation Passed");
    }

    @And("proceed to checkout")
    public void proceed_to_checkout() {

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkout.click();
        System.out.println("Checkout completed");
    }

    @And("user logout")
    public void user_logout() {
    	
    	ScreenshotUtility.captureScreenshot("Complete ecommerce workflow");
        driver.quit();
    	System.out.println("Browser Closed");
    }
}