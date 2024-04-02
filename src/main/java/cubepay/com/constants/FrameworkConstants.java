package cubepay.com.constants;

import cubepay.com.enums.ConfigProperties;
import cubepay.com.utilities.PropertyUtils;

public final class FrameworkConstants {
    private FrameworkConstants() {
    }

    private static final String PROJECT_PATH = System.getProperty("user.dir");

    private static final String EXTENTREPORTFOLDERPATH = PROJECT_PATH + "/extent-test-output/";
    private static String extentreportfilepath = "";

    public static String getEXTENTREPORTFILE() {
        if (extentreportfilepath.isEmpty()) {
            extentreportfilepath = createReportPath();
        }
        return extentreportfilepath;
    }

    private static String createReportPath() {
        if (PropertyUtils.getValue(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("yes")) {
            return EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "/index.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }

    private static final Integer IMPLICITWAIT = 10;

    public static Integer getIMPLICITWAIT() {
        return IMPLICITWAIT;
    }

    private static final Integer EXPLICITWAIT = 50;

    public static Integer getEXPLICITWAIT() {
        return EXPLICITWAIT;
    }

    private static final String CHROMEDRIVERPATH = PROJECT_PATH + "\\src\\test\\resources\\chromedriver.exe";

    public static String getCHROMEDRIVERPATH() {
        return CHROMEDRIVERPATH;
    }

    private static final String SCREENSHOT_PATH = PROJECT_PATH + "\\src\\test\\resources\\Screenshots\\screenshot.png";

    public static String getScreenshotPath() {
        return SCREENSHOT_PATH;
    }

    private static final String READCONFIG_PATH = PROJECT_PATH + "\\src\\test\\resources\\config.properties";

    public static String getConfigFilePath() {
        return READCONFIG_PATH;
    }

    private static final String READCONFIGJSON_PATH = PROJECT_PATH + "\\src\\test\\resources\\config\\Config.json";

    public static String getConfigJsonFilePath() {
        return READCONFIGJSON_PATH;
    }

    private static final String READEXCELFILE_PATH = PROJECT_PATH + "\\Book 17.xlsx";

    public static String getReadexcelfilePath() {
        return READEXCELFILE_PATH;
    }

    private static final String RUNMANAGEREXCELFILE_PATH = PROJECT_PATH + "\\src\\test\\resources\\excelFiles\\RunManager.xlsx";

    public static String getRunmanagerexcelfilepath() {
        return RUNMANAGEREXCELFILE_PATH;
    }
}
