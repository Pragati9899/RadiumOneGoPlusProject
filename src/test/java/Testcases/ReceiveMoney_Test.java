package Testcases;

import PageObjects.*;
import Utilities.Utils;
import basePackage.BaseTestClass;
import com.google.zxing.NotFoundException;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static Utilities.Utils.decodeQRCode;
import static io.restassured.RestAssured.baseURI;

public class ReceiveMoney_Test extends BaseTestClass {
    // Assertions are not added in testcases
    //need to add locators of latest txn table to add assertion
    Sign_inPage signInPage;
    public AndroidDriver driver;

    @BeforeClass
    public void initialiseTest() {
        this.driver = initialise();
    }

    @BeforeClass(dependsOnMethods = "initialiseTest()")
    public void login() throws InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);

        //enter mobile number
        signInPage.setEnterMobileNoField(rb.getString("MobileNumber"));
        signInPage.clickSignInBtn();
        Thread.sleep(15000);

        //enter verification code
        String[] a = {"1", "2", "3", "4", "5", "6"};
        signInPage.setVerificationCode(a);
        Thread.sleep(10000);
       // signInPage.clickSubmit_Btn();


        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password"));
        signInPage.clickSubmitBtn();
        Thread.sleep(10000);


        // Notification permission box
        if (rb.getString("NotificationPerm").equals("Allow")) {
            signInPage.allowNotificationPopup();
        } else {
            signInPage.denyNotificationPopup();
        }


    }

    @Test(priority = 1)
    public void TC_001_ReceiveMoney_WithReferenceNumberAndTip() throws InterruptedException, IOException, NotFoundException {

        signInPage = new Sign_inPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //---------Click setting button----------
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        //------------enable Reference number and tip----------
        settingsPage.clickGeneralSetting();
        Thread.sleep(3000);
        if (!settingsPage.verifyRefNoToggleBtnEnabled()) {
            settingsPage.setReferenceNoToggleBtn();
        }
        Thread.sleep(3000);
        if (!settingsPage.verifyTipToggleBtnEnabled()) {
            settingsPage.setTipAmountToggleBtn();
        }
        Thread.sleep(6000);
        driver.navigate().back();
        driver.navigate().back();
        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage = new ReceiveMoneyPage(driver);
        Thread.sleep(6000);
        mainScreenPage.clickReceiveMoneyBtn();
        Thread.sleep(3000);
        receiveMoneyPage.receiveMoneyFlow(rb.getString("amount"),
                rb.getString("tip"), rb.getString("refNo"));
        Thread.sleep(20000);

        //---------Read QR code--------------
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot1.png");
        FileUtils.copyFile(screenshotFile, destinationFile);
        Utils util = new Utils();
        Thread.sleep(6000);
        WebElement qrCode = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        util.generateImage(qrCode, destinationFile);
        WebElement qrCodeElement = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
        System.out.println(content);
        //---------------scan QR code------------
        try {
            baseURI = "https://api.g-sandbox.radiumone.io/paynow";
            Response validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTY0Mjg2NywiZXhwIjoxNjk2MjQ3NjY3LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.3InLCdoSuA45Gw7IYjpKBAqX1n68tHoSKD9n5ncbezs")
                    .accept(ContentType.JSON)
                    .queryParam("qr_data", content)
                    .when()
                    .post("/notify/mockup").then()
                    .log().all()
                    .contentType(ContentType.JSON).extract().response();
            String responseBody = validatableResponse.getBody().asString();
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        receiveMoneyPage.readPopupAmount();
        Thread.sleep(3000);

        receiveMoneyPage.clickDone_btn();
        Thread.sleep(10000);

        //-----------   Assertion ------------------
        mainScreenPage.clickRefreshBtn();
        String result = mainScreenPage.readAmount();
        Assert.assertEquals(result, "+" + " $ " + String.valueOf((Double.parseDouble(rb.getString("amount")) + Double.parseDouble(rb.getString("tip")))) + "0");
    }


    @Test(priority = 2)
    public void TC_002_ReceiveMoney_WithoutReferenceNumber() throws InterruptedException, IOException, NotFoundException {
        signInPage = new Sign_inPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //---------Click setting button----------
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        //------------Disable Reference number----------
        settingsPage.clickGeneralSetting();
        settingsPage.setReferenceNoToggleBtn();
        settingsPage.clickBackBtn();
        settingsPage.clickBackBtn();
        //------Complete flow of receive money-----------
        mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage = new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.enterTip(rb.getString("tip"));
        receiveMoneyPage.clickNextBtn();
        Thread.sleep(30000);

        //---------Read QR code--------------
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot2.png");
        FileUtils.copyFile(screenshotFile, destinationFile);
        Utils util = new Utils();
        Thread.sleep(6000);
        WebElement qrCode = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        util.generateImage(qrCode, destinationFile);
        WebElement qrCodeElement = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
        System.out.println(content);

        //---------------scan QR code------------
        try {
            baseURI = "https://api.g-sandbox.radiumone.io/paynow";
            Response validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTY0Mjg2NywiZXhwIjoxNjk2MjQ3NjY3LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.3InLCdoSuA45Gw7IYjpKBAqX1n68tHoSKD9n5ncbezs")
                    .accept(ContentType.JSON)
                    .queryParam("qr_data", content)
                    .when()
                    .post("/notify/mockup").then()
                    .log().all()
                    .contentType(ContentType.JSON).extract().response();
            String responseBody = validatableResponse.getBody().asString();
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        receiveMoneyPage.readPopupAmount();
        Thread.sleep(3000);
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);

        //-----------   Assertion ------------------
        mainScreenPage.clickRefreshBtn();
        Thread.sleep(3000);
        String result = mainScreenPage.readAmount();
        Assert.assertEquals(result, "+" + " $ " + String.valueOf((Double.parseDouble(rb.getString("amount")) + Double.parseDouble(rb.getString("tip")))) + "0");

    }

    @Test(priority = 3)
    public void TC_003_ReceiveMoney_WithoutTipAndRefNo() throws InterruptedException, IOException, NotFoundException {
        signInPage = new Sign_inPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //----click on Setting Btn-----
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        Thread.sleep(3000);
        //----Disable Reference no and tip -------------
        settingsPage.clickGeneralSetting();
        settingsPage.setTipAmountToggleBtn();
        settingsPage.clickBackBtn();
        settingsPage.clickBackBtn();
        //------Complete flow of receive money-----------
        mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage = new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.clickNextBtn();
        Thread.sleep(3000);

        //---------Read QR code--------------
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot3.png");
        FileUtils.copyFile(screenshotFile, destinationFile);
        Utils util = new Utils();
        Thread.sleep(6000);
        WebElement qrCode = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        util.generateImage(qrCode, destinationFile);
        WebElement qrCodeElement = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
        System.out.println(content);

        //---------------scan QR code------------
        try {
            baseURI = "https://api.g-sandbox.radiumone.io/paynow";
            Response validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTY0Mjg2NywiZXhwIjoxNjk2MjQ3NjY3LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.3InLCdoSuA45Gw7IYjpKBAqX1n68tHoSKD9n5ncbezs")
                    .accept(ContentType.JSON)
                    .queryParam("qr_data", content)
                    .when()
                    .post("/notify/mockup").then()
                    .log().all()
                    .contentType(ContentType.JSON).extract().response();
            String responseBody = validatableResponse.getBody().asString();
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        receiveMoneyPage.readPopupAmount();
        Thread.sleep(3000);
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);

        //-----------   Assertion ------------------
        mainScreenPage.clickRefreshBtn();
        Thread.sleep(3000);
        String result = mainScreenPage.readAmount();
        Assert.assertEquals(result, "+" + " $ " + (rb.getString("amount")));


    }

    @Test(priority = 4)
    public void TC_004_ReceiveMoney_WithoutTipOnly() throws InterruptedException, IOException, NotFoundException {
        signInPage = new Sign_inPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);
        //-----verify current page is mainScreen----------
        settingsPage.verifyMainscreenIsDisplayed();
        //----click on Setting Btn-----
        mainScreenPage.clickSettingsBtn();

        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();

        //----set Tip toggle button -------------
        settingsPage.clickGeneralSetting();
        settingsPage.setReferenceNoToggleBtn();
        // settingsPage.setTipAmountToggleBtn();
        settingsPage.clickBackBtn();
        settingsPage.clickBackBtn();
        //------Complete flow of receive money-----------
        mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage = new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.setRefNoWithoutTip(rb.getString("refNo"));
        receiveMoneyPage.clickNextBtn();
        Thread.sleep(3000);
        //---------Read QR code--------------
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot3.png");
        FileUtils.copyFile(screenshotFile, destinationFile);
        Utils util = new Utils();
        Thread.sleep(6000);
        WebElement qrCode = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        util.generateImage(qrCode, destinationFile);
        WebElement qrCodeElement = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
        System.out.println(content);

        //---------------scan QR code------------
        try {
            baseURI = "https://api.g-sandbox.radiumone.io/paynow";
            Response validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTY0Mjg2NywiZXhwIjoxNjk2MjQ3NjY3LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.3InLCdoSuA45Gw7IYjpKBAqX1n68tHoSKD9n5ncbezs")
                    .accept(ContentType.JSON)
                    .queryParam("qr_data", content)
                    .when()
                    .post("/notify/mockup").then()
                    .log().all()
                    .contentType(ContentType.JSON).extract().response();
            String responseBody = validatableResponse.getBody().asString();
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        receiveMoneyPage.readPopupAmount();
        Thread.sleep(3000);
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);

        //-----------   Assertion ------------------
        mainScreenPage.clickRefreshBtn();
        Thread.sleep(3000);
        String result = mainScreenPage.readAmount();
        Assert.assertEquals(result, "+" + " $ " + (rb.getString("amount")));
    }
    /*@Test(priority = 5)
    public void TC_005_ReceiveMoney_SendERecipt() throws InterruptedException {
        signInPage= new Sign_inPage(driver);
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        //------Complete flow of receive money-----------
        mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.enterTip(rb.getString("tip"));
        receiveMoneyPage.enterReferenceNumber(rb.getString("refNo"));
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);
        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();
        String result=mainScreenPage.readAmount();
        Assert.assertEquals(result,"+"+" $ "+rb.getString("amount"));

    }*/
 /* @AfterClass
    public void teardown(){
        driver.quit();
    }*/
}
