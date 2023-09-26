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

public class ReceiveMoney_Test extends BaseTestClass   {
    // Assertions are not added in testcases
    //need to add locators of latest txn table to add assertion
    Sign_inPage signInPage;
    public AndroidDriver driver;
    @BeforeMethod
    public void initialiseTest(){
        this.driver=initialise();
    }


   @BeforeMethod(dependsOnMethods = "initialiseTest()")
    public void login() throws InterruptedException {
        //sign in page object
        Sign_inPage signInPage = new Sign_inPage(driver);
        Thread.sleep(3000);
//       JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        //check if permission diauloge are displayed or not
        if(signInPage.permissionpop_upIsDisplayed()) {
            if (rb.getString("PermissionDialogue").equals("While using the app")) {
                Thread.sleep(4000);
                signInPage.AcceptThePop_up1();
                Thread.sleep(2000);
                signInPage.AcceptThePop_up1();
                Thread.sleep(2000);
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
        Thread.sleep(2000);
        signInPage.clickSubmit_Btn();
        Thread.sleep(20000);

        //enter security settings
        signInPage.setEnterPasswordField(rb.getString("Password"));
        signInPage.setReEnterPasswordField(rb.getString("Password"));
        signInPage.clickSubmit_Btn();
       Thread.sleep(3000);
//       List<String> asyncStorageKeys = (List<String>) jsExecutor.executeScript("return await AsyncStorage.getItem('token');");

        // Notification permission box
        if(rb.getString("NotificationPerm").equals("Allow")) {
            signInPage.allowNotificationPopup();
        }else{
            signInPage.denyNotificationPopup();
        }
//       List<String> asyncStorageKeys = (List<String>) jsExecutor.executeScript("return Object.keys(window.asyncStorage);");

//       for (String key : asyncStorageKeys) {
//           Object value = jsExecutor.executeScript(
//                   "return window.asyncStorage.getItem(arguments[0]);",
//                   key
//           );
//
//           // Process or store the key-value pair as needed
//           System.out.println("Key: " + key + ", Value: " + value);
//       }

   }

    @Test(priority = 1)
    public void TC_001_ReceiveMoney_WithReferenceNumber() throws InterruptedException, IOException, NotFoundException {

        signInPage = new Sign_inPage(driver);
        MainScreenPage mainScreenPage = new MainScreenPage(driver);

        //------Complete flow of receive money-----------
        ReceiveMoneyPage receiveMoneyPage = new ReceiveMoneyPage(driver);
        Thread.sleep(6000);
        mainScreenPage.clickReceiveMoneyBtn();
        Thread.sleep(3000);
        receiveMoneyPage.receiveMoneyFlow(rb.getString("amount"),
                rb.getString("tip"), rb.getString("refNo"));
        Thread.sleep(20000);

        //---------Read QR code--------------
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot.png");
        FileUtils.copyFile(screenshotFile, destinationFile);
        Utils util=new Utils();
        Thread.sleep(6000);
        WebElement qrCode=driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        util.generateImage(qrCode,destinationFile);
        WebElement qrCodeElement = driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
        System.out.println(content);
        try {
            baseURI = "https://api.g-sandbox.radiumone.io/paynow";
            Response validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTY0Mjg2NywiZXhwIjoxNjk2MjQ3NjY3LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.3InLCdoSuA45Gw7IYjpKBAqX1n68tHoSKD9n5ncbezs")
                    .accept(ContentType.JSON)
                    .header("Accept-Encoding", "gzip, deflate, br")
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
//-------Refresh Button------------------
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);
            mainScreenPage.clickRefreshBtn();
            String result = mainScreenPage.readAmount();
            Assert.assertEquals(result, "+" + " $ " + rb.getString("amount"));

        }


     @Test(priority = 2)
    public void TC_002_ReceiveMoney_WithoutReferenceNumber() throws InterruptedException, IOException, NotFoundException {
         signInPage= new Sign_inPage(driver);
         MainScreenPage mainScreenPage= new MainScreenPage(driver);
      SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setReferenceNoToggleBtn();

        //------Complete flow of receive money-----------
         mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.enterTip(rb.getString("tip"));
        receiveMoneyPage.enterAmount(rb.getString("refNo"));
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickDone_btn();
         Thread.sleep(30000);

         File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         File destinationFile = new File("C:\\Users\\Pragati\\Desktop\\RadiumOneGoPlus\\src\\test\\resources\\Screenshots\\screenshot.png");
         FileUtils.copyFile(screenshotFile, destinationFile);
         Utils util=new Utils();
         WebElement qrCode=driver.findElement(By.xpath("(//com.horcrux.svg.GroupView)[5]"));
         util.generateImage(qrCode,destinationFile);
         WebElement qrCodeElement = driver.findElement(By.id("com.example.qrcode:id/imageView"));
         File screenshot = driver.getScreenshotAs(OutputType.FILE);

         String content = decodeQRCode(util.generateImage(qrCodeElement, screenshot));
         Assert.assertEquals(content,"f3ce8d4d-074f-483f-9fd0-45c7947fd40c");
        //-------Refresh Button------------------
      /*   mainScreenPage.clickRefreshBtn();
         String result=mainScreenPage.readAmount();
         Assert.assertEquals(result,"+"+" $ "+rb.getString("amount"));*/

    }

    @Test(priority = 3)
    public void TC_003_ReceiveMoney_WithoutTipAndRefNo() throws InterruptedException {
        signInPage= new Sign_inPage(driver);
        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        //----set Tip toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setReferenceNoToggleBtn();
        settingsPage.setTipAmountToggleBtn();

        //------Complete flow of receive money-----------
        mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);

        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();
        String result=mainScreenPage.readAmount();
        Assert.assertEquals(result,"+"+" $ "+rb.getString("amount"));

    } @Test(priority = 4)
    public void TC_004_ReceiveMoney_WithoutTipOnly() throws InterruptedException {
        signInPage= new Sign_inPage(driver);
        //----click on Setting Btn-----
        MainScreenPage mainScreenPage= new MainScreenPage(driver);
        mainScreenPage.clickSettingsBtn();
        //----enter security password--------
        signInPage.inputSecurityPassword(rb.getString("Password"));
        signInPage.clickContinueBtn();
        //----set Tip toggle button -------------
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setTipAmountToggleBtn();

        //------Complete flow of receive money-----------
        mainScreenPage.clickReceiveMoneyBtn();
        ReceiveMoneyPage receiveMoneyPage=new ReceiveMoneyPage(driver);
        receiveMoneyPage.enterAmount(rb.getString("amount"));
        receiveMoneyPage.enterReferenceNumber(rb.getString("refNo"));
        receiveMoneyPage.clickNextBtn();
        receiveMoneyPage.clickDone_btn();
        Thread.sleep(3000);




        //-------Refresh Button------------------
        mainScreenPage.clickRefreshBtn();
        String result=mainScreenPage.readAmount();
        Assert.assertEquals(result,"+"+" $ "+rb.getString("amount"));
      //  https://api.g-sandbox.radiumone.io/paynow/notify/mockup?qr_data=00020101021226560009SG.PAYNOW010120213201509555BNN1030100412230914205029520400005303702540450.05802SG5914DEV MERCHANT 56009SINGAPORE621601120870-0000VL663049149
    }
    @Test(priority = 5)
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

    }
   /* @AfterClass
    public void teardown(){
        driver.quit();
    }*/
}
