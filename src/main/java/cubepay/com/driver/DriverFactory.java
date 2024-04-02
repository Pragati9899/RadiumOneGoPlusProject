package cubepay.com.driver;

import cubepay.com.enums.ConfigProperties;
import cubepay.com.enums.DriverModes;
import cubepay.com.utilities.PropertyUtils;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;

public class DriverFactory {
    private DriverFactory() {
    }
  private static AndroidDriver driver ;
    public static AndroidDriver get(DriverModes modes) throws MalformedURLException {

        if (modes.equals(DriverModes.ANDROID_MOBILE)) {
            driver = new MobileDriverImpl().setDesiredCapability();
        } else if (modes.equals(DriverModes.ANDROID_EMULATOR_MOBILE)) {
            driver = new EmulatorDriverImpl().setDesiredCapability();
        } else if (modes.equals(DriverModes.ANDROID_TAB)) {
            driver = new TabDriverImpl().setDesiredCapability();
        }
        return driver;

    }

}
