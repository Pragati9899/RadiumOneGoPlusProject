package randomPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExtentReportDemo {

    private static final String CODE1 = "";
    private static final String CODE2 = "";
    ExtentReports extent;
    ExtentSparkReporter spark;

    @BeforeSuite
    public void reportSetup(){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
        
        extent.createTest("ScreenCapture")
                .addScreenCaptureFromPath("extent.png")
                .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());
        extent.createTest("LogLevels")
                .info("info")
                .pass("pass")
                .warning("warn")
                .skip("skip")
                .fail("fail");

        extent.createTest("CodeBlock").generateLog(
                Status.PASS,
                MarkupHelper.createCodeBlock(CODE1, CODE2));

        extent.createTest("ParentWithChild")
                .createNode("Child")
                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");

        extent.createTest("Tags")
                .assignCategory("MyTag")
                .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");

        extent.createTest("Authors")
                .assignAuthor("TheAuthor")
                .pass("This test 'Authors' was assigned by a special kind of author tag.");

        extent.createTest("Devices")
                .assignDevice("TheDevice")
                .pass("This test 'Devices' was assigned by a special kind of devices tag.");

        extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
                .fail(new RuntimeException("A runtime exception occurred!"));

    }
    @AfterSuite
    public void tearDown(){
        extent.flush();
    }
}
