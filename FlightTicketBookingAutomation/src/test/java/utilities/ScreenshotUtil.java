package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

// Utility class for screenshot capture
public class ScreenshotUtil {

    // Captures screenshot and returns saved path
    public static String captureScreenshot(
            WebDriver driver,
            String fileName) throws Exception {

        // Generate unique timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        // Screenshot file path
        String path = System.getProperty("user.dir")
                        + "\\screenshots\\"
                        + fileName + "_"
                        + timestamp + ".png";

        // Capture screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Save screenshot
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
