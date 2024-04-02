package cubepay.com.pageObjects;
import cubepay.com.base.BaseClass;
import org.openqa.selenium.By;

public class Permissions {


    private static final By permissions_popup= By.xpath( "//android.widget.LinearLayout");
    private static final By whileAppInUse= By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private static final By oneTimeOnly= By.id( "com.android.permissioncontroller:id/permission_allow_one_time_button");
    private static final By dontAllow= By.id( "com.android.permissioncontroller:id/permission_deny_button");

    // id.widget.Button[@text='While using the app']
    //android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']
    //com.android.permissioncontroller:id/permission_allow_foreground_only_button

    public boolean isPermissionDisplayed() {
        return BaseClass.verifyElementIsPresent(permissions_popup, "permissions_popup");

    }
    public void setWhileAppInUsePermission() {
        BaseClass.clickElement(whileAppInUse,"whileAppInUse");
    }
    public void setOneTimeOnlyPermission() {
        BaseClass.clickElement(oneTimeOnly,"oneTimeOnly");
    }

    public void setDontAllowPermission() {
        BaseClass.clickElement(dontAllow,"dontAllow");
    }




    //----------Notification Popup-------------------
    private static final By allowBtn= By.id( "com.android.permissioncontroller:id/permission_allow_button");
    private static final By denyBtn= By.id( "com.android.permissioncontroller:id/permission_deny_button");

    public void allowNotificationPopup() {
        BaseClass.clickElement(allowBtn,"allowBtn");
    }

    public void denyNotificationPopup() {
        BaseClass.clickElement(denyBtn,"denyBtn");
    }

    public void handlePermissions() {
        if (isPermissionDisplayed()) {
            allowNotificationPopup();
            setWhileAppInUsePermission();
            setWhileAppInUsePermission();
        }
    }
}
