package cubepay.com.pageObjects;

import cubepay.com.base.BaseClass;
import cubepay.com.driver.DriverManager;
import cubepay.com.utilities.DynamicXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainScreenPage {
    //android.widget.TextView[text()='Received Today']
    private static final String XPATH1 = "(//android.widget.TextView)[%s]";
    private static final String XPATH2 = "(//android.view.ViewGroup)[%s](//android.widget.TextView)[%s1]";
    private static final String XPATH3 = "(//android.view.ViewGroup)[%s]";
    private static final String XPATH4 = "//android.view.ViewGroup[@content-desc=%s]";
    public void clickHelpCenterBtn() {
        BaseClass.clickElement(DynamicXpath.constructXpath(XPATH3, "14"), "HelpCenterBtn");
    }
    private static final By LogoutBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"Logout\"]");


    public void clickLogoutBtn() {
        BaseClass.clickElement(LogoutBtn, "logoutBtn");
    }

    //-------------Name of user-------------------
    public Boolean isUserNameDisplayed() {
        return BaseClass.verifyElementIsDisplayed(DynamicXpath.constructXpath(XPATH1, "1")
                , "UserName");
    }

    //-----------------Name of device-------------

    public Boolean isDeviceNameDisplayed() {
        return BaseClass.verifyElementIsDisplayed(DynamicXpath.constructXpath(XPATH2, "0", "1"), "DeviceName");
    }

    //----------------Received Amount----------------

    public String getReceivedAmount() {
        return BaseClass.getText(DynamicXpath.constructXpath(XPATH1, "2"));
    }

    //--------------------Compared percentage to yesterday-------

    public String getComparedPercentage() {
        return BaseClass.getText(DynamicXpath.constructXpath(XPATH2, "3", "0"));
    }

    //----------------Menu buttons----------------

    public void clickReceiveMoneyBtn() {
        BaseClass.clickElement(DynamicXpath.constructXpath(XPATH4, "Receive Money"), "ReceiveMoneyBtn");
    }

    public void clickSettingsBtn() {
      //  BaseClass.clickElement(DynamicXpath.constructXpath(XPATH4, "Settings"), "SettingsBtn");
        BaseClass.clickElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Settings\"]"), "SettingsBtn");

    }



    public void clickHistoryBtn() {
        BaseClass.clickElement(DynamicXpath.constructXpath(XPATH4, "History"), "HistoryBtn");
    }

    By BackBtn = By.xpath("//com.horcrux.svg.SvgView");

    public Boolean verifyBackBtnIsDisplayed() {
        return BaseClass.verifyElementIsDisplayed(BackBtn, "BackBtn");
    }
    public boolean ifBackButtonIsPresent() {
        return BaseClass.verifyElementIsPresent(BackBtn, "Back button");

    }
    public void verifyMainscreenIsDisplayed() {
        for (int i = 0; i < 7; i++) {
            if (Boolean.TRUE.equals(ifBackButtonIsPresent())) {
                DriverManager.getDriver().navigate().back();
            } else {
                break;
            }
        }
    }

    //------------------------Refresh button----
    @FindBy(xpath = "(//com.horcrux.svg.PathView)[2]")
    WebElement refreshBtn;

    public void clickRefreshBtn() {
        refreshBtn.click();
    }

    //------------------Help Center----------


    //-------------kiosk mode--------------------
    private static final By HelpCenterBtn = By.xpath("(//android.view.ViewGroup)[14]");

       public void Btn() {
           BaseClass.clickElement(HelpCenterBtn, "helpCenterBtn");
    }

    //-----------------Logout btn----------------


    //---------Txn table-------------
    @FindBy(xpath = "(//android.widget.TextView)[9]")
    WebElement amount;

    public String readAmount() {
        return amount.getAttribute("text");
    }

    @FindBy(xpath = "(//android.widget.TextView)[8]")
    WebElement paynow_ref;

    public String readPayNowRef() {
        return amount.getAttribute("text");
    }

    @FindBy(xpath = "(//android.widget.TextView)[10]")
    WebElement timeOfTxn;

    public String readTimeOfTxn() {
        return amount.getAttribute("text");
    }

    //-----Security password--------

    public void navigateToMainScreenIfRequired() {
        if (ifBackButtonIsPresent()) {
            verifyMainscreenIsDisplayed();
            clickHelpCenterBtn();
            clickLogoutBtn();
        }
    }
}
