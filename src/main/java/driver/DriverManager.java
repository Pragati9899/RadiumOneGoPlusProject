package driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    public static ThreadLocal<AppiumDriver> dr = new ThreadLocal<AppiumDriver>();
    public static ThreadLocal<Boolean> isAndroid = new ThreadLocal<Boolean>();

    public static AppiumDriver getDriver() {

        return dr.get();

    }

    public static void setAppiumDriver(AppiumDriver driver) {

        dr.set(driver);
    }

    public static Boolean getIsAndroid() {

        return isAndroid.get();

    }

    public static void setIsAndroid(Boolean value) {

        isAndroid.set(value);
    }


}