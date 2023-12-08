package basePackage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class BaseTestClass {

    public AndroidDriver driver;
    public ResourceBundle rb;

    public UiAutomator2Options setDesiredCapability() throws IOException {
        rb = ResourceBundle.getBundle("config");
        UiAutomator2Options options = new UiAutomator2Options();
        // options.setUdid("emulator-5554");
        options.setUdid("10BD611L2G000CU");
        // options.setUdid("22e4fdbb");
        options.setCapability("platformName", "Android");
        options.setCapability("appium:platformVersion", "13.0");
        options.setCapability("appium:appPackage", "io.radiumone.goplus");
        options.setCapability("appium:appActivity", "io.radiumone.goplus.MainActivity");
        options.setCapability("appium:automationName", "uiautomator2");
         options.setCapability("appium:noReset", "true");
        options.setCapability("appium:fullReset", "false");
        return options;
    }

    public AndroidDriver initialise() {
        try {
            this.driver = new AndroidDriver(new URL("http://192.168.0.58:4723/"), setDesiredCapability());

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return driver;
    }

    public File takeScreenshot(AndroidDriver mobileDriver) throws IOException {
        File source = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot.png");
        FileUtils.copyFile(source, destinationFile);
        return destinationFile;
    }


}

