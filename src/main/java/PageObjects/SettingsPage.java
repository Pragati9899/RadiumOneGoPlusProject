package PageObjects;

import basePackage.BaseClass;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends BaseClass {
    protected AndroidDriver driver;

    public SettingsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //----------Security setting-----------------
    By passwordField= By.xpath("//android.view.ViewGroup//android.widget.EditText");
    By cancelBtn= By.xpath("//android.view.ViewGroup[@content-desc=\"Cancel\"]");
    By continueBtn= By.xpath("//android.view.ViewGroup[@content-desc=\"Continue\"]");


    public void enterSecuritySetting() {
        click(continueBtn);
    }

    public void clickCancelBtn() {
        click(cancelBtn);
    }

    /*------------------ General
                        settings---------------------------------------------------------
     */

    By generalSetting =By.xpath("//android.view.ViewGroup[@content-desc=\"General\"]");

    /*-------------------Reference No-------------------*/
    By referenceNoToggleBtn=By.xpath("(//android.widget.Switch)[1]");

    public void setReferenceNoToggleBtn() {
        click(referenceNoToggleBtn);
    }
    public boolean verifyRefNoToggleBtnEnabled() {
     return verifyElementIsChecked(referenceNoToggleBtn);
    }
    /*-------------------Tip amount-------------------*/

    By tipAmountToggleBtn=By.xpath("(//android.widget.Switch)[2]");

    public void setTipAmountToggleBtn() {
        click(tipAmountToggleBtn);
    }

    public boolean verifyTipToggleBtnEnabled() {
       return verifyElementIsChecked(tipAmountToggleBtn);
    }

    public void clickBackBtn() {
        backBtn.click();
    }

    public Boolean verifyBackBtnIsDisplayed() {
        return backBtn.isDisplayed();
    }

    public void verifyMainscreenIsDisplayed() {
        for (int i = 0; i < 5; i++) {
            if (Boolean.TRUE.equals(verifyBackBtnIsDisplayed())) {
                driver.navigate().back();
            } else {
                break;
            }
        }
    }




    //-------------Manage users setting---------------
    By manageUsersSetting =By.xpath("//android.view.ViewGroup[@content-desc=\"Manage Users\"]");
    By addNewUserBtn =By.xpath("//com.horcrux.svg.SvgView");
    By selectDeviceDrpdwn =By.xpath("//android.widget.HorizontalScrollView");
    By selectRoleDrpdwn =By.xpath("//android.view.ViewGroup[@content-desc=\"Select Role\"]");
    By mobileNoField =By.xpath("//android.widget.EditText[@text='+65']");
    By nameField =By.xpath("//android.widget.EditText[@text='Name']");
    By saveBtn =By.xpath("//android.view.ViewGroup[@text='Save']");
    By manageUserToggleBtn =By.xpath("//android.view.ViewGroup/android.widget.Switch[3]");

    //-----Add new user-------
    public void addNewUser(String role, String device, String mobile_no, String name) {
        click(manageUsersSetting);
        click(addNewUserBtn);
       /* selectDropdownByForLoop(driver, selectDeviceDrpdwn, device);
        selectDropdownByForLoop(driver, selectRoleDrpdwn, role);*/
        sendKeys(mobileNoField,mobile_no);
        sendKeys(nameField,name);
        click(saveBtn);
    }
    public void setManageUserToggleBtn() {
        click(manageUserToggleBtn);

    }
    By clickUserToEdit =By.xpath("//android.view.ViewGroup/android.view.ViewGroup");
    By roleDrpdwn =By.xpath("//android.view.ViewGroup[@content-desc=\"Select Role\"]");
    By mobileNo_Field =By.xpath("//android.view.ViewGroup[2]/android.widget.EditText");
    By givenName_Field =By.xpath("//android.view.ViewGroup[4]/android.widget.EditText");
    By updateBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Update\"]");


    //---Edit user ------
    public void editUser(String role, String mobile_no, String name) {
        click(manageUsersSetting);
        click(clickUserToEdit);
      //  selectDropdownByForLoop(driver, roleDrpdwn, role);
        sendKeys(mobileNo_Field,mobile_no);
        sendKeys(givenName_Field,name);
        click(updateBtn);
    }

    //-------------Manage Devices settings-----------
    By manageDevicesSetting =By.xpath("//android.view.ViewGroup[@content-desc=\"Manage Devices\"]");
    By addMobileDeviceBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Add Mobile Device\"]");
    By deviceList =By.xpath("//android.view.ViewGroup");


    //---------Add new device----------
    public void clickManageDevicesBtn() {
        click(manageDevicesSetting);
    }

   /* public int addNewDevice() {
        click(addMobileDeviceBtn);
        return countOptionsOfList(driver, deviceList);
    }

    public int countNoOfDevices() {
        return countOptionsOfList(driver, deviceList);
    }*/

    //-------------Subscription setting--------------------------
    By subscriptionsSetting =By.xpath("//android.view.ViewGroup[@content-desc=\"Subscriptions\"]");
    By clickDeviceDrpdwn =By.xpath("(//com.horcrux.svg.PathView)[2]");
    By deviceDrpdwn =By.xpath("(//android.widget.TextView)[12]");
    By clickUsersDrpdwn =By.xpath("(//com.horcrux.svg.PathView)[2]");
    By usersDrpdwn =By.xpath("(//android.widget.TextView)[17]");
    By continue_Btn =By.xpath("//android.view.View");
    By totalDueAmount =By.xpath("//android.widget.TextView[8]");
    By cancelSubsBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Cancel Subscription\"]");
    By purchaseBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Purchase\"]/android.view.ViewGroup");
    By subsCancel_Btn =By.xpath("(//android.view.ViewGroup)[18]");
    By e_receiptToggleBtn =By.xpath("(//android.widget.Switch)[1]");
    By care_plusToggleBtn =By.xpath("(//android.widget.Switch)[2]");
    By error_messageOfSubs =By.xpath("(//android.widget.TextView)[1]");
    By Ok_error_messageOfSubs =By.xpath("(//android.widget.TextView)[2]");


    //-------Add subscription-----------

    public Object verifyErrorMessageDisplay(){
        click(Ok_error_messageOfSubs);
        return verifyElementIsDisplayed(error_messageOfSubs);
    }
    public double verifySubsDue(String devices, String users) {
        return (7 + 2.5 * (Integer.parseInt(devices) - 2) + 2 * (Integer.parseInt(users) - 5));
    }

    public void clickContinueBtn() {
        //continue_Btn.click();
        scollToSpecificElement("Continue");
    }

    public void setUsers(String users) {
        click(usersDrpdwn);
        scollToSpecificElement("users");
        click(subsCancel_Btn);
    }

    public void setDevices(String devices) {
        click(deviceDrpdwn);
        scollToSpecificElement("devices");
        click(subsCancel_Btn);
    }


    public void clickSubscriptionSetting() {
        click(subscriptionsSetting);
    }

    public void setCarePlusToggleBtn() {
        click(care_plusToggleBtn);
    }

    public void setEReceiptToggleBtn() {
        click(e_receiptToggleBtn);
    }
    public void clickPurchaseBtn(){
        click(purchaseBtn);
    }

    public double purchaseSubscription() {
        String totalDueAmountText =  getText(totalDueAmount);
        return Double.parseDouble(totalDueAmountText);
    }
    public Boolean verifyEReceiptToggleBtnIsDisplayed(){
        return verifyElementIsChecked(e_receiptToggleBtn);
    }

    //------Cancel Subscription --------------
    By staySubscribedBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Stay Subscribed\"]");
    By cancelSubscriptionBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Cancel Subscription\"]");
    By cancelSubsYesBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"Yes\"]");
    By cancelSubsNoBtn =By.xpath("//android.view.ViewGroup[@content-desc=\"No\"]");
    By element =By.xpath("//android.view.ViewGroup/com.horcrux.svg.SvgView");

  /*  @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel Subscription\"]")
    WebElement cancelSubscriptionBtn;*/

    public Boolean verifyCancelSubsBtnIsDisplayed(){
        return verifyElementIsDisplayed(cancelSubscriptionBtn);
    }
    public void cancelSubscription() {
        click(cancelSubscriptionBtn);
        click(cancelSubscriptionBtn);
        click(cancelSubsYesBtn);
    }


    ////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Security\"]")
    WebElement securitySetting;
    @FindBy(xpath = "//com.horcrux.svg.SvgView")
    WebElement backBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Voice Alert, Make a voice announcement when money is received.\"]\n")
    WebElement voiceAlert;
    @FindBy(xpath = "//android.view.ViewGroup[2]//android.widget.Switch")
    WebElement voiceAlertToggleBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Sound Alert, Make a chime alert sound when money is received.\"]")
    WebElement soundAlert;
    @FindBy(xpath = "//android.view.ViewGroup[2]//android.widget.Switch")
    WebElement soundAlertToggleBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Banner Notification, Produce a notification dialog when money is received.\"]")
    WebElement bannerNotification;
    @FindBy(xpath = "//android.view.ViewGroup[2]//android.widget.Switch")
    WebElement notificationAlertToggleBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Notifications\"]")
    WebElement notificationsSetting;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Language\"]")
    WebElement languageSetting;
}
