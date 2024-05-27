package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.base.BasePage;

import static com.core.logger.CoreLogger.logger;
public class FlightTicketPage extends BasePage {

    WebDriver driver;

    /**
     * @Description method for loading driver instance completely with FlightTicketPage objects
     */
    public FlightTicketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //waitForNgDriverToFinish(driver);
    }

    @FindBy(xpath = "(//img[@class='selectDropDownImageFlight ng-star-inserted'])[1]")
    WebElement selectPlane;

    @FindBy(xpath = "(//button[@type='button'])[11]")
    WebElement selectFare;

    @FindBy(xpath = "(//button[@type='button'])[23]")
    WebElement returnFare;

    @FindBy(xpath = "(//div[@class='menu-bg-copy oe-card-width ng-star-inserted'])[1]")
    WebElement bag;

    @FindBy(xpath = "(//div[@class='imageClass'])[1]")
    WebElement selectWeightBag;

    @FindBy(xpath = "(//img[@class='selectDropDownImageFlight ng-star-inserted'])[4]")
    WebElement selectReturnPlane;

    @FindBy(xpath = "(//p[@class='default-date-class ng-star-inserted'])[1]")
    WebElement departureDate;

    @FindBy(xpath = "(//p[@class='default-date-class ng-star-inserted'])[3]")
    WebElement returnDate;

    @FindBy(xpath = "(//label[@id='flightDetailsAccordion'])[1]")
    WebElement flightDetails;

    @FindBy(xpath = "(//div[@class='depart-journey journeyDetailsDept ng-star-inserted']//div[@class='fareBreakdownSegments ng-star-inserted']//label[@id='lblAmount'])[1]")
    WebElement flightTicketTaxOne;

    @FindBy(xpath = "(//label[@id='lblAmount'])[15]")
    WebElement flightTicketTaxTwo;

    /**
     * To click on flight
     * @return
     */
    public void clickSelectPlane(){
        waitForElementToBeVisible(selectPlane);
        selectPlane.click();
        selectFare.click();
    }

    /**
     * To get departure date of flight
     * @return
     */
    public String getDepartureDate(){
        staticWaitInSeconds(5);
        String date = departureDate.getText();
        System.out.println("Date:"+date);
        return date;
    }

    /**
     * To select details of return flight
     * @return
     */
    public void clickReturnPlane(){
        waitForElementToBeVisible(selectReturnPlane);
        selectReturnPlane.click();
    }

    /**
     * To get return date of flight
     * @return
     */
    public String getReturnDate(){
        staticWaitInSeconds(5);
        String date = returnDate.getText();
        logger.info("ReturnDate:"+date);
        return date;
    }
    /**
     * To select an option for the fare
     * @return
     */
    public void clickSelectReturnPlane(){
        staticWaitInSeconds(3);
        //waitForElementToBeVisible(selectFare);
        clickUsingJavaScript(driver,selectFare);
        //selectFare.click();
        staticWaitInSeconds(4);
        waitForElementToBeClickable(bag);
        bag.click();
        scrollElementIntoView(driver,selectWeightBag);
        selectWeightBag.click();
        staticWaitInSeconds(2);
        selectWeightBag.click();
    }

    /**
     * To click on flight details to see selected flight for taxes, amount
     * @return
     */
    public void clickFlightDetails(){
        staticWaitInSeconds(2);
        flightDetails.click();
    }

    /**
     * To get on flight tax of the sourceflight
     * @return
     */
    public String getFlightTicketTaxOne(){
        String taxOne = flightTicketTaxOne.getText();
        logger.info("Tax:"+taxOne);
        return taxOne;
    }

    /**
     * To get on flight tax of the destinationflight
     * @return
     */
    public String getFlightTicketTaxTwo(){
        String taxTwo = flightTicketTaxTwo.getText();
        logger.info("Tax2:"+taxTwo);
        return taxTwo;
    }

}
