package cubepay.com.base;

import com.google.common.util.concurrent.Uninterruptibles;
import cubepay.com.driver.DriverManager;
import cubepay.com.extentreports.ExtentLogger;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static cubepay.com.base.WaitStrategy.*;


public class BaseClass {

    //click methods overloaded
    protected void tap(AndroidDriver driver, WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    protected Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2);
    }

    protected void doubleTap(AndroidDriver driver, WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    protected void zoom(AndroidDriver driver, WebElement element) {

        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() + 100,
                        centerOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() - 100,
                        centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence, sequence2));

    }

    protected void longPress(AndroidDriver driver, WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofSeconds(2)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    protected void swipeOrScroll(AndroidDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endY = (int) (size.getHeight() * 0.25);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }


    public static void clickElement(By by, String elementName) {
        try {
            waitForVisibility(by).click();
            ExtentLogger.pass(elementName + " is clicked successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
    }


    protected void clickElement(WebElement element, String elementName) {
        try {
            waitToClick(element).click();
            ExtentLogger.pass(elementName + " is clicked successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
    }


    public static void sendKeys(By by, String value, String elementName) {
        try {

            waitToBePresent(by).sendKeys(value);
            ExtentLogger.pass(elementName + " is entered successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
    }


    public void sendKeys(WebElement element, String value, String elementName) {

        try {
            waitToBePresent(element).sendKeys(value);
            element.sendKeys(value);
            ExtentLogger.pass(elementName + " is entered successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
    }


    protected void clickIfExists(By by, String elementName) {
        if (!DriverManager.getDriver().findElements(by).isEmpty()) {
            clickElement(DriverManager.getDriver().findElement(by), elementName);
        }
    }


    public static String getText(By by) {
        String text = "";
        try {
            text = waitForVisibility(by).getText().trim();
            ExtentLogger.pass("Get text successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(DriverManager.getDriver().findElement(by).getText() + " is not displayed");
        }
        return text;
    }

    protected String getText(WebElement element) {
        String text = "";
        try {
            text = waitForVisibility(element).getText().trim();
            ExtentLogger.pass("Get text successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(element.getText() + " is not displayed");
        }
        return text;
    }

    public static String getAttribute(By by) {
        String text = "";
        try {
            text = waitForVisibility(by).getAttribute("text");
            ExtentLogger.pass("Get Attribute successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(DriverManager.getDriver().findElement(by).getText() + " is not displayed");
        }
        return text;
    }

    public static boolean verifyElementIsPassword(By by, String elementName) {
        boolean isElementPassword = false;
        try {
            isElementPassword = Boolean.parseBoolean(waitForVisibility(by).getAttribute("password"));
            ExtentLogger.pass(elementName + " is a encrypted");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
        return isElementPassword;
    }

    protected void scollToSpecificElement() {
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"); ---->hardcoding
        //---->Below one is dynamic
        DriverManager.getDriver().findElement(new AppiumBy
                .ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector()" +
                ".textContains(\\\"\"+text+\"\\\").instance(0))")).click();
    }


    protected void clickElementOneUntilElementTwoAppears(By by1, By by2, String elementName) {
        while (!DriverManager.getDriver().findElement(by2).isDisplayed()) {
            clickElement(by1, elementName);
        }
    }

    protected void clickElementUntilElementDisAppears(By by, String elementName) {
        boolean isElementPresent = false;
        int count = 3;
        while ((!isElementPresent) && (count > 0)) {

            try {
                clickElement(by, elementName);
                count--;
                isElementPresent = !(DriverManager.getDriver().findElement(by).isDisplayed());
            } catch (TimeoutException e) {
                e.printStackTrace();
                throw new TimeoutException(elementName + " is not displayed");

            }
        }
    }

    protected boolean verifyElementIsChecked(By by, String elementName) {
        boolean isElementChecked;
        try {
            isElementChecked = Boolean.parseBoolean(waitForVisibility(by).getAttribute("checked"));
            ExtentLogger.pass(elementName + " is checked");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
        return isElementChecked;
    }

    public static boolean verifyElementIsDisplayed(By by, String elementName) {
        boolean isElementDisplayed;

        try {
            isElementDisplayed = Boolean.parseBoolean(waitForVisibility(by).getAttribute("displayed"));
            ExtentLogger.pass(elementName + " is displayed");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }
        return isElementDisplayed;
    }


    public static void clearField(By by, String elementName) {
        try {
            waitForVisibility(by).clear();
            ExtentLogger.pass(elementName + " is cleared successfully");
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException(elementName + " is not displayed");
        }

    }

    public static void waitForExpiration(long seconds) {
        try {
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(seconds));
            ExtentLogger.pass("Wait for " + seconds + "seconds");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static List<WebElement> returnElements(By by) {
        List<WebElement> element = null;

        try {
            element = DriverManager.getDriver().findElements((by));

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static boolean verifyElementIsPresent(By by, String elementName) {
        boolean isElementDisplayed;

        List<WebElement> elements = DriverManager.getDriver().findElements((by));

        if (elements.isEmpty()) {
            isElementDisplayed = false;
        } else {
            isElementDisplayed = true;
            ExtentLogger.pass(elementName + " is displayed");
        }

        return isElementDisplayed;
    }
}