package utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    public static void captureScreenshot(String fileName) {

        try {
            File src = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("./screenshots/" + fileName + ".png");
            FileUtils.copyFile(src,dest);
            System.out.println("Screenshot captured successfully");
        } catch (Exception e) {
            System.out.println("Screenshot failed");
        }
    }
}