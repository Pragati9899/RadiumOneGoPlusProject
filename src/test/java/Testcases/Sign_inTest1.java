package Testcases;

import PageObjects.MainScreenPage;
import PageObjects.Sign_inPage;
import basePackage.BaseTestClass;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class Sign_inTest1 extends BaseTestClass {

    public AndroidDriver driver;
    @BeforeClass
    public void initialiseTest(){
        this.driver=initialise();
    }

    @Test(priority = 2)
    public void TC_001_SignInTest_validNumber() throws MalformedURLException, InterruptedException {
       //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(3000);


       //enter verification code
        String[] a = {"1","2","3","4","5","6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        Boolean result=signInPage.checkSecuritySettingIsDisplayed();
        Assert.assertEquals(result,true);

/*
        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password"));
        signInPage.clickSubmitBtn();

        MainScreenPage mainScreen = new MainScreenPage(driver);
        Boolean nameOfUser=mainScreen.checkNameOfUser();

        Assert.assertEquals(nameOfUser,true);*/
    }
    @Test(priority = 0)
    public void TC_002_SignInTest_InvalidNumber() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);

        //check if permission diauloge are displayed or not
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
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber2"));
        signInPage.clickSignInBtn();
        Thread.sleep(3000);

        Boolean result=signInPage.checkErrorMessageDisplayedForInvalidNumber();
        //----Assertion----
        Assert.assertEquals(result,true);
    }
    @Test(priority = 1)
    public void TC_003_SignInTest_WrongCode() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(3000);
        //enter verification code
        String[] a = {"9","2","3","4","5","6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        //----Assertion----
        Boolean result=signInPage.checkErrorMessageIsDisplayedForWrongOtp();
        Assert.assertEquals(result,true);

    }
    @Test(priority = 5)
    public void TC_004_SignInTest_ResendCode() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);

        //check if permission diauloge are displayed or not
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
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber1"));
        signInPage.clickSignInBtn();
        Thread.sleep(3000);
        //enter verification code
        signInPage.clickResendBtn();
        String[] a = {"9","2","3","4","5","6"};
        signInPage.setVerificationCode(a);
        signInPage.clickSubmitBtn();

        //----Assertion----
        Boolean result=signInPage.checkSecuritySettingIsDisplayed();
        Assert.assertEquals(result,true);
    }
    @Test(priority = 4)
    public void TC_005_SignInTest_securityPasswordMatched() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);


        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password1"));
        signInPage.clickSubmitBtn();

        //----Assertion----
        MainScreenPage mainScreen = new MainScreenPage(driver);
        Boolean nameOfUser=mainScreen.checkNameOfUser();
        Assert.assertEquals(nameOfUser,true);

    }
    @Test(priority = 3)
    public void TC_006_SignInTest_securityPasswordUnMatched() throws MalformedURLException, InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);

        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password1"));
        signInPage.clickSubmitBtn();


        //----Assertion----
        Boolean errResult=signInPage.checkErrorMessageIsDisplayedForUnmatchedPassword();
        Assert.assertEquals(errResult,true);
    }
    @AfterMethod
    public void teardown(){
       // driver.quit();
    }
}

