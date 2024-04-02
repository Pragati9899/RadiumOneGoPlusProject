package cubepay.com.pageObjects;

import cubepay.com.base.BaseClass;
import cubepay.com.utilities.DynamicXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiveMoneyPage {

    private static final String XPATH1 ="(//android.widget.EditText)[%s]";
    private static final String XPATH2 ="(//android.view.ViewGroup)[%s]";
    private static final By  amountField= DynamicXpath.constructXpath(XPATH1,"1");
    private static final By  tipField= DynamicXpath.constructXpath(XPATH1,"1");
    private static final By  refrenceNo= DynamicXpath.constructXpath(XPATH1,"1");
    private static final By nextBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"Next\"]");
    private static final By  crossBtn= DynamicXpath.constructXpath(XPATH2,"16");
    private static final By amountText = By.xpath("//android.widget.TextView[@text='Amount']");
    private static final By tipText = By.xpath("//android.widget.TextView[@text='Tip']");
    private static final By referenceText = By.xpath("//android.widget.TextView[@text='Reference']");
    private static final By  feildCrossBtn= DynamicXpath.constructXpath(XPATH2,"23");
    private static final By  totalAmountScanToPayPage= DynamicXpath.constructXpath(XPATH1,"7");
    private static final By  crossBtnScanToPayPage= DynamicXpath.constructXpath(XPATH2,"15");

    private static final By merchantName = By.xpath("(//android.widget.TextView)[9]");
    private static final By expiredIn = By.xpath("(//android.widget.TextView)[5]");
    private static final By titleOfScanToPay = By.xpath("//android.widget.TextView[@text='Scan To Pay']");

    private static final By showQRcodeBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"Show QR code\"]");



    public String receiveMoneyFlow(String amount, String tip, String refNo)  {
        BaseClass.sendKeys(amountField, amount,"amount");
        BaseClass.sendKeys(tipField, tip,"tip");
        BaseClass.sendKeys(refrenceNo, refNo,"refNo");
        BaseClass.clickElement(nextBtn,"nextBtn");
        return amount;
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
