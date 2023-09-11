package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.Watchable;

public class ReceiveMoneyPage {
    public AndroidDriver driver;
    public ReceiveMoneyPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.EditText")
    WebElement amountField;
    @FindBy(xpath = "//android.view.ViewGroup[3]/android.widget.EditText")
    WebElement tipField;
    @FindBy(xpath = "//android.view.ViewGroup[5]/android.widget.EditText")
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

    public void receiveMoneyFlow(String amount,String tip,String refNo){
        amountField.sendKeys(amount);
        tipField.sendKeys(tip);
        referenceNumber.sendKeys(refNo);
        nextBtn.click();
        sendBtn.click();
        done_Btn.click();

    }
   public void enterAmount(String amount){
        amountField.sendKeys(amount);
   }
   public void enterTip(String tip){
        tipField.sendKeys(tip);
   }
   public void enterReferenceNumber(String refNo){
        referenceNumber.sendKeys(refNo);
   }
   public void clickNextBtn(){
        nextBtn.click();
   }
   public void clickSendBtn(){
        sendBtn.click();
   }
   public void clickDone_btn(){
        done_Btn.click();
   }
}
