/*
package Testcases;

import cubepay.com.pageObjects.MainScreenPage;
import cubepay.com.pageObjects.SettingsPage;
import cubepay.com.pageObjects.Login;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManageUsers_Test extends BaseTestClass {
    //Assertions are left
    // need to find locators

    public AndroidDriver driver;

    @BeforeClass
    public void initialiseTest() {
        this.driver = initialise();
    }

    @Test
    public void login() throws InterruptedException {
        Login signInPage = new Login(driver);

        //check if permission diauloges are displayed or not
        if (signInPage.permissionpop_upIsDisplayed()) {
            if (rb.getString("PermissionDialogue").equals("While using the app")) {
                signInPage.AcceptThePop_up1();
                signInPage.AcceptThePop_up1();
                signInPage.AcceptThePop_up1();
            } else if (rb.getString("PermissionDialogue").equals("Only this time")) {
                signInPage.AcceptThePop_up2();
                signInPage.AcceptThePop_up2();
                signInPage.AcceptThePop_up2();
            } else if (rb.getString("PermissionDialogue").equals("Don’t allow")) {
                signInPage.AcceptThePop_up3();
                signInPage.AcceptThePop_up3();
                signInPage.AcceptThePop_up3();
            }
        }
        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(6000);

        //enter verification code
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password1"));
        signInPage.clickSubmitBtn();
    }

    @Test
    public void TC_001_ManageUsers_AddNewUser() {

        Login signInPage = new Login(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //-----Add new user------------
        mainScreenPage.clickSettingsBtn();
        settingsPage.addNewUser("User", "device-1", "98234564", "ab cd");

    }

    @Test
    public void TC_002_ManageUsers_EditUser() {

        Login signInPage = new Login(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        //-----Add new user------------
        mainScreenPage.clickSettingsBtn();
        settingsPage.editUser("Manager", "93778444", "adf kl");

    }


}
*/
