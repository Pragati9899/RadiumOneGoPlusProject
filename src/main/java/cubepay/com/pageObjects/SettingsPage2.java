package cubepay.com.pageObjects;

import cubepay.com.base.BaseClass;
import org.openqa.selenium.By;

public class SettingsPage2 {
    private static final By settingsPageTitle = By.xpath("//android.widget.TextView[@text=\"Settings\"]\n");

    public  String getSettingsPageTitle(){
        return BaseClass.getText(settingsPageTitle);
    }
}
