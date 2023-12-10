package PageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HistoryPage {

    public AndroidDriver driver;

    public HistoryPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //----------------------History -----------------

    @FindBy(xpath = "(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[1]")
    WebElement monthPicker;
    @FindBy(xpath = "(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[2]")
    WebElement datePicker;
    @FindBy(xpath = "(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[3]")
    WebElement yearPicker;
   /* @FindBy(xpath = "//android.widget.Button[@text='CONFIRM']\n")
    WebElement confirmBtn;*/
    @FindBy(id = "android:id/button1")
    WebElement confirmBtn;
    @FindBy(xpath = "//android.widget.Button[@text='CANCEL']")
    WebElement cancelBTn;
    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement fromDateField;
    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement toDateField;
    @FindBy(xpath = "//android.widget.EditText[@text='PayNow Ref #']")
    WebElement payNowRefField;
    @FindBy(xpath = "//android.widget.EditText[@text='Your Ref #']")
    WebElement yourRefField;
    @FindBy(xpath = "//android.widget.EditText[@text='Amount']")
    WebElement amountField;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Search\"]")
    WebElement searchBtn;

    //---------History filter---------------------------------------------

    public void selectFromDate(String fromYear, String fromMonth, String fromDate) throws InterruptedException {
        Thread.sleep(4000);
        //-----------select From Date---------
        fromDateField.click();
        Thread.sleep(4000);
        monthPicker.clear();
        monthPicker.sendKeys(fromMonth);
        datePicker.clear();
        datePicker.sendKeys(fromDate);
        yearPicker.clear();
        yearPicker.sendKeys(fromYear);
        Thread.sleep(3000);

        AndroidTouchAction touch = new AndroidTouchAction (driver);
        touch.tap(TapOptions.tapOptions().withPosition(PointOption.point(530,1050))).perform();
      //  driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" +"CONFIRM"+ "\").instance(0))")).click();
      //  confirmBtn.click();
    }

    public void selectToDate(String toDate, String toYear, String toMonth) throws InterruptedException {
        toDateField.click();
        Thread.sleep(4000);
        monthPicker.clear();
        monthPicker.sendKeys(toMonth);
        datePicker.clear();
        datePicker.sendKeys(toDate);
        yearPicker.clear();
        yearPicker.sendKeys(toYear);
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "CONFIRM" + "\").instance(0))")).click();

       // confirmBtn.click();
    }

    public void setPayNowRef(String payNowRef) {
        payNowRefField.sendKeys(payNowRef);
    }

    public void setRefNo(String refNo) {
        yourRefField.sendKeys(refNo);
    }

    public void setAmount(String amount) {
        amountField.sendKeys(amount);
    }

    public void clickSearchBtn() {
        searchBtn.click();
    }

    public void clickOrderByBtn() {
        orderByBTn.click();
    }

    public Boolean countOfTxn() {
        String txnCount = countOfTxn.getText();
        Boolean result = countOfTxn.isDisplayed();
        if (result) {
            System.out.println(txnCount + " Transactions are displayed");
        } else {
            System.out.println("No data is available to display");
        }
        return result;
    }


    @FindBy(xpath = "(//android.widget.TextView)[3]")
    WebElement textInCaseOfNoTxn;

    public String verifyTextInCaseOfNoTxn() {
        return textInCaseOfNoTxn.getText();
    }

    @FindBy(xpath = "(//android.widget.TextView)[4]")
    WebElement countOfTxn;
    @FindBy(xpath = "(//com.horcrux.svg.PathView)[3]")
    WebElement historyPageIcon;
    @FindBy(xpath = "(//com.horcrux.svg.PathView)[2]")
    WebElement orderByBTn;
    @FindBy(xpath = "(//android.widget.TextView)[3]")
    WebElement dateOfTxn;
    @FindBy(xpath = "(//android.widget.TextView)[5]")
    WebElement timeOfTxn;

    public String verifyDateOfTxn() {
        return dateOfTxn.getText();
    }

    public String verifyTimeOfTxn() {
        return timeOfTxn.getText();
    }

    @FindBy(xpath = "(//android.widget.TextView)[8]")
    WebElement amountOfTxn;

    public String verifyAmountOfTxn() {
        return amountOfTxn.getText();
    }

    @FindBy(xpath = "(//android.widget.TextView)[7]")
    WebElement payNowRefOfTxn;

    public String verifyPayNowRefOfTxn() {
        return payNowRefOfTxn.getText();
    }

    @FindBy(xpath = "(//android.widget.TextView)[10]")
    WebElement refNoOfTxn;

    public String verifyRefNoOfTxn() {
        payNowRefOfTxn.click();
        return refNoOfTxn.getText();
    }

    @FindBy(xpath = "(//android.widget.TextView)[12]")
    WebElement tipOfTxn;
    @FindBy(xpath = "(//android.widget.TextView)[6]")
    WebElement owner_or_date;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Location\"]")
    WebElement locationBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Receipt\"]")
    WebElement receiptBtn;
    @FindBy(xpath = "//android.widget.ImageView")
    WebElement receiptImage;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Show QR code\"]")
    WebElement showQRcodeBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Done\"]")
    WebElement doneBtn;

     public Boolean verifyE_reciptBtnIsDisplayed(){
         return receiptBtn.isDisplayed();
     }

    public void clickOnIcon() {
        historyPageIcon.click();
    }



    //------scan E-receipt--------------
    public void clickReceiptBtn() {
        receiptBtn.click();
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\" click here \"]")
    WebElement clickHereBtn;
    @FindBy(xpath = "//android.widget.EditText[@text='Mobile Number']")
    WebElement whatsappNumberField;
    @FindBy(xpath = "//android.widget.EditText[@text='Email Address']")
    WebElement emailAddressField;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Send\"]")
    WebElement sendBtn;

    //-----SendE-receipt-------------
    public Boolean VerifyReceiptImageIsDisplayed() {
      return receiptImage.isDisplayed();
    }
}
