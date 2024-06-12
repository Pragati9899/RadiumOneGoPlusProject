package cubepay.com.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverImpl {

    public AndroidDriver setDesiredCapability() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("udid", "10BD611L2G000CU");
        options.setCapability("platformName", "Android");
        options.setCapability("platforVersion", "13.0");
        options.setCapability("appium:appPackage", "io.radiumone.goplus");
        options.setCapability("appium:appActivity", "io.radiumone.goplus.MainActivity");
        options.setCapability("automationName", "uiautomator2");
        options.setCapability("newCommandTimeout", 190);
       /* options.setCapability("noReset", "true");
        options.setCapability("fullReset", "false");*/

       // options.setCapability(, "10BD611L2G000CU");

        return new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);

         }
}
