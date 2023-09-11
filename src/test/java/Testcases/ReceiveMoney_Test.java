package Testcases;

import PageObjects.MainScreenPage;
import PageObjects.ReceiveMoneyPage;
import PageObjects.SettingsPage;
import PageObjects.Sign_inPage;
import basePackage.BaseTestClass;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReceiveMoney_Test extends BaseTestClass   {
    // Assertions are not added in testcases
    //need to add locators of latest txn table to add assertion

    public AndroidDriver driver;
    @BeforeClass
    public void initialiseTest(){
        this.driver=initialise();
    }

    @Test
    public void login() throws InterruptedException {
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

    @Test(priority = 1)
    public void TC_001_ReceiveMoney_WithReferenceNumber() throws InterruptedException {

        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();

        //----set Reference number toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setReferenceNoToggleBtn();

        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.receiveMoneyFlow("20","2","12345");

        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();

    } @Test(priority = 2)
    public void TC_002_ReceiveMoney_WithoutReferenceNumber() throws InterruptedException {

        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();

        //----set Reference number toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setReferenceNoToggleBtn();

        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount("200");
        receiveMoneyPage.enterTip("2");
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickSendBtn();
        receiveMoneyPage.clickDone_btn();

        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();

    }

    @Test(priority = 3)
    public void TC_003_ReceiveMoney_WithoutTipAndRefNo() throws InterruptedException {

        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();

        //----set Tip toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setReferenceNoToggleBtn();

        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount("200");
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickSendBtn();
        receiveMoneyPage.clickDone_btn();

        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();

    } @Test(priority = 4)
    public void TC_004_ReceiveMoney_WithoutTipOnly() throws InterruptedException {

        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();

        //----set Tip toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);

        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount("200");
        receiveMoneyPage.enterReferenceNumber("23345");
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickSendBtn();
        receiveMoneyPage.clickDone_btn();

        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();
    }
    @Test(priority = 4)
    public void TC_005_ReceiveMoney_SendERecipt() throws InterruptedException {

        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();

        //----set Tip toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);

        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount("200");
        receiveMoneyPage.enterReferenceNumber("23345");
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickSendBtn();
        receiveMoneyPage.clickDone_btn();

        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();
    }

}
