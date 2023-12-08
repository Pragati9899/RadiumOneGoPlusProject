package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainScreenPage {
    //android.widget.TextView[text()='Received Today']


    public AndroidDriver driver;

    public MainScreenPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //-------------Name of user-------------------
    @FindBy(xpath = "(//android.widget.TextView)[1]")
    WebElement nameOfUser;

    public Boolean verifyNameOfUser() {
        return Boolean.valueOf(nameOfUser.getAttribute("displayed"));
    }

    //-----------------Name of device-------------
    @FindBy(xpath = "(//android.view.ViewGroup)[0](//android.widget.TextView)[1]")
    WebElement nameOfDevice;

    public Boolean verifyNameOfDevice() {
        return nameOfDevice.isDisplayed();
    }

    //----------------Received Amount----------------
    @FindBy(xpath = "(//android.view.ViewGroup)[0](//android.widget.TextView)[2]")
    WebElement receivedAmount;

    public Boolean verifyReceivedAmount() {
        Boolean totalAmount = receivedAmount.isDisplayed();
        return totalAmount;
    }

    //--------------------Compared percentage to yesterday-------
    @FindBy(xpath = "(//android.view.ViewGroup)[3](//android.widget.TextView)[0]")
    WebElement comparedPerc;

    public Boolean checkComparedPerc() {
        Boolean perc = comparedPerc.isDisplayed();
        return perc;
    }

    //----------------Menu buttons----------------
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Receive Money\"]")
    WebElement receiveMoneyBtn;

    public void clickReceiveMoneyBtn() {
        receiveMoneyBtn.click();
    }

    @FindBy(xpath = "  //android.view.ViewGroup[@content-desc=\"Settings\"]")
    WebElement settingsBtn;

    public void clickSettingsBtn() {
        settingsBtn.click();
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"History\"]")
    WebElement historyBtn;

    public void clickHistoryBtn() {
        historyBtn.click();
    }

    //------------------------Refresh button----
    @FindBy(xpath = "(//com.horcrux.svg.PathView)[2]")
    WebElement refreshBtn;

    public void clickRefreshBtn() {
        refreshBtn.click();
    }

    //------------------Help Center----------
    @FindBy(xpath = "(//android.view.ViewGroup)[3]//com.horcrux.svg.PathView")
    WebElement helpCenterBtn;

    public void clickHelpCenterBtn() {
        helpCenterBtn.click();
    }

    //-------------kiosk mode--------------------
    @FindBy(xpath = "(//android.view.ViewGroup)[2]//com.horcrux.svg.PathView")
    WebElement kioskBtn;

    public void clickKioskBtn() {
        kioskBtn.click();
    }

    //-----------------Logout btn----------------
    @FindBy(xpath = "//android.widget.TextView[@text=\"Logout\"]")
    WebElement logoutBtn;

    public void clickLogoutBtn() {
        logoutBtn.click();
    }

    //---------Txn table-------------
    @FindBy(xpath = "(//android.widget.TextView)[9]")
    WebElement amount;

    public String readAmount() {
        String result = amount.getAttribute("text");
        return result;
    }

    @FindBy(xpath = "(//android.widget.TextView)[8]")
    WebElement paynow_ref;

    public String readPayNowRef() {
        String result = amount.getAttribute("text");
        return result;
    }

    @FindBy(xpath = "(//android.widget.TextView)[10]")
    WebElement timeOfTxn;

    public String readTimeOfTxn() {
        String result = amount.getAttribute("text");
        return result;
    }

    //-----Security password--------


}
