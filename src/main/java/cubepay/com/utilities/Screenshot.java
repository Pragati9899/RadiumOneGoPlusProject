package cubepay.com.utilities;
import cubepay.com.constants.FrameworkConstants;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public final class Screenshot {
    private Screenshot(){}
    public static File takeScreenshot(AndroidDriver mobileDriver) throws IOException {
        File source = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(FrameworkConstants.getScreenshotPath());
        FileUtils.copyFile(source, destinationFile);
        return destinationFile;
    }
}
