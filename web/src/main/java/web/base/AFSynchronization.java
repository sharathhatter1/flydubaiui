package web.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.factory.DriverFactory;

import java.time.Duration;

public class AFSynchronization {
    protected   int STATIC_WAIT = 3;
    protected  final int DURATION_IN_SECONDS = 30;
    private static final WebDriver driver = DriverFactory.driver;

    /**
     * @Description This method is used for waiting for element to be visible in webpage
     */
    public void waitForElementToBeVisible(WebElement element){
        Duration maxTimeOut = Duration.ofSeconds(DURATION_IN_SECONDS);
        Wait<WebDriver> wait = new WebDriverWait(driver,maxTimeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * @Description This method is used for waiting for element to be clickable in webpage
     */
    public void waitForElementToBeClickable(WebElement element){
        Duration maxTimeOut = Duration.ofSeconds(DURATION_IN_SECONDS);
        Wait<WebDriver> wait = new WebDriverWait(driver,maxTimeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * @Description This method is used for waiting for element to be visible in webpage
     */
    public void waitForVisibilityOfElementLocated(WebElement element){
        Duration maxTimeOut = Duration.ofSeconds(DURATION_IN_SECONDS);
        Wait<WebDriver> wait = new WebDriverWait(driver,maxTimeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * @Description This method is used for scrolling for element in webpage
     */
    public void scrollElementIntoView(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        staticWaitInSeconds(STATIC_WAIT);
    }

    /**
     * @Description This method is used for waiting with certain time by halting the script execution
     */
    public void staticWaitInSeconds(int seconds){
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param webDriver
     * @param element
     * @Description This method is implemented to click on element using javascript.
     */
    public static void clickUsingJavaScript(WebDriver webDriver, WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new RuntimeException(element + " not found in the page.");
        }

    }

}
