package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.base.BasePage;

import static com.core.logger.CoreLogger.logger;

public class PersonalDetailsPage extends BasePage {

    WebDriver driver;

    /**
     * @Description method for loading driver instance completely with PersonalDetailsPage objects
     */
    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //waitForNgDriverToFinish(driver);
    }

    @FindBy(xpath = "(//label[@id='lblAmount'])[1]")
    WebElement amount;

    @FindBy(xpath = "(//span[contains(text(),'Continue to passenger details')])[1]")
    WebElement passengerDetails;

    @FindBy(xpath = "(//input[@id='First_Name'])[1]")
    WebElement firstName;

    @FindBy(xpath = "(//input[@id='Last_Name'])[1]")
    WebElement lastName;

    @FindBy(xpath = "(//input[@id='Email_Address'])[1]")
    WebElement emailId;

    @FindBy(xpath = "(//mat-radio-button[@id='Male.Text'])[1]")
    WebElement male;

    @FindBy(xpath = "(//div[@class='mat-form-field-infix ng-tns-c24-61'])[1]")
    WebElement code;

    @FindBy(xpath = "(//div[@id='countryValueCode'])[4]")
    WebElement selectCountry;

    @FindBy(xpath = "(//input[@id='Mobile_Number'])[1]")
    WebElement mobileNumber;

    @FindBy(xpath = "//div[@class='continuePayment']//button[@type='button']")
    WebElement reviewBooking;

    @FindBy(xpath = "(//div[@class='flight-date departure-flight-date']//p[@class='default-date-class'])[1]")
    WebElement flightDateOne;

    @FindBy(xpath = "(//div[@class='flight-date departure-flight-date']//p[@class='default-date-class'])[2]")
    WebElement flightDateTwo;


    /**
     * To get the total amount
     * @return
     */
    public String getAmount(){
        staticWaitInSeconds(2);
        String total = amount.getText();
        System.out.println("Total:"+total);
        return total;
    }

    /**
     * To click on the passenger details link
     * @return
     */
    public void clickPersonalDetails(){
        passengerDetails.click();
    }

    /**
     * To enter passenger details and continue
     * @return
     */
    public void enterPassengerDetails(String fName, String lName, String email, String mobile){
        waitForElementToBeVisible(firstName);
        firstName.click();
        logger.info("FirstName:"+fName);
        firstName.sendKeys(fName);
        lastName.click();
        logger.info("LastName:"+lName);
        lastName.sendKeys(lName);
        emailId.click();
        logger.info("emailId:"+email);
        emailId.sendKeys(email);
        staticWaitInSeconds(2);
        /*waitForElementToBeClickable(male);
        waitForElementToBeClickable(code);*/
        male.click();
        staticWaitInSeconds(2);
        code.click();
        selectCountry.click();
        mobileNumber.click();
        logger.info("mobileNumber:"+mobile);
        mobileNumber.sendKeys(mobile);
//        scrollElementIntoView(driver,reviewBooking);
        staticWaitInSeconds(3);
        clickUsingJavaScript(driver,reviewBooking);
        staticWaitInSeconds(3);
    }

    /**
     * To get the date of departure
     * @return
     */
    public String getFlightDateOne(){
        staticWaitInSeconds(2);
        String flightOne = flightDateOne.getText();
        String[] date = flightOne.split(" ");
        logger.info("date:"+date[0]);
        return date[0];
    }
    /**
     * To get the date of return
     * @return
     */
    public String getFlightDateTwo(){
        staticWaitInSeconds(2);
        scrollElementIntoView(driver,flightDateTwo);
        String flightTwo = flightDateTwo.getText();
        String[] dateOne = flightTwo.split(" ");
        logger.info("Date:"+dateOne[0]);
        return dateOne[0];
    }



}
