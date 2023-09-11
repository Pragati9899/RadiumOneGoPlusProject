package Testcases;

import PageObjects.HistoryPage;
import PageObjects.MainScreenPage;
import PageObjects.Sign_inPage;
import basePackage.BaseTestClass;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Histroy_Test extends BaseTestClass {
    public AndroidDriver driver;
    @BeforeClass
    public void initialiseTest(){
        this.driver=initialise();
    }

    @Test
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

    @Test(priority = 1)
    public void TC_001_HistoryFilter_withAllInput(){
        //-----Click on History Button-------------
        MainScreenPage mainScreenPage=new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        Boolean result=historyPage.historyFilter("2023-08-08","2023-09-08",
                "086Y-0000PDD","Af1234567890","550");

        //-----------Assertion ------------
        Assert.assertEquals(result,true);
    }

    @Test(priority = 2)
    public void TC_002_HistoryFilter_withPayNowRef(){

        //-----Click on History Button-------------
        MainScreenPage mainScreenPage=new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        Boolean result=historyPage.historyFilter("2023-08-08","2023-09-08",
                "086Y-0000PDD","","");

        //-----------Assertion ------------
        Assert.assertEquals(result,true);
    }

    @Test(priority = 3)
    public void TC_003_HistoryFilter_withRefNo(){

        //-----Click on History Button-------------
        MainScreenPage mainScreenPage=new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        Boolean result=historyPage.historyFilter("2023-08-08","2023-09-08",
                "","Af1234567890","");

        //-----------Assertion ------------
        Assert.assertEquals(result,true);
    }

    @Test(priority = 4)
    public void TC_004_HistoryFilter_withAmount(){
        //-----Click on History Button-------------
        MainScreenPage mainScreenPage=new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        Boolean result=historyPage.historyFilter("2023-08-08","2023-09-08",
                "","","550");

        //-----------Assertion ------------
        Assert.assertEquals(result,true);
    }
    @Test(priority = 5)
    public void TC_005_HistoryFilter_withDateOnly(){

        //-----Click on History Button-------------
        MainScreenPage mainScreenPage=new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        Boolean result=historyPage.historyFilter("2023-08-08","2023-09-08",
                "","","");

        //-----------Assertion ------------
        Assert.assertEquals(result,true);
    }
}
