package cubepay.com.pageObjects;

import cubepay.com.base.BaseClass;
import cubepay.com.constants.FrameworkConstants;
import cubepay.com.driver.DriverManager;
import cubepay.com.enums.ConfigProperties;
import cubepay.com.utilities.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    //------Mobile no. field------------------
//android.widget.EditText[@text='Mobile Number']
    private static final By MobileNoField = By.xpath("//android.widget.EditText");

    public LoginPage setEnterMobileNoField(String mobileNoField) {
        BaseClass.clickElement(MobileNoField, "mobileNoFeild");
        BaseClass.clearField(MobileNoField, "mobileNoFeild");
        BaseClass.sendKeys(MobileNoField, mobileNoField, "mobileNo");
        return this;
    }

    private static final By LoginPageText = By.xpath("//android.widget.TextView[@text='Please enter your mobile number to login']");

    public Boolean isLoginPageTextDisplayed() {
        return BaseClass.verifyElementIsDisplayed(LoginPageText, "Login Text");
    }

    private static final By signInBtn = By.xpath("//android.widget.TextView[@text='Sign In']");

    public void clickSignInBtn() {
        DriverManager.getDriver().hideKeyboard();
        BaseClass.clickElement(signInBtn, "signInBtn");
    }

    private static final By errorMessageForInvalidNumber = By.xpath("//android.widget.TextView[@text='Please enter a valid mobile number.']\n");

    public Boolean isErrorMessageDisplayedForInvalidNumber() {
        return BaseClass.verifyElementIsDisplayed(errorMessageForInvalidNumber, "errorMessageForInvalidNumber");
    }

    private static final By errorMessageForUnregisteredNum = By.xpath("//android.widget.TextView[@text='User not found. Please check your information or contact support for help.']\n");

    public Boolean isErrorMessageDisplayedForUnregisteredNum() {
        return BaseClass.verifyElementIsDisplayed(errorMessageForUnregisteredNum, "errorMessageForUnregisteredNum");
    }

    private static final By errorMessage_RetryBlockPeriod = By.xpath("//android.widget.TextView[@text=\"New OTP can't be regenerated yet. Please wait a bit before retrying.\"]\n");

    public Boolean isErrorMessageDisplayedForRetryBlockPeriod() {
        return BaseClass.verifyElementIsDisplayed(errorMessage_RetryBlockPeriod, "errorMessage_RetryBlockPeriod");
    }

    private static final By signupBtn = By.xpath("");

    public void clickSignupBtn() {
        DriverManager.getDriver().hideKeyboard();
        BaseClass.clickElement(signupBtn, "signupBtn");
    }

    //----Verification code----------------------------
    private static final By verificationPage = By.xpath("//android.widget.TextView[@text='77dc -']");

    private static final By verificationCodeBox1 = By.xpath("//android.widget.TextView[@text='77dc -']/following-sibling::android.widget.EditText");
    private static final By verificationCodeBox2 = By.xpath("(//android.widget.EditText)[2]");
    private static final By verificationCodeBox3 = By.xpath("(//android.widget.EditText)[3]");
    private static final By verificationCodeBox4 = By.xpath("(//android.widget.EditText)[4]");
    private static final By verificationCodeBox5 = By.xpath("(//android.widget.EditText)[5]");
    private static final By verificationCodeBox6 = By.xpath("(//android.widget.EditText)[6]");

    public boolean isVerificationFieldEmpty() {
        return BaseClass.getAttribute(verificationCodeBox1).isEmpty();
    }

    public boolean isPasswordFieldEmpty() {
        return BaseClass.getAttribute(passwordField).isEmpty();
    }

    public boolean isVerificationCodeEncrypted() {
        return BaseClass.verifyElementIsPassword(verificationCodeBox2, "Verification code field");
    }

    public void setVerificationCode(String[] code) {
        BaseClass.sendKeys(verificationCodeBox1, code[0], "verificationCode1");
        BaseClass.sendKeys(verificationCodeBox2, code[1], "verificationCode2");
        BaseClass.sendKeys(verificationCodeBox3, code[2], "verificationCode3");
        BaseClass.sendKeys(verificationCodeBox4, code[3], "verificationCode4");
        BaseClass.sendKeys(verificationCodeBox5, code[4], "verificationCode5");
        BaseClass.sendKeys(verificationCodeBox6, code[5], "verificationCode6");
    }

    public boolean verifyAutofocus() {
        return Boolean.parseBoolean(DriverManager.getDriver().findElement(verificationCodeBox1).getAttribute("focused"));
    }

    public boolean isKeyboardDisplayed() {
        return DriverManager.getDriver().isKeyboardShown();
    }

    public boolean isVerificationPageDisplayed() {
        return BaseClass.verifyElementIsDisplayed(verificationPage, "verification Page");
    }

    public boolean isVerificationPagePresent() {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT())).
                until(ExpectedConditions.visibilityOfElementLocated(verificationPage));
        return BaseClass.verifyElementIsPresent(verificationPage, "verification Page");

    }

    private static final By backBtn = By.xpath("//com.horcrux.svg.u");

    public void clickBackBtn() {
        BaseClass.clickElement(backBtn, "Back Button");
    }

    public Boolean isBackBtnDisplayed() {
        return BaseClass.verifyElementIsDisplayed(backBtn, "Back Button");
    }

    private static final By errorMessageForWrongOtp = By.xpath("//android.widget.TextView[@text='Invalid OTP code provided. Please try again.']");

    public Boolean checkErrorMessageIsDisplayedForWrongOtp() {
        return BaseClass.verifyElementIsDisplayed(errorMessageForWrongOtp, "errorMessageForWrongOtp");
    }

    //--Resend Button--------------------------------
    private static final By resendBtn = By.xpath("//android.widget.TextView[@text='Resend']");

    public void clickResendBtn() {
        DriverManager.getDriver().hideKeyboard();
        BaseClass.clickElement(resendBtn, "resendBtn");
    }

    private static final By resendTimer =
            By.xpath("//android.widget.TextView[contains(@text ,\"Didn't receive the OTP\")]");


    public boolean isResendTimerDisplayed() {
        return BaseClass.verifyElementIsDisplayed(resendTimer, "ResendTimer");
    }

    public Boolean isDisplayedResendBtn() {
        return BaseClass.verifyElementIsDisplayed(resendBtn, "resendBtn");
    }

    //----Security Settings----------------------------
    private static final By securitySetting = By.xpath("//android.widget.TextView[@text='Security Settings']");

    public boolean isSecurityPasswordPagePresent() {
        return BaseClass.verifyElementIsPresent(securitySetting, "security setting page");

    }

    public Boolean checkSecuritySettingIsDisplayed() {
        return BaseClass.verifyElementIsDisplayed(securitySetting, "security Setting Page");
    }


    private static final By passwordField = By.xpath("//android.widget.TextView[@text='Enter Password']/following-sibling::android.widget.EditText");
    private static final By reEnterPasswordField = By.xpath("//android.widget.TextView[@text='Re-Enter Password']/following-sibling::android.widget.EditText");

    public LoginPage setEnterPasswordField(String password) {
        BaseClass.sendKeys(passwordField, password, "password");
        return this;
    }

    public LoginPage setReEnterPasswordField(String password) {
        BaseClass.sendKeys(reEnterPasswordField, password, "reEnterPassword");
        return this;
    }

    private static final By errorMessageForUnmatchedPassword = By.xpath("//android.widget.TextView[@text='* Passwords do not match!']");


    public Boolean isErrorMessageDisplayedForUnmatchedPassword() {
        return BaseClass.verifyElementIsDisplayed(errorMessageForUnmatchedPassword, "errorMessageForUnmatchedPassword");
    }

    private static final By errorMessageToEnterPassword = By.xpath("//android.widget.TextView[@text='* Please enter password!']");

    public Boolean isErrorMessageDisplayedToEnterPassword() {
        return BaseClass.verifyElementIsDisplayed(errorMessageToEnterPassword, "errorMessageToEnterPassword");
    }


    private static final By errorMessageForShortPassword = By.xpath("//android.widget.TextView[@text='* Password should have minimum 6 characters']");

    public Boolean isErrorMessageDisplayedForShortPassword() {
        return BaseClass.verifyElementIsDisplayed(errorMessageForShortPassword, "errorMessageForShortPassword");
    }

    public boolean isSecurityPasswordsEncrypted() {
        return BaseClass.verifyElementIsPassword(passwordField, "Security password field");
    }

    private static final By SubmitBtn = By.xpath("//android.view.ViewGroup[@content-desc='Submit']");

    public void clickSubmitBtn() {
        DriverManager.getDriver().hideKeyboard();
        BaseClass.clickElement(SubmitBtn, "SubmitBtn");
    }


    public void handleSecurityPasswordPage() {
        if (isSecurityPasswordPagePresent()) {
            setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                    .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                    .clickSubmitBtn();
        }
    }

    public void setLoginCredentials(ConfigProperties configProperties) {
        setEnterMobileNoField(PropertyUtils.getValue(configProperties))
                .clickSignInBtn();
        String[] a = {"1", "2", "3", "4", "5", "6"};
        setVerificationCode(a);
    }

    public void setMobileNumber(ConfigProperties configProperties) {
        setEnterMobileNoField(PropertyUtils.getValue(configProperties))
                .clickSignInBtn();
    }

    public void setVerificationCode() {
        String[] a1 = {"1", "2", "3", "4", "5", "6"};
        setVerificationCode(a1);
    }

}


