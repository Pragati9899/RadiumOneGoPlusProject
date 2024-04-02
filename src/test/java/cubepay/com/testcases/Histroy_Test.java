/*
package Testcases;

import cubepay.com.pageObjects.HistoryPage;
import cubepay.com.pageObjects.MainScreenPage;
import cubepay.com.pageObjects.SettingsPage;
import cubepay.com.pageObjects.Login;
import com.google.zxing.NotFoundException;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class Histroy_Test  {
    public AndroidDriver driver;




    // @BeforeMethod(dependsOnMethods = "initialiseTest()")
    public void login() throws InterruptedException {
        //sign in page object
        Login signInPage = new Login(driver);
        sleep(3000);

        if (signInPage.permissionpop_upIsDisplayed()) {
            if (rb.getString("PermissionDialogue").equals("While using the app")) {
                sleep(4000);
                signInPage.AcceptThePop_up1();
                sleep(2000);
                signInPage.AcceptThePop_up1();
                sleep(2000);
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
        sleep(6000);

        //enter verification code
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);

        //  signInPage.clickSubmit_Btn();
        sleep(10000);
        String pagesource = driver.getPageSource();
        System.out.println(pagesource);
        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password"));
        signInPage.clickSubmit_Btn();
        sleep(3000);

        // Notification permission box
        if (rb.getString("NotificationPerm").equals("Allow")) {
            signInPage.allowNotificationPopup();
        } else {
            signInPage.denyNotificationPopup();
        }
    }

    @Test(priority = 1)
    public void TC_001_HistoryFilter_withAllInput() throws InterruptedException {
        SettingsPage settingsPage = new SettingsPage(driver);

        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();

        //-----Click on History Button-------------
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
        // historyPage.selectToDate( rb.getString("toDate"),rb.getString("toYear"),rb.getString("toMonth"));
        historyPage.setPayNowRef(mainScreenPage.readPayNowRef());
        historyPage.setRefNo(rb.getString("refNo"));
        historyPage.setAmount(rb.getString("amount"));
        historyPage.clickSearchBtn();
        sleep(20000);
        historyPage.clickOrderByBtn();
        //-----check the condition of no txn---------
        if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
            System.out.println(historyPage.verifyTextInCaseOfNoTxn());
        } else {
            //-----------Assertion ------------
            String result = historyPage.verifyPayNowRefOfTxn();
            Assert.assertEquals(result, rb.getString("payNowRef"));
        }
    }

    @Test(priority = 2)
    public void TC_002_HistoryFilter_withPayNowRef() throws InterruptedException {
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();

        //-----Click on History Button-------------
        mainScreenPage.clickHistoryBtn();
        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
        // historyPage.selectToDate( rb.getString("toDate"),rb.getString("toYear"),rb.getString("toMonth"));
        historyPage.setPayNowRef(rb.getString("payNowRef"));
        historyPage.clickSearchBtn();
        sleep(20000);
        historyPage.clickOrderByBtn();
        //-----check the condition of no txn---------
        if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
            System.out.println(historyPage.verifyTextInCaseOfNoTxn());
        } else {
            //-----------Assertion ------------
            String result = historyPage.verifyPayNowRefOfTxn();
            Assert.assertEquals(result, rb.getString("payNowRef"));
        }
    }


    @Test(priority = 3)
    public void TC_003_HistoryFilter_withRefNo() throws InterruptedException {
        SettingsPage settingsPage = new SettingsPage(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //-----Click on History Button-------------
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
        // historyPage.selectToDate( rb.getString("toDate"),rb.getString("toYear"),rb.getString("toMonth"));
        historyPage.setRefNo(rb.getString("ref_no"));
        historyPage.clickSearchBtn();
        sleep(20000);
        historyPage.clickOrderByBtn();
        //-----check the condition of no txn---------
        if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
            System.out.println(historyPage.verifyTextInCaseOfNoTxn());
        } else {
            //-----------Assertion ------------
            String result = historyPage.verifyRefNoOfTxn();
            Assert.assertEquals(result, rb.getString("ref_no"));
        }
    }

    @Test(priority = 4)
    public void TC_004_HistoryFilter_withAmount() throws InterruptedException {
        SettingsPage settingsPage = new SettingsPage(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //-----Click on History Button-------------
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
        // historyPage.selectToDate( rb.getString("toDate"),rb.getString("toYear"),rb.getString("toMonth"));
        historyPage.setAmount(rb.getString("purAmount"));
        historyPage.clickSearchBtn();
        sleep(20000);
        historyPage.clickOrderByBtn();

        //-----check the condition of no txn---------
        if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
            System.out.println(historyPage.verifyTextInCaseOfNoTxn());
        } else {
            //-----------Assertion ------------
            String result = historyPage.verifyAmountOfTxn();
            Assert.assertEquals(result, rb.getString("purAmount"));
        }
    }

    //Does not getting error message
    @Test(priority = 5)
    public void TC_005_HistoryFilter_withDateOnly() throws InterruptedException {
        SettingsPage settingsPage = new SettingsPage(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //-----Click on History Button-------------
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        mainScreenPage.clickHistoryBtn();

        //--------History Filter-------------------
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
        historyPage.selectToDate(rb.getString("toDate"), rb.getString("toYear"), rb.getString("toMonth"));
        historyPage.clickSearchBtn();
        sleep(20000);
        historyPage.clickOrderByBtn();
        //-----check the condition of no txn---------
        if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
            System.out.println(historyPage.verifyTextInCaseOfNoTxn());
        } else {
            //-----------Assertion ----------
            Boolean result = historyPage.countOfTxn();
            Assert.assertEquals(result, true);
        }
    }
    @Test(priority = 6)
    public void TC_006_E_recipt_Image() throws InterruptedException {
        SettingsPage settingsPage = new SettingsPage(driver);
        HistoryPage historyPage = new HistoryPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        Login signInPage = new Login(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //---------Click setting button----------
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();

        settingsPage.clickSubscriptionSetting();
        sleep(20000);
        if(settingsPage.verifyCancelSubsBtnIsDisplayed()&&settingsPage.verifyEReceiptToggleBtnIsDisplayed()){
            settingsPage.verifyMainscreenIsDisplayed();
            mainScreenPage.clickHistoryBtn();

            //--------History Filter-------------------
            historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
           // historyPage.selectToDate(rb.getString("toDate"), rb.getString("toYear"), rb.getString("toMonth"));
            historyPage.clickSearchBtn();
            sleep(20000);

            if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
                System.out.println(historyPage.verifyTextInCaseOfNoTxn());
                assertThat(historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")).isTrue().withFailMessage("No data available to display");
            }
            else{
                historyPage.clickOnIcon();
              if (historyPage.verifyE_reciptBtnIsDisplayed()) {
                  historyPage.clickReceiptBtn();
              }
              //  assertThat( historyPage.VerifyReceiptImageIsDisplayed()).isTrue();
                assertThat( historyPage.VerifyReceiptImageIsDisplayed()).isTrue();


          }
    }else {
            assertThat(settingsPage.verifyCancelSubsBtnIsDisplayed() && settingsPage.verifyEReceiptToggleBtnIsDisplayed()).isTrue().withFailMessage("Either you do not have an active subscription or E-receipt toggle button is enabled ");
        }
}
    @Test(priority = 6)
    public void TC_006_E_recipt_Scanner() throws InterruptedException, IOException, NotFoundException {
        SettingsPage settingsPage = new SettingsPage(driver);
        HistoryPage historyPage = new HistoryPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        Login signInPage = new Login(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //---------Click setting button----------
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();

        settingsPage.clickSubscriptionSetting();
        sleep(20000);
        if(settingsPage.verifyCancelSubsBtnIsDisplayed()&&settingsPage.verifyEReceiptToggleBtnIsDisplayed()){
            settingsPage.verifyMainscreenIsDisplayed();
            mainScreenPage.clickHistoryBtn();

            //--------History Filter-------------------
            historyPage.selectFromDate(rb.getString("fromYear"), rb.getString("fromMonth"), rb.getString("fromDate"));
            // historyPage.selectToDate(rb.getString("toDate"), rb.getString("toYear"), rb.getString("toMonth"));
            historyPage.clickSearchBtn();
            sleep(20000);

            if (historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")) {
                System.out.println(historyPage.verifyTextInCaseOfNoTxn());
                assertThat(historyPage.verifyTextInCaseOfNoTxn().equals("No data available to display")).isTrue().withFailMessage("No data available to display");
            }
            else{
                historyPage.clickOnIcon();
                if (historyPage.verifyE_reciptBtnIsDisplayed()) {
                    historyPage.clickReceiptBtn();
*/
/*
                    //---------Read QR code--------------
                    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\receipt.png");
                    FileUtils.copyFile(screenshotFile, destinationFile);
                    Utils util = new Utils();
                    Thread.sleep(6000);
                    WebElement qrCode = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
                    util.generateImage(qrCode, destinationFile);
                    WebElement qrCodeElement = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
                    File screenshot = driver.getScreenshotAs(OutputType.FILE);

                    String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
                    System.out.println(content);*//*

                    //---------------scan QR code------------
                    try {
                        baseURI = "https://api.g-sandbox.radiumone.io";
                        Response validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NjIzMTYxMSwiZXhwIjoxNjk2ODM2NDExLCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NzgifQ.BBrZHMAZtie7vHt2mitfYZqvk2F0fX7aEbjcJjrB4ho")
                                .header("Accept-Encoding", "gzip, deflate, br")
                                .queryParam("type", "paynow_ref")
                                .queryParam("id","08JB-0001LEA")
                                .when()
                                .get("/receipt/qr").then()
                                .log().all()
                                .contentType(ContentType.JSON).extract().response();
                        String responseBody = validatableResponse.asString();
                        System.out.println(responseBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //  assertThat( historyPage.VerifyReceiptImageIsDisplayed()).isTrue();
                assertThat( historyPage.VerifyReceiptImageIsDisplayed()).isTrue();


            }
        }else {
            assertThat(settingsPage.verifyCancelSubsBtnIsDisplayed() && settingsPage.verifyEReceiptToggleBtnIsDisplayed()).isTrue().withFailMessage("Either you do not have an active subscription or E-receipt toggle button is enabled ");
        }
    }
}
*/
