package cubepay.com.testcases;

import cubepay.com.basetest.BaseTest;
import cubepay.com.enums.ConfigProperties;
import cubepay.com.pageObjects.*;
import cubepay.com.utilities.PropertyUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {
    ForgotPassword fp = new ForgotPassword();
    LoginPage lp = new LoginPage();
    MainScreenPage mp = new MainScreenPage();
    SettingsPage2 sp = new SettingsPage2();
    Permissions per = new Permissions();

    @Test
    public void testEnterSecurityPassword() {

        mp.navigateToMainScreenIfRequired();
        lp.setLoginCredentials(ConfigProperties.REGISTEREDMOBILENO);
        lp.handleSecurityPasswordPage();
        per.handlePermissions();
        mp.clickSettingsBtn();
        fp.setSecurityPassword();

        Assertions.assertThat(sp.getSettingsPageTitle()).isEqualTo("Settings");
    }
}
