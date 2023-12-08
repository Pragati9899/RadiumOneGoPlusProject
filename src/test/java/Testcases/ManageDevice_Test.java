package Testcases;

import PageObjects.MainScreenPage;
import PageObjects.SettingsPage;
import PageObjects.Sign_inPage;
import basePackage.BaseTestClass;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManageDevice_Test extends BaseTestClass {
    public AndroidDriver driver;

    @BeforeMethod
    public void initialiseTest() {
        this.driver = initialise();
    }

  //  @Test
    public void login() throws InterruptedException {
        Sign_inPage signInPage = new Sign_inPage(driver);

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
        //Thread.sleep(6000);

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
    public void TC_001_ManageDevices_AddNewDevice() {
        //----click on Setting Btn-----
        Sign_inPage signInPage = new Sign_inPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);

        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //---------Click setting button----------
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        //-----Add new device------------

        settingsPage.clickManageDevicesBtn();
        int count = settingsPage.countNoOfDevices();
        int countOfDevice = settingsPage.addNewDevice();

        //-----Assertion-----------
        Assert.assertEquals(count + 1, countOfDevice);
    }
}
