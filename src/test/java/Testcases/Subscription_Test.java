package Testcases;
import static org.assertj.core.api.Assertions.*;
import PageObjects.MainScreenPage;
import PageObjects.SettingsPage;
import PageObjects.Sign_inPage;
import basePackage.BaseTestClass;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Subscription_Test extends BaseTestClass {
    //Assertion of cancel subscription is left

    public AndroidDriver driver;
    @BeforeClass
    public void initialiseTest(){
        this.driver=initialise();
    }

   // @Test
    public void login() throws InterruptedException {
        Sign_inPage signInPage = new Sign_inPage(driver);

        //check if permission diauloges are displayed or not
        if(signInPage.permissionpop_upIsDisplayed()) {
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
        String[] a = {"1","2","3","4","5","6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password1"));
        signInPage.clickSubmitBtn();
    }

    @Test
    public void TC_001_Subscription_PurchaseSubscription() throws InterruptedException {
        Sign_inPage signInPage = new Sign_inPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //---------Click setting button----------
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        settingsPage.clickSubscriptionSetting();
        //---------Verify Cancel subs is displayed----
        if(!settingsPage.verifyCancelSubsBtnIsDisplayed()) {

            //----Buy subscription------------
            settingsPage.setDevices("12");
            settingsPage.setUsers("15");
            settingsPage.setEReceiptToggleBtn();
            settingsPage.setCarePlusToggleBtn();
            settingsPage.clickContinueBtn();
            settingsPage.clickPurchaseBtn();
            Double due_Amount = settingsPage.verifySubsDue("12", "15");
            Double dueAmount = settingsPage.purchaseSubscription();

            //-----Assertion-----------
            Assert.assertEquals(dueAmount, due_Amount);
        }
        else {
           assertThat(false).as("You have an active subscription");
        }
    }
    @Test
    public void TC_002_Subscription_CancelSubscription() {

        Sign_inPage signInPage = new Sign_inPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        Boolean result = null;
        try {
            //-----verify current page is mainScreen----------
            settingsPage.verifyMainscreenIsDisplayed();

            //---------Click setting button----------
            mainScreenPage.clickSettingsBtn();

            //----enter security password--------
            signInPage.inputSecurityPassword(rb.getString("Password"));
            signInPage.clickContinueBtn();
            settingsPage.clickSubscriptionSetting();

            //-----Cancel Subscription------------
            settingsPage.cancelSubscription();
            result = settingsPage.verifyCancelSubsBtnIsDisplayed();
        } catch (Exception e) {

            //-----Assertion-----------
            assertThat(result.booleanValue()).isEqualTo(true).withFailMessage("You do not have active plan");
        }

    }
}


