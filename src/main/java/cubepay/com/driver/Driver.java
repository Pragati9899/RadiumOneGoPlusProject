package cubepay.com.driver;

import cubepay.com.enums.ConfigProperties;
import cubepay.com.enums.DriverModes;
import cubepay.com.utilities.PropertyUtils;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.util.Objects;

import static cubepay.com.driver.DriverManager.*;


public class Driver {
    private Driver() {
    }

    private static AndroidDriver driver = null;
    public static void initDriver() throws MalformedURLException {
        if (Objects.isNull(getDriver())) {
            String modevalue = PropertyUtils.getValue(ConfigProperties.MODE);
             driver = DriverFactory.get(DriverModes.valueOf(modevalue.toUpperCase()));
            setDriver(driver);
        }
    }

    public static void quitDriver() {
        if (Objects.nonNull(getDriver())) {
            getDriver().quit();
            unload();
        }
    }


}
