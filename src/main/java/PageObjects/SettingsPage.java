package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static actiondriver.ActionDriver.*;

public class SettingsPage {  public AndroidDriver driver;
    public SettingsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //----------Security setting-----------------
    @FindBy(xpath = "//android.view.ViewGroup//android.widget.EditText")
    WebElement passwordField;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel\"]")
    WebElement cancelBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Continue\"]")
    WebElement continueBtn;
    public void enterSecuritySetting(String pass_word){
        passwordField.sendKeys(pass_word);
        continueBtn.click();
    }
    public void clickCancelBtn(){
        cancelBtn.click();
    }

    //------------------- General settings------------
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"General\"]")
    WebElement generalSetting;
    @FindBy(xpath = "(//android.widget.Switch)[2]")
    WebElement referenceNoToggleBtn;
    public void setReferenceNoToggleBtn(){
    generalSetting.click();
    referenceNoToggleBtn.click();
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Notifications\"]")
    WebElement notificationsSetting;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Language\"]")
    WebElement languageSetting;


    //-------------Manage users setting---------------
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Manage Users\"]")
    WebElement manageUsersSetting;
    @FindBy(xpath = "//com.horcrux.svg.SvgView")
    WebElement addNewUserBtn;
    @FindBy(xpath = "//android.widget.HorizontalScrollView")
    List<WebElement> selectDeviceDrpdwn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Select Role\"]")
    List<WebElement> selectRoleDrpdwn;
    @FindBy(xpath = "//android.widget.EditText[@text='+65']")
    WebElement mobileNoField;
    @FindBy(xpath = "//android.widget.EditText[@text='Name']")
    WebElement nameField;
    @FindBy(xpath = "//android.view.ViewGroup[@text='Save']")
    WebElement saveBtn;

           //-----Add new user-------
    public void addNewUser(String role,String device, String mobile_no,String name){
        manageUsersSetting.click();
        addNewUserBtn.click();
        selectDropdownByForLoop( driver, selectDeviceDrpdwn, device);
        selectDropdownByForLoop( driver, selectRoleDrpdwn, role);
        type(mobileNoField,mobile_no);
        type(nameField,name);
        saveBtn.click();
    }


    @FindBy(xpath = "//android.view.ViewGroup/android.widget.Switch[3]")
    WebElement manageUserToggleBtn;
    public void setManageUserToggleBtn(){
        manageUserToggleBtn.click();

    }

    @FindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup")
    WebElement clickUserToEdit;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Select Role\"]")
    List<WebElement>  roleDrpdwn;
    @FindBy(xpath = "//android.view.ViewGroup[2]/android.widget.EditText")
    WebElement mobileNo_Field;
    @FindBy(xpath = "//android.view.ViewGroup[4]/android.widget.EditText")
    WebElement givenName_Field;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Update\"]")
    WebElement updateBtn;

        //---Edit user ------
    public void editUser(String role,String mobile_no,String name){
        manageUsersSetting.click();
        clickUserToEdit.click();
        selectDropdownByForLoop( driver, roleDrpdwn, role);
        type(mobileNo_Field,mobile_no);
        type(givenName_Field,name);
        updateBtn.click();
    }
    //-------------Manage Devices settings-----------
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Manage Devices\"]")
    WebElement manageDevicesSetting;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Add Mobile Device\"]")
    WebElement addMobileDeviceBtn;
    @FindBy(xpath = "//android.view.ViewGroup")
    List<WebElement>  deviceList;

              //---------Add new device----------
    public void clickManageDevicesBtn(){
        manageDevicesSetting.click();
    }
    public int addNewDevice(){
        addMobileDeviceBtn.click();
        int countedOptionsOfList=countOptionsOfList(driver,deviceList);
        return countedOptionsOfList;
    }
    public int countNoOfDevices(){
        int count=countOptionsOfList(driver,deviceList);
        return count;
    }

    //-------------Subscription setting--------------------------
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Subscriptions\"]")
    WebElement subscriptionsSetting;
    @FindBy(xpath = "//android.view.ViewGroup[4]//com.horcrux.svg.PathView")
    List<WebElement> deviceDrpdwn;
    @FindBy(xpath = "//android.view.ViewGroup[5]//com.horcrux.svg.PathView")
    List<WebElement> usersDrpdwn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Continue\"]/android.view.ViewGroup/android.widget.TextView")
    WebElement continue_Btn;
    @FindBy(xpath = "//android.widget.TextView[8]")
    WebElement totalDueAmount;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel\"]/android.view.ViewGroup")
    WebElement cancel_Btn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Purchase\"]/android.view.ViewGroup")
    WebElement purchaseBtn;
             //-------Add subscription-----------
    public double addSubscription(String devices, String users){
        subscriptionsSetting.click();
        selectDropdownByForLoop(driver,deviceDrpdwn,devices);
        selectDropdownByForLoop(driver,usersDrpdwn,users);
        double totalSubAmount =(7+2.5*(Integer.parseInt(devices)-2)+2*(Integer.parseInt(users)-5));
        continue_Btn.click();
        return totalSubAmount;
    }
    public double purchaseSubscription(){
        String totalDueAmountText=totalDueAmount.getText();
        double totalDueAmount=Double.parseDouble(totalDueAmountText);
        return totalDueAmount;
    }

              //------Cancel Subscription --------------
    @FindBy(xpath = "//android.view.ViewGroup/com.horcrux.svg.SvgView")
    WebElement element;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel Subscription\"]/android.widget.TextView")
    WebElement cancelSubscriptionBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Stay Subscribed\"]")
    WebElement staySubscribedBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel Subscription\"]")
    WebElement cancelSubscription_Btn;

   public void cancelSubscription(){
       subscriptionsSetting.click();
       cancelSubscriptionBtn.click();
       cancelSubscription_Btn.click();
   }






     ////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Security\"]")
    WebElement securitySetting;
    @FindBy(xpath = "//com.horcrux.svg.PathView")
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







}
