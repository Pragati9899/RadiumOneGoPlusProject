package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {
    public AndroidDriver driver;
    public HistoryPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //----------------------History -----------------

    @FindBy(xpath = "//android.view.ViewGroup[1]//android.widget.EditText")
    WebElement fromDateField;
    @FindBy(xpath = "//android.view.ViewGroup[3]//android.widget.EditText")
    WebElement toDateField;
    @FindBy(xpath = "//android.view.ViewGroup[5]//android.widget.EditText")
    WebElement payNowRefField;
    @FindBy(xpath = "//android.view.ViewGroup[7]//android.widget.EditText")
    WebElement yourRefField;
    @FindBy(xpath = "//android.view.ViewGroup[9]//android.widget.EditText")
    WebElement amountField;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Search\"]")
    WebElement searchBtn;

          //---------History filter-----
    public Boolean historyFilter(String fromDate, String toDate, String payNowRef, String refNo, String amount){
        fromDateField.sendKeys(fromDate);
        toDateField.sendKeys(toDate);
        payNowRefField.sendKeys(payNowRef);
        yourRefField.sendKeys(refNo);
        amountField.sendKeys(amount);
        searchBtn.click();
        orderByBTn.click();
        String txnCount=countOfTxn.getText();
        Boolean result=countOfTxn.isDisplayed();
        if(result){
            System.out.println(txnCount+" Transactions are displayed");
        }else {
            System.out.println("No data is available to display");
        }
        return result;
    }

    @FindBy(xpath = "//android.view.ViewGroup[2]//android.widget.TextView[1]")
    WebElement countOfTxn;
    @FindBy(xpath = "//android.view.ViewGroup[2]")
    WebElement historyPage;
    @FindBy(xpath = "//android.view.ViewGroup[1]//com.horcrux.svg.PathView")
    WebElement orderByBTn;

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

           //------scan E-receipt--------------
    public Boolean scanE_receiptQRcode(String fromDate, String toDate, String payNowRef, String refNo, String amount){
        fromDateField.sendKeys(fromDate);
        toDateField.sendKeys(toDate);
        payNowRefField.sendKeys(payNowRef);
        yourRefField.sendKeys(refNo);
        amountField.sendKeys(amount);
        searchBtn.click();
        orderByBTn.click();
        historyPage.click();
        receiptBtn.click();
        Boolean result=receiptImage.isDisplayed();
        showQRcodeBtn.click();
        doneBtn.click();
        return result;
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
    public void sendE_recipt(String fromDate, String toDate, String payNowRef,String refNo,String amount,String whatsappNumber,String email){
        fromDateField.sendKeys(fromDate);
        toDateField.sendKeys(toDate);
        payNowRefField.sendKeys(payNowRef);
        yourRefField.sendKeys(refNo);
        amountField.sendKeys(amount);
        searchBtn.click();
        historyPage.click();
        receiptBtn.click();
        Boolean result=receiptImage.isDisplayed();
        showQRcodeBtn.click();
        clickHereBtn.click();
        whatsappNumberField.sendKeys(whatsappNumber);
        emailAddressField.sendKeys(email);
        sendBtn.click();
    }
}
