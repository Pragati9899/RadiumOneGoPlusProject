package Testcases;

import PageObjects.Sign_inPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Testclass {
    @Test
    public void TC_001Sign_InTest() throws InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid("emulator-5554");
        options.setCapability("platformName", "Android");
        options.setCapability("appium:platformVersion", "13.0");
        options.setCapability("appium:appPackage", "io.radiumone.goplus");
        options.setCapability("appium:appActivity", "io.radiumone.goplus.MainActivity");
        options.setCapability("appium:automationName", "uiautomator2");
        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(new URL("http://192.168.0.58:4723"),options);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Sign_inPage signInPage = new Sign_inPage(driver);

     /*   signInPage.clickOnCountryCode();
        Thread.sleep(2000);*/
        signInPage.selectCountryCode("Japan");

    }
}
