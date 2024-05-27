package web.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


/**
 * @Description Driver factory provides different browser instances
 * @return- web drivers for various browsers
 */

public class DriverFactory {

    private static DriverFactory INSTANCE = null;
    public static WebDriver driver;

    public static DriverFactory getInstance(){
        if(null == INSTANCE){
            INSTANCE = new DriverFactory();
        }
        return INSTANCE;
    }

    public static WebDriver getDriver(String browserName){
            driver = initDriver(browserName);
        return driver;
    }

    /**
     * @Description Initiate driver based on provided browser name in build.gradle file
     * @param browserName - name of the browser to initiate session
     * @return - Webdriver session
     */
    public static WebDriver initDriver(String browserName) {

        System.out.println("browser name is : " + browserName);
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1440,900");
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver = new ChromeDriver(chromeOptions);
                Duration implicitWaitSF = Duration.ofSeconds(5);
                driver.manage().timeouts().implicitlyWait(implicitWaitSF);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--width=1440");
                firefoxOptions.addArguments("--height=900");
                firefoxOptions.addPreference("javascript.enabled", true);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                System.out.println("plz pass the right browser name.... " + browserName);
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }
}
