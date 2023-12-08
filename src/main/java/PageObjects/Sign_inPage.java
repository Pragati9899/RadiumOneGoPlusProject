package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static actiondriver.ActionDriver.IsDisplayed;

public class Sign_inPage {
    public AndroidDriver driver;

    public Sign_inPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //-------Permission Diauloge-----------------------------
    @FindBy(xpath = "//android.widget.LinearLayout")
    WebElement permissionpop_up;

    public Boolean permissionpop_upIsDisplayed() {
        Boolean result = permissionpop_up.isDisplayed();
        return result;
    }
    // id.widget.Button[@text='While using the app']

    //android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']
    //com.android.permissioncontroller:id/permission_allow_foreground_only_button
    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    WebElement getPermissionPop_up1;

    public void AcceptThePop_up1() {
        getPermissionPop_up1.click();
    }

    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
    WebElement getPermissionPop_up2;

    public void AcceptThePop_up2() {
        getPermissionPop_up2.click();
    }

    @FindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    WebElement getPermissionPop_up3;

    public void AcceptThePop_up3() {
        getPermissionPop_up3.click();
    }

    //----country code-----------------------
    @FindBy(xpath = "//android.widget.TextView")
    WebElement element;
    @FindBy(xpath = "//android.widget.TextView")
    List<WebElement> countryCode;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Done\"]")
    WebElement doneBtn;

    public void clickOnCountryCode() {
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


    public void setEnterMobileNoField(String mobileNoField) {
        MobileNoField.click();
        MobileNoField.sendKeys(mobileNoField);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Sign In']")
    WebElement signInBtn;

    public void clickSignInBtn() {
        signInBtn.click();
        signInBtn.click();
    }

   /* @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"User not found. Please verify the provided information or contact support for assistance.\"]")
    WebElement errorMessageForInvalidNumber;*/
    @FindBy(id = "toastText2")
    WebElement errorMessageForInvalidNumber;
    public Boolean checkErrorMessageDisplayedForInvalidNumber() {
        return Boolean.valueOf(errorMessageForInvalidNumber.getAttribute("displayed"));
    }
    //android.widget.TextView[@content-desc="Please enter a valid phone number."]
    @AndroidBy(id="toastText1") public
    //----Verification code----------------------------
    @FindBy(xpath = "//android.widget.EditText")
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

    public void setVerificationCode(String[] code) throws InterruptedException {

        verificationCode1.sendKeys(code[0]);
        Thread.sleep(200);
        verificationCode2.sendKeys(code[1]);
        Thread.sleep(200);
        verificationCode3.sendKeys(code[2]);
        Thread.sleep(200);
        verificationCode4.sendKeys(code[3]);
        Thread.sleep(200);
        verificationCode5.sendKeys(code[4]);
        Thread.sleep(200);
        verificationCode6.sendKeys(code[5]);
        element2.click();
    }

    public void clickSubmitBtn() {

        submitBtn.click();
    }

    public void clickSubmit_Btn() {
        submitBtn.click();

    }

    //android.widget.TextView[@text='Submit']
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Wrong OTP\"]")
    WebElement errorMessageForWrongOtp;

    public Boolean checkErrorMessageIsDisplayedForWrongOtp() {
        Boolean result = errorMessageForWrongOtp.isDisplayed();
        return result;
    }

    //--Resend Button--------------------------------
    @FindBy(xpath = "//android.widget.TextView[@text='Resend (60s)']")
    WebElement resendBtn;

    public void clickResendBtn() throws InterruptedException {
        Thread.sleep(2000);
        resendBtn.click();
    }

    //----Security Settings----------------------------
    @FindBy(xpath = "//android.widget.TextView[@text='Security Settings']")
    WebElement securitySetting;

    public Boolean checkSecuritySettingIsDisplayed() {
        return IsDisplayed(driver, securitySetting);
    }

    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement PasswordField;
    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement reEnterPasswordField;

    public void setEnterPasswordField(String password) {
        PasswordField.sendKeys(password);
    }

    public void setReEnterPasswordField(String password) {
        reEnterPasswordField.sendKeys(password);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='* Passwords do not match!']")
    WebElement errorMessageForUnmatchedPassword;

    public Boolean checkErrorMessageIsDisplayedForUnmatchedPassword() {
        return errorMessageForUnmatchedPassword.isDisplayed();
    }

    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement inputPassword;

    public void inputSecurityPassword(String password) {
        inputPassword.sendKeys(password);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Continue\"]")
    WebElement continueBtn;

    public void clickContinueBtn() {
        continueBtn.click();
    }//android.view.ViewGroup[@content-desc="Continue"]

    @FindBy(xpath = " //android.view.ViewGroup[@content-desc=\"Cancel\"]")
    WebElement cancelBtn;

    public void clickCancelBtn() {
        cancelBtn.click();
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Forgot your security password?\"]")
    WebElement forgetPasswordLink;

    public void forgetPassword(String[] code, String password) {
        forgetPasswordLink.click();
        continueBtn.click();
        frgtPsswdVerificationCode1.click();
        frgtPsswdVerificationCode1.sendKeys(code[0]);
        frgtPsswdVerificationCode1.sendKeys(code[1]);
        frgtPsswdVerificationCode1.sendKeys(code[2]);
        frgtPsswdVerificationCode1.sendKeys(code[3]);
        frgtPsswdVerificationCode1.sendKeys(code[4]);
        frgtPsswdVerificationCode1.sendKeys(code[5]);
        submit_Btn.click();

        enterNewPassword.sendKeys(password);
        reEnterNewPassword.sendKeys(password);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Resend (60s)\"]")
    WebElement frgtPsswdResendBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Submit\"]")
    WebElement submit_Btn;
    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement frgtPsswdVerificationCode1;
    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement frgtPsswdVerificationCode2;
    @FindBy(xpath = "(//android.widget.EditText)[3]")
    WebElement frgtPsswdVerificationCode3;
    @FindBy(xpath = "(//android.widget.EditText)[4]")
    WebElement frgtPsswdVerificationCode4;
    @FindBy(xpath = "(//android.widget.EditText)[5]")
    WebElement frgtPsswdVerificationCode5;
    @FindBy(xpath = "(//android.widget.EditText)[6]")
    WebElement frgtPsswdVerificationCode6;
    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement enterNewPassword;
    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement reEnterNewPassword;


    //----------Notification Popup-------------------
    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    WebElement allowBtn;
    @FindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    WebElement denyBtn;

    public void allowNotificationPopup() {
        allowBtn.click();
    }

    public void denyNotificationPopup() {
        denyBtn.click();
    }
}
