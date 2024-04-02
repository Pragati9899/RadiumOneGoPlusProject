package cubepay.com.base;
import cubepay.com.constants.FrameworkConstants;
import cubepay.com.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class WaitStrategy {
    private WaitStrategy(){}
    public static WebElement waitToClick(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()));
        return wait.until(ExpectedConditions.elementToBeClickable(DriverManager.getDriver().findElement(by)));
    }

    public static WebElement waitToClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitToBePresent(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitToBePresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()));
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    public static WebElement waitForVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getEXPLICITWAIT()));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

}
