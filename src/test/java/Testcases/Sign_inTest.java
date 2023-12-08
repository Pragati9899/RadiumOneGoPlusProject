package Testcases;

import PageObjects.MainScreenPage;
import PageObjects.Sign_inPage;
import basePackage.BaseTestClass;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;


public class Sign_inTest extends BaseTestClass {

    public AndroidDriver driver;

    @BeforeMethod
    public void initialiseTest() {
        this.driver = initialise();
    }

    @Test
    public void TC_001_SignInTestWith_RegsisteredNumber() throws  InterruptedException {

        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(20000);

        //enter verification code
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);
       // signInPage.clickSubmitBtn();

        Boolean result = signInPage.checkSecuritySettingIsDisplayed();
        Assert.assertEquals(result, true);

        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password"));
        signInPage.clickSubmitBtn();

        MainScreenPage mainScreen = new MainScreenPage(driver);
        Boolean nameOfUser=mainScreen.verifyNameOfUser();

        Assert.assertEquals(nameOfUser,true);
    }


    @Test //
    public void TC_002_SignInTestWith_InvalidNumber() throws  InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber1"));
        signInPage.clickSignInBtn();
     //   Thread.sleep(000);

        Boolean result = signInPage.checkErrorMessageDisplayedForInvalidNumber();
        //----Assertion----
        Assert.assertEquals(result, true);
    }

    @Test //-------Running----
    public void TC_003_SignInTestWith_WrongVerificationCode() throws  InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(10000);
        //enter verification code
        String[] a = {"9", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);

        //----Assertion----
        Boolean result = signInPage.checkErrorMessageIsDisplayedForWrongOtp();
        Assert.assertEquals(result, true);

    }
    @Test //
    public void TC_005_SignInTestWith_NumberLessThan_8Digit() throws InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber2"));
        signInPage.clickSignInBtn();
        //   Thread.sleep(000);

        Boolean result = signInPage.checkErrorMessageDisplayedForInvalidNumber();
        //----Assertion----
        Assert.assertEquals(result, true);
    }

    @Test//-------Running----
    public void TC_004_SignInTest_ResendCode() throws  InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);


        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(10000);
        //enter verification code
        Thread.sleep(3000);
        signInPage.clickResendBtn();
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        //----Assertion----
        Boolean result = signInPage.checkSecuritySettingIsDisplayed();
        Assert.assertEquals(result, true);
    }

    @Test
    public void TC_005_SignInTest_securityPasswordMatched() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);


        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(10000);

        //enter verification code
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        //enter security settings
        Thread.sleep(5000);
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password"));
        signInPage.clickSubmit_Btn();

        // Notification permission box
        if (rb.getString("NoteficationPerm").equals("Allow")) {
            signInPage.allowNotificationPopup();
        } else {
            signInPage.denyNotificationPopup();
        }
        //----Assertion----
        MainScreenPage mainScreen = new MainScreenPage(driver);
        Boolean nameOfUser = mainScreen.verifyNameOfUser();
        Assert.assertEquals(nameOfUser, true);

    }

    @Test //-----Running-----
    public void TC_006_SignInTest_securityPasswordUnMatched() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);


        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(10000);

        //enter verification code
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();


        //enter security settings

        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password1"));
        signInPage.clickSubmit_Btn();


        //----Assertion----
        Boolean errResult = signInPage.checkErrorMessageIsDisplayedForUnmatchedPassword();
        Assert.assertEquals(errResult, true);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

