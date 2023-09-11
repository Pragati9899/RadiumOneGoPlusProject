package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static actiondriver.ActionDriver.IsDisplayed;

public class Sign_inPage{
    public AndroidDriver driver;
    public Sign_inPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //-------Permission Diauloge-----------------------------
    @FindBy(xpath = "//android.widget.LinearLayout")
    WebElement permissionpop_up;
    public Boolean permissionpop_upIsDisplayed(){
        Boolean result=permissionpop_up.isDisplayed();
        return result;
    }
   // id.widget.Button[@text='While using the app']
    //android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']
    @FindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']\n")
    WebElement getPermissionPop_up1;
    public void AcceptThePop_up1(){
        getPermissionPop_up1.click();
    }
    @FindBy(xpath = "//android.widget.Button[@text='Only this time']")
    WebElement getPermissionPop_up2;
    public void AcceptThePop_up2(){
        getPermissionPop_up2.click();
    }
    @FindBy(xpath = "//android.widget.Button[@text='Don’t allow']")
    WebElement getPermissionPop_up3;
    public void AcceptThePop_up3(){
        getPermissionPop_up3.click();
    }

    //----country code-----------------------
    @FindBy(xpath = "//android.widget.TextView")
    WebElement element;
    @FindBy(xpath = "//android.widget.TextView")
     List<WebElement> countryCode;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Done\"]")
    WebElement doneBtn;
    public void clickOnCountryCode(){
        element.click();
    }
    public void selectCountryCode(String country_code) {
        for (int i = 0; i < countryCode.size(); i++) {
            String text = (countryCode.get(i).getText());
            if (text.equals(country_code)) {
                countryCode.get(i).click();
            }
        }
    }

    //------Mobile no. field------------------
    @FindBy(xpath = "//android.widget.EditText[@text='Enter Mobile Number']")
    WebElement MobileNoField;
    public void setEnterMobileNoField(String mobileNoField){
        MobileNoField.click();
        MobileNoField.sendKeys(mobileNoField);
    }
    @FindBy(xpath = "//android.widget.TextView[@text='Sign In']")
    WebElement signInBtn;
    public void  clickSignInBtn(){
        signInBtn.click();
        signInBtn.click();
    }

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"toastText1\"]")
    WebElement errorMessageForInvalidNumber;
    public Boolean checkErrorMessageDisplayedForInvalidNumber(){
        Boolean result = errorMessageForInvalidNumber.isDisplayed();
        return result;
    }
    //----Verification code----------------------------
    @FindBy(xpath = "(//android.view.ViewGroup)[3]//android.widget.EditText")
    WebElement verificationCode1;
    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement verificationCode2;
    @FindBy(xpath = "(//android.widget.EditText)[3]")
    WebElement verificationCode3;
    @FindBy(xpath = "(//android.widget.EditText)[4]")
    WebElement verificationCode4;
    @FindBy(xpath = "(//android.widget.EditText)[5]")
    WebElement verificationCode5;
    @FindBy(xpath = "(//android.widget.EditText)[6]")
    WebElement verificationCode6;
    @FindBy(xpath = "//android.view.ViewGroup")
    WebElement element2;
    @FindBy(xpath = "//android.widget.TextView[@text='Submit']")
    WebElement submitBtn;
    public void setVerificationCode(String[] code){
        verificationCode1.click();
        verificationCode1.sendKeys(code[0]);
        verificationCode2.sendKeys(code[1]);
        verificationCode3.sendKeys(code[2]);
        verificationCode4.sendKeys(code[3]);
        verificationCode5.sendKeys(code[4]);
        verificationCode6.sendKeys(code[5]);
        element2.click();
    }
    public void clickSubmitBtn(){
        submitBtn.click();
        submitBtn.click();
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Wrong OTP\"]")
    WebElement errorMessageForWrongOtp;
    public Boolean checkErrorMessageIsDisplayedForWrongOtp(){
        Boolean result =errorMessageForWrongOtp.isDisplayed();
        return result;
    }
    //--Resend Button--------------------------------
    @FindBy(xpath = "//android.widget.TextView[@text='Resend (60s)'")
    WebElement resendBtn;
    public void clickResendBtn(){
        resendBtn.click();
    }
    //----Security Settings----------------------------
    @FindBy(xpath = "//android.widget.TextView[@text='Security Settings']")
    WebElement securitySetting;
    public Boolean checkSecuritySettingIsDisplayed(){
        Boolean result=IsDisplayed(driver,securitySetting );
        return result;
    }
    @FindBy(xpath = "//android.widget.EditText")
    WebElement PasswordField;
    @FindBy(xpath = "(//android.view.ViewGroup)[3](//android.widget.EditText)[3]")
    WebElement reEnterPasswordField;
    public void setEnterPasswordField(String password){
        PasswordField.sendKeys(password);
    }
    public void setReEnterPasswordField(String password){
        reEnterPasswordField.sendKeys(password);
    }


    @FindBy(xpath = "//android.widget.TextView[@text='* Passwords do not match!']")
    WebElement errorMessageForUnmatchedPassword;
    public Boolean checkErrorMessageIsDisplayedForUnmatchedPassword(){
        Boolean result=errorMessageForUnmatchedPassword.isDisplayed();
        return result;
    }


}
