package cubepay.com.driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class EmulatorDriverImpl {

    public AndroidDriver setDesiredCapability() throws MalformedURLException {

       // UiAutomator2Options options = new UiAutomator2Options();

       /* options.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        options.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        options.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        options.setCapability("appium:appPackage", "io.radiumone.goplus");
        options.setCapability("appium:appActivity", "io.radiumone.goplus.MainActivity");
        options.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        options.setCapability(MobileCapabilityType.NO_RESET, "true");
        options.setCapability(MobileCapabilityType.FULL_RESET, "false");*/

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("udid","emulator-5554");
        dc.setCapability("platformName","Android");
        dc.setCapability("platformVersion","13.0");
        dc.setCapability("appium:appPackage", "io.radiumone.goplus");
        dc.setCapability("appium:appActivity", "io.radiumone.goplus.MainActivity");
        dc.setCapability("automationName","uiautomator2");
        dc.setCapability("noReset","true");
        dc.setCapability("fullReset","false");




        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), dc);
    }
}
