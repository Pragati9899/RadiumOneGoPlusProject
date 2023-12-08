package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiveMoneyPage {
    public AndroidDriver driver;

    public ReceiveMoneyPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement amountField;
    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement tipField;
    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement refNoWithoutTip;


    @FindBy(xpath = "(//android.widget.EditText)[3]")
    WebElement referenceNumber;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Next\"]")
    WebElement nextBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Done\"]")
    WebElement doneBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Show QR code\"]")
    WebElement showQRcodeBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Done\"]")
    WebElement done_Btn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\" click here \"]")
    WebElement clickHereBtn;
    @FindBy(xpath = "//android.widget.EditText[@text='Mobile Number']")
    WebElement whatsappNumberField;
    @FindBy(xpath = "//android.widget.EditText[@text='Email Address']")
    WebElement emailAddressField;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Send\"]")
    WebElement sendBtn;

    public String receiveMoneyFlow(String amount, String tip, String refNo) throws InterruptedException {
        amountField.clear();
        amountField.sendKeys(amount);
        tipField.clear();
        tipField.sendKeys(tip);
        referenceNumber.sendKeys(refNo);
        nextBtn.click();
//        Thread.sleep(5000);
        return amount;
    }

    public void enterAmount(String amount) {
        amountField.clear();

        amountField.sendKeys(amount);
    }

    public void enterTip(String tip) {
        tipField.clear();
        tipField.sendKeys(tip);
    }

    public void enterReferenceNumber(String refNo) {
        referenceNumber.sendKeys(refNo);
    }

    public void setRefNoWithoutTip(String refNo) {
        refNoWithoutTip.sendKeys(refNo);
    }

    public void clickNextBtn() {
        nextBtn.click();
    }

    public void clickSendBtn() {
        sendBtn.click();
    }

    public void clickDone_btn() {
        done_Btn.click();

    }

    //------------payment received popup-------------
    @FindBy(xpath = "(//android.widget.TextView)[2]")
    WebElement popupAmount;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Close\"]")
    WebElement closeBtn;

    public String readPopupAmount() {
        String amount = popupAmount.getAttribute("text");
        closeBtn.click();
        return amount;
    }
}
