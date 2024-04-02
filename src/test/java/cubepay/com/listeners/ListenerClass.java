package cubepay.com.listeners;

import cubepay.com.extentreports.ExtentLogger;
import cubepay.com.extentreports.ExtentReport;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

public class ListenerClass implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        try {
            ExtentReport.flushReports();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExtentLogger.pass(result.getMethod().getMethodName() + " is passed", false);
        } catch (ScreenshotException e) {
            e.printStackTrace();
            ExtentLogger.pass(result.getMethod().getMethodName() + " is passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            ExtentLogger.fail(result.getMethod().getMethodName() + " is failed", true);

        } catch (ScreenshotException e) {
            e.printStackTrace();
            ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
            ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");
        }
        String throwableMessage = result.getThrowable().toString();
        String[] throwableLines = throwableMessage.split("\\n");
        for (String line : throwableLines) {
            ExtentLogger.fail(line);
        }
        ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        try {
            ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped", false);

        } catch (ScreenshotException e) {
            ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped");
            e.printStackTrace();
        }
    }

}
