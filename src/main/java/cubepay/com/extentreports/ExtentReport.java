package cubepay.com.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cubepay.com.constants.FrameworkConstants;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {
    private ExtentReport() {
    }

    private static ExtentReports extent;
    ExtentTest test;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getEXTENTREPORTFILE());
            extent.attachReporter(spark);

            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("GoPlusAutomation_TestReport");
            spark.config().setReportName("Automation test report");
        }
    }

    public static void flushReports() throws IOException {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        Desktop.getDesktop().browse(new File(FrameworkConstants.getEXTENTREPORTFILE()).toURI());

    }

    public static void createTest(String testcasename) {

        ExtentManager.setExtentTest(extent.createTest(testcasename));
    }
}
