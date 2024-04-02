package cubepay.com.testcases;

import cubepay.com.base.BaseClass;
import cubepay.com.basetest.BaseTest;
import cubepay.com.enums.ConfigProperties;
import cubepay.com.pageObjects.LoginPage;
import cubepay.com.pageObjects.MainScreenPage;
import cubepay.com.utilities.PropertyUtils;
import cubepay.com.utilities.ReadConfigFile;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    LoginPage lp = new LoginPage();
    MainScreenPage mp = new MainScreenPage();


    @Test
    public void loginByRegisteredNumber() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        Assertions.assertThat(lp.isVerificationPageDisplayed()).isTrue();
    }

    @Test
    public void loginByUnRegisteredNumber() {
        lp.setMobileNumber(ConfigProperties.UNREGISTEREDMOBILENO);
        Assertions.assertThat(lp.isErrorMessageDisplayedForUnregisteredNum()).isTrue();
    }

    @Test
    public void loginByNullMobileNo() {
        lp.setEnterMobileNoField("").clickSignInBtn();
        Assertions.assertThat(lp.isErrorMessageDisplayedForInvalidNumber()).isTrue();
    }

    @Test
    public void loginByNumberLessThan_8Digit() {
        lp.setMobileNumber(ConfigProperties.SHORTMOBILENO);
        Assertions.assertThat(lp.isErrorMessageDisplayedForInvalidNumber()).isTrue();
    }

    @Test
    public void verifyRetryBlockPeriodErrorMessage() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        lp.clickBackBtn();
        lp.clickSignInBtn();
        Assertions.assertThat(lp.isErrorMessageDisplayedForRetryBlockPeriod()).isTrue();
    }

    @Test
    public void verifyInstantRelogin() {
        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO2);
        BaseClass.waitForExpiration(10);
        lp.clickBackBtn();
        lp.setEnterMobileNoField(PropertyUtils.getValue(ConfigProperties.REGISTEREDMOBILENO));
        BaseClass.waitForExpiration(60);
        lp.clickSignInBtn();
        lp.setVerificationCode();
        lp.handleSecurityPasswordPage();
        Assertions.assertThat(mp.isUserNameDisplayed()).isTrue();
    }

    @Test
    public void loginByEditedNumber() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO2);
        String[] a = {"9", "2", "3", "4", "5", "6"};
        lp.setVerificationCode(a);
        BaseClass.waitForExpiration(10);
        lp.clickBackBtn();
        lp.setEnterMobileNoField(PropertyUtils.getValue(ConfigProperties.REGISTEREDMOBILENO));
        BaseClass.waitForExpiration(60);
        lp.clickSignInBtn();

        Assertions.assertThat(lp.isVerificationPageDisplayed()).isTrue();
    }

    @Test
    public void verifyAutofocusForVerificationField() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            Assertions.assertThat(lp.verifyAutofocus()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyKeyboardOpensForVerificationField() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            Assertions.assertThat(lp.isKeyboardDisplayed()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void loginWithWrongVerificationCode() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        String[] a = {"9", "2", "3", "4", "5", "6"};
        lp.setVerificationCode(a);
        Assertions.assertThat(lp.checkErrorMessageIsDisplayedForWrongOtp()).isTrue();
    }


    @Test
    public void verifyVerificationCodeEncrypted() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        String[] a = {"9", "2", "3", "4", "5", "6"};
        lp.setVerificationCode(a);
        Assertions.assertThat(lp.isVerificationCodeEncrypted()).isTrue();
    }

    @Test
    public void loginByResendCode() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        String[] a = {"8", "2", "3", "4", "5", "6"};
        lp.setVerificationCode(a);
        BaseClass.waitForExpiration(60);
        lp.clickResendBtn();
        lp.setVerificationCode();
        lp.handleSecurityPasswordPage();
        Assertions.assertThat(mp.isUserNameDisplayed()).isTrue();
    }

    @Test
    public void verifyResendBtnDisplayAfterPassingTimer() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            if (lp.isResendTimerDisplayed()) {
                BaseClass.waitForExpiration(65);
                Assertions.assertThat(lp.isDisplayedResendBtn()).isTrue();
            }
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyBackButtonIsDisplayedOnVerificationPage() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            Assertions.assertThat(lp.isBackBtnDisplayed()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyReloginByUsingBackButtonAfterPassingRetryBlockPeriod() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            BaseClass.waitForExpiration(60);
            lp.clickBackBtn();
            lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO2);
            Assertions.assertThat(lp.isVerificationPagePresent()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyReloginByUsingBackButtonAfterPassingExpiryTimeOfOTP() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            BaseClass.waitForExpiration(185);
            lp.clickBackBtn();
            lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO2);
            Assertions.assertThat(lp.isVerificationPagePresent()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyNavigationToLoginPageByClickingBackButton() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            lp.clickBackBtn();
            lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO2);
            Assertions.assertThat(lp.isLoginPageTextDisplayed()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyResendTimer() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            Assertions.assertThat(lp.isResendTimerDisplayed()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyVerificationFieldNotAcceptsAlphabets() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            String[] a1 = {"a", "b", "c", "d", "d", "f"};
            lp.setVerificationCode(a1);
            Assertions.assertThat(lp.isVerificationFieldEmpty()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyVerificationFieldNotAcceptsSpecialChar() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            String[] a1 = {"@", "$", "!", ">", "?", "&"};
            lp.setVerificationCode(a1);
            Assertions.assertThat(lp.isVerificationFieldEmpty()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyVerificationFieldAcceptsNumerals() {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            String[] a1 = {"1", "2", "3", "4", "5", "6"};
            lp.setVerificationCode(a1);
            lp.handleSecurityPasswordPage();
            Assertions.assertThat(mp.isUserNameDisplayed()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test(priority = -1)
    public void loginByExpiredVerificationCode() throws InterruptedException {
        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            BaseClass.waitForExpiration(185);
            lp.setVerificationCode();
            Assertions.assertThat(lp.checkErrorMessageIsDisplayedForWrongOtp()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyErrorMessageForNullSecurityPassword() {
       lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);
        lp.setEnterPasswordField("")
                .setReEnterPasswordField("")
                .clickSubmitBtn();

        Assertions.assertThat(lp.isErrorMessageDisplayedToEnterPassword()).isTrue();
    }

    @Test
    public void verifyErrorMessageForSecurityPasswordLessThan6Digit() {
        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);
        lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.WRONGPASSWORD))
                .clickSubmitBtn();

        Assertions.assertThat(lp.isErrorMessageDisplayedForShortPassword()).isTrue();
    }

    @Test
    public void verifySettingUpMatchedSecurityPassword() {
        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);
        lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                .clickSubmitBtn();

        Assertions.assertThat(mp.isUserNameDisplayed()).isTrue();
    }

    @Test
    public void verifySettingUpSecurityPasswordOf10Digit() {

        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);

        lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.TENDIGITPASSWORD))
                .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.TENDIGITPASSWORD))
                .clickSubmitBtn();

        Assertions.assertThat(mp.isUserNameDisplayed()).isTrue();
    }

    @Test
    public void verifyErrorMessageForUnMatchedSecurityPassword() {

        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);
        lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.WRONGPASSWORD))
                .clickSubmitBtn();

        Assertions.assertThat(lp.isErrorMessageDisplayedForUnmatchedPassword()).isTrue();
    }

    @Test
    public void verifyEnteredSecurityPasswordsEncrypted() {
        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);
        lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.WRONGPASSWORD))
                .clickSubmitBtn();
        Assertions.assertThat(lp.isSecurityPasswordsEncrypted()).isTrue();
    }

    @Test
    public void verifyPasswordFieldNotAcceptsAlphabets() {

        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            lp.setVerificationCode();
            lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.ALPHAPASSWORD))
                    .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.ALPHAPASSWORD));
            Assertions.assertThat(lp.isPasswordFieldEmpty()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyPasswordFieldNotAcceptsSpecialChar() {

        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            lp.setVerificationCode();
            lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.SPECCHARPASSWORD))
                    .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.SPECCHARPASSWORD));
            Assertions.assertThat(lp.isPasswordFieldEmpty()).isTrue();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }

    @Test
    public void verifyPasswordFieldAcceptsNumerals() {

        lp.setMobileNumber(ConfigProperties.REGISTEREDMOBILENO);
        if (lp.isVerificationPagePresent()) {
            lp.setVerificationCode();
            lp.setEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD))
                    .setReEnterPasswordField(PropertyUtils.getValue(ConfigProperties.PASSWORD));
            Assertions.assertThat(lp.isPasswordFieldEmpty()).isFalse();
        } else {
            Assertions.fail("Verification page is not displayed");
        }
    }
}

