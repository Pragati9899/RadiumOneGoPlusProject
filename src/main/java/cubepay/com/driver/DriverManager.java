package cubepay.com.driver;
import io.appium.java_client.android.AndroidDriver;

public final class DriverManager {

    private DriverManager(){}

    private static final ThreadLocal<AndroidDriver> threadLocalDriver = new ThreadLocal<>();

    public static AndroidDriver getDriver(){
        return threadLocalDriver.get();
    }
    public static void setDriver(AndroidDriver driver){
        threadLocalDriver.set(driver);
    }

    public static void unload(){
        threadLocalDriver.remove();
    }



}