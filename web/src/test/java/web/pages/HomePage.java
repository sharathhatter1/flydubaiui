package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.base.BasePage;

import java.time.LocalDate;
import java.util.Random;

import static com.core.logger.CoreLogger.logger;


public class HomePage extends BasePage {

    WebDriver driver;


    @FindBy(xpath = "//button[normalize-space()='Accept All']")
    WebElement cookiesOption;

    @FindBy(xpath = "(//input[@id='airport-origin'])[1]")
    WebElement sourceCity;

    @FindBy(css = "li[class='full_width active'] span[class='airLocation']")
    WebElement selectOriginCity;


    @FindBy(xpath = "(//input[@id='airport-destination'])[1]")
    WebElement destinationCity;

    @FindBy(xpath = "(//div[@class='lightpick__days'])[2]//div[6]")
    WebElement startDate;

    @FindBy(xpath = "(//div[@class='lightpick__days'])[1]//div[9]")
    WebElement endDate;


    @FindBy(css = "li[class='full_width active'] span[class='airLocation']")
    WebElement selectDestinationCity;

    @FindBy(css = "div[class='section-title']")
    WebElement signUpMenu;

    @FindBy(css = "div#close-popup")
    WebElement popupWindow;

    @FindBy(css = "#edit-submit")
    WebElement signIn;
    @FindBy(css = "nav#block-account-menu>ul>li:nth-of-type(3)>a")
    WebElement topSignIn;
    @FindBy(css = "nav#block-account-menu>ul>li:nth-of-type(2)>a")
    WebElement topSignUp;

    @FindBy(css = "a[href*='membership-info']")
    WebElement memberShipInfoLink;

    @FindBy(xpath = "(//input[@value='Search'])[1]")
    WebElement search;





    @FindBy(xpath = "//div[@class='mat-form-group traveller-details mr-grid-space']//div[@id='travellerData']")
    WebElement passenger;




    /**
     * @Description method for loading driver instance completely with Homepage objects
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (isCookiePOpupDisplayed()) {
            closeCookiePopup();
        }
        //waitForNgDriverToFinish(driver);
    }


    /**
     * To //click origin city
     * @return
     */
    public void sourceCityClick(){
        sourceCity.click();
    }


    /**
     * To //add city name
     * @return
     */
    public void addCity(String originCity){
        sourceCity.sendKeys(originCity);
    }

    /**
     * To select origin city
     * @return
     */
    public void selectOriginCity(){
        waitForElementToBeVisible(selectOriginCity);
        selectOriginCity.click();
    }

    /**
     * To //add city name
     * @return
     */
    public void destinationCityField(String destinationCityParam){
        destinationCity.sendKeys(destinationCityParam);
    }

    /**
     * To //select destination city city name
     * @return
     */
    public void destinationCitySelect(String destinationCityName){
        //System.out.println("----------"+"(//lebel[@class='airCity'][contains(text(), '"+destinationCityName+"')])[1]");
        driver.findElement(By.xpath("(//lebel[@class='airCity'][contains(text(), '"+destinationCityName+"')])[1]")).click();
    }
    /**
     * To get start data of flight
     * @return
     */
    public void selectStartDate(){
        staticWaitInSeconds(3);
        LocalDate currentDate = LocalDate.now();
        logger.info("currentDate:"+currentDate);
        String date = currentDate.toString();
        String[] selectCurrentDate = date.split("-");
        logger.info("selectCurrentDate:"+selectCurrentDate[2]);
        driver.findElement(By.xpath("(//div[contains(text(),'"+selectCurrentDate[2]+"')])[1]")).click();
    }

    /**
     * To get end date of flight
     * @return
     */
    public void selectEndDate(){
        LocalDate currentDate = LocalDate.now();
        logger.info("currentDate:"+currentDate);
        Random random = new Random();
        int randomDays = random.nextInt(30)+1;
        logger.info("RandomDays:"+randomDays);
        LocalDate futureDate = currentDate.plusDays(randomDays);
        logger.info("futureDate:"+futureDate);
        String date1 = futureDate.toString();
        String [] selectFutureDate = date1.split("-");
        logger.info("SelectFutureDate:"+selectFutureDate[2]);
        driver.findElement(By.xpath("(//div[contains(text(),'"+selectFutureDate[2]+"')])[2]")).click();

    }

    public void selectReturnDate(){
        Random random = new Random();
        int randomDays = random.nextInt(5)+1;
        logger.info("RandomDays:"+randomDays);
        int daysToAdd = randomDays;
        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);
        String futureDay = String.valueOf(futureDate.getDayOfMonth());
        logger.info("future date:"+futureDay);

        // Construct dynamic XPath to select the future day within the current month
        String dayXPath = String.format("(//div[contains(text(),'%s')])[2]", futureDay);
        WebElement dateElement = driver.findElement(By.xpath(dayXPath));
        dateElement.click();
    }


    public void clickLogin() {
//        WebElement loginBtn = driver.findElement(By.linkText(""+langData.loginLink+""));
        scrollElementIntoView(driver, topSignIn);
        topSignIn.click();
        waitForElementToBeVisible(signIn);
    }
    public Boolean isPopupWindowDisplayed() {
        try{
            return popupWindow.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


    /**
     * To asser initial cookie popup
     * @return
     */
    public Boolean isCookiePOpupDisplayed() {
        try{
            return cookiesOption.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public void closePopupWindow(){
        popupWindow.click();
    }

    public void closeCookiePopup(){
        cookiesOption.click();
    }

    /**
     * To clcik on flight search
     * @return
     */
    public void clickSearch(){
        waitForVisibilityOfElementLocated(search);
        search.click();
        staticWaitInSeconds(4);
    }






    public String getPassengerDetails(){
        String pass = passenger.getText();
        String[] passengers = pass.split(" ");
        logger.info("passenger:"+passengers[0]);
        return passengers[0];
    }



}
