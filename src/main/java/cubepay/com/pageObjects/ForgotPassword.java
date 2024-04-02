package cubepay.com.pageObjects;
import cubepay.com.base.BaseClass;
import cubepay.com.enums.ConfigProperties;
import cubepay.com.utilities.PropertyUtils;
import org.openqa.selenium.By;

public class ForgotPassword {

    /*
             Enter password to access settings
   */
    private static final By inputPassword = By.xpath("(//android.widget.EditText)[1]");

    public ForgotPassword enterPassword(String password) {
        BaseClass.sendKeys(inputPassword, password,"password");
        return this;
    }

    private static final By continueBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"Continue\"]");

    public void clickContinueBtn() {
        BaseClass.clickElement(continueBtn, "continueBtn");
    }

    private static final By crossBtn = By.xpath("(//android.view.ViewGroup)[6]");

    public void clickCancelBtn() {
        BaseClass.clickElement(crossBtn,"crossBtn");
    }


    private static final By incorrectPasswordErrorMessage = By.xpath("//android.widget.TextView[contains(@text, 'Incorrect Password')]");

    public boolean isIncorrectPasswordErrorMessageDisplayed() {
        return BaseClass.verifyElementIsDisplayed(incorrectPasswordErrorMessage,"incorrectPasswordErrorMessage");
    }

    private static final By forgetPasswordLink = By.xpath("//android.view.ViewGroup[@content-desc=\"Forgot your security password?\"]");
    public void clickForgetPasswordLink() {
        BaseClass.clickElement(forgetPasswordLink,"forgetPasswordLink");
    }


    public void forgotPassword(String[] code, String password) {
        BaseClass.clickElement(forgetPasswordLink,"forgetPasswordLink");
        BaseClass.clickElement(continueBtn,"continueBtn");
        BaseClass.sendKeys(verificationCodeBox1, code[0],"verificationCode");
        BaseClass.sendKeys(verificationCodeBox2, code[1],"verificationCode");
        BaseClass.sendKeys(verificationCodeBox3, code[2],"verificationCode");
        BaseClass.sendKeys(verificationCodeBox4, code[3],"verificationCode");
        BaseClass.sendKeys(verificationCodeBox5, code[4],"verificationCode");
        BaseClass.sendKeys(verificationCodeBox6, code[5],"verificationCode");
        BaseClass.sendKeys(passwordField, password,"password");
        BaseClass.sendKeys(reEnterPasswordField, password,"reEnterPassword");
        BaseClass.clickElement(SubmitBtn,"SubmitBtn");
    }
    private static final By resendBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"Resend (60s)\"]");
    public void clickResendBtn() {
        BaseClass.clickElement(resendBtn,"resendBtn");
    }
    private static final By verificationCodeBox1 = By.xpath("//android.widget.TextView[@text='77dc -']/following-sibling::android.widget.EditText");
    private static final By verificationCodeBox2 = By.xpath("(//android.widget.EditText)[2]");
    private static final By verificationCodeBox3 = By.xpath("(//android.widget.EditText)[3]");
    private static final By verificationCodeBox4 = By.xpath("(//android.widget.EditText)[4]");
    private static final By verificationCodeBox5 = By.xpath("(//android.widget.EditText)[5]");
    private static final By verificationCodeBox6 = By.xpath("(//android.widget.EditText)[6]");
    private static final By passwordField = By.xpath("(//android.widget.EditText)[1]");
    private static final By reEnterPasswordField = By.xpath("(//android.widget.EditText)[2]");

    private static final By SubmitBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"Submit\"]");

    public void clickSubmitBtn()  {
        BaseClass.clickElement(SubmitBtn,"SubmitBtn");
    }
    private static final By errorMessageForUnmatchedPassword = By.xpath("//android.widget.TextView[@text='* Passwords do not match!']");


    public Boolean isErrorMessageDisplayedForUnmatchedPassword() {
        return BaseClass.verifyElementIsDisplayed(errorMessageForUnmatchedPassword,"errorMessageForUnmatchedPassword");
    }
    private static final By errorMessageToEnterPassword = By.xpath("//android.widget.TextView[@text='* Please enter password!']");
    public Boolean isErrorMessageDisplayedToEnterPassword() {
        return BaseClass.verifyElementIsDisplayed(errorMessageToEnterPassword,"errorMessageToEnterPassword");
    }


    private static final By errorMessageForShortPassword = By.xpath("//android.widget.TextView[@text='* Password should have minimum 6 characters']");
    public Boolean isErrorMessageDisplayedForShortPassword () {
        return BaseClass.verifyElementIsDisplayed(errorMessageForShortPassword,"errorMessageForShortPassword");
    }

    public void setSecurityPassword(){
        enterPassword(PropertyUtils.getValue(ConfigProperties.PASSWORD)).clickContinueBtn();
    }


}
