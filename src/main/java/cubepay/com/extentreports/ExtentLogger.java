package cubepay.com.extentreports;

import com.aventstack.extentreports.MediaEntityBuilder;
import cubepay.com.enums.ConfigProperties;
import cubepay.com.utilities.PropertyUtils;
import cubepay.com.utilities.ScreenshotBase64Utils;

public final class ExtentLogger {
    private ExtentLogger() {

    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void pass(String message, boolean isScreenshotRequired) {
        if (PropertyUtils.getValue(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes") && isScreenshotRequired) {
            ExtentManager.getExtentTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotBase64Utils.getBase64Image()).build());
        } else {
            pass(message);
        }
    }

    public static void fail(String message, boolean isScreenshotRequired) {
        if (PropertyUtils.getValue(ConfigProperties.FAILEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes") && isScreenshotRequired) {
            ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotBase64Utils.getBase64Image()).build());
        } else {
            fail(message);
        }
    }

    public static void skip(String message, boolean isScreenshotRequired) {
        if (PropertyUtils.getValue(ConfigProperties.SKIPPEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes") && isScreenshotRequired) {
            ExtentManager.getExtentTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotBase64Utils.getBase64Image()).build());
        } else {
            skip(message);
        }
    }
}


