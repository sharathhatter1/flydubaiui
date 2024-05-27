package web.base;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import web.factory.DriverFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import static com.core.logger.CoreLogger.logger;

public class BaseTest {

    public static WebDriver driver;
    protected static String strBrowser;
    protected static String strUrl;
    protected static String strRunType;
    protected static Properties properties;
    public static Map<String, String> propMap;
    protected static String strDataFolderPath;

    /**
     * @Description Get Environment, Browser & App Language from build.gradle file
     * default Environment Value = uat (UAT)
     * This below method gets configuration data like environment and language
     */
    @BeforeMethod
    public void setupMethod() {

        strRunType = System.getProperty("RunType");
        logger.info("setupMethod() -runType is set to - " + strRunType);
        getConfigData();
        Allure.attachment(System.getProperty("browser"),"txt");
    }

    /**
     * To launch the browser and url
     */
    protected void launchBrowserAndOpenUrl() {
        // default Browser Value = Chrome
        strBrowser = System.getProperty("browser");
        logger.info("launchBrowserAndOpenUrl()- browser is set to - " + strBrowser);
        try {
            driver = DriverFactory.getDriver(strBrowser);
        } catch (org.openqa.selenium.NoSuchSessionException noSuchSessionException) {
            logger.info("launchBrowserAndOpenUrl()- exception occurred - NoSuchSessionException ......" + noSuchSessionException);
        }
        driver.manage().deleteAllCookies();
        driver.get("https://qa1-flydubai.np.flydubai.com/en/");
    }

    /**
     * @Description Get default values from build.gradle file
     * This below method sets config data in our local repository
     */
    protected void getConfigData() {
        Path currentRelativePath = Paths.get("");
        String strPathToRoot = currentRelativePath.toAbsolutePath().toString();
        properties = new Properties();
        strDataFolderPath=strPathToRoot + "/src/test/resources/data/";
        File fileTestDataFile = new File(strDataFolderPath + "StaticData.properties");
        logger.info("Data File Path:- " +fileTestDataFile);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileTestDataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fileInputStream != null;
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        propMap = new HashMap<>();
        propMap.putAll(properties.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(),
                        e -> e.getValue().toString())));
    }


    /**
     * @Description This below method quits browser after test script is executed
     * and closes all browser sessions opened
     */
    @AfterMethod(alwaysRun = true)
    public WebDriver tearDownAfterTest(ITestContext iTestContext) {
        boolean hasQuit = driver.toString().contains("(null)");
        System.out.println(hasQuit);
        if (!hasQuit) {
         driver.quit();
         return DriverFactory.driver;
        }
        return null;
    }

    /**
     * @Description This below method sets browser webDriver session into context driver
     * which can used in other classes/listeners
     */
    public static void setDriverToContext(ITestContext iTestContext, WebDriver driver) {
        iTestContext.setAttribute("WebDriver", driver);
    }


}
