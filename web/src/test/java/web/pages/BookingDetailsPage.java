package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.base.BasePage;

import static com.core.logger.CoreLogger.logger;


public class BookingDetailsPage extends BasePage {

    WebDriver driver;

    /**
     * @Description method for loading driver instance completely with BookingDetailsPage objects
     */
    public BookingDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //waitForNgDriverToFinish(driver);
    }

    @FindBy(xpath = "//body[1]/fz-root[1]/div[1]/div[1]/fz-review[1]/fz-review-page[1]/section[1]/section[1]/fz-trip-details-review[1]/div[1]/fz-review-trip-details[1]/div[1]/div[2]/div[1]/div[2]/div[1]/fz-flight-details-review[1]/div[1]/div[1]/div[1]/div[4]/div[1]/span[1]/fz-static-label[1]/label[1]")
    WebElement flightOneDep;

    @FindBy(xpath = "//body[1]/fz-root[1]/div[1]/div[1]/fz-review[1]/fz-review-page[1]/section[1]/section[1]/fz-trip-details-review[1]/div[2]/fz-review-trip-details[1]/div[1]/div[2]/div[1]/div[2]/div[1]/fz-flight-details-review[1]/div[1]/div[1]/div[1]/div[4]/div[1]/span[1]/fz-static-label[1]/label[1]")
    WebElement flightTwoDep;

    @FindBy(xpath = "//label[@id='lblFareAndTaxMessage']")
    WebElement noOfPassengers;

    @FindBy(xpath = "(//span[@id='span'])[1]")
    WebElement continuePayment;

    @FindBy(xpath = "//div[@id='totalAmount']//label[@id='lblAmount']")
    WebElement totalAmount;

    @FindBy(xpath = "(//label[@id=\"lblAmount\"])[5]")
    WebElement departureFlightTax;

    @FindBy(xpath = "(//div[@class='flightSummaryLabel']//label[@class='ng-star-inserted'])[2]")
    WebElement returnFlight;

    @FindBy(xpath = "(//label[@id=\"lblAmount\"])[5]")
    WebElement returnFlightTax;

    /**
     * To get on routes for the deaprture flight
     * @return
     */
    public String getFlightOneDeparture(){
        String Departure = flightOneDep.getText();
        return Departure;
    }

    /**
     * To get on routes for the return flight
     * @return
     */
    public String getReturnFlight(){
        String returnFlight = flightTwoDep.getText();
        return returnFlight;
    }

    /**
     * To get on number of passengers details
     * @return
     */
    public String getNoOfPassengerDetails(){
        scrollElementIntoView(driver,noOfPassengers);
        String numbers = noOfPassengers.getText();
        String [] passengers = numbers.split(" ");
        return passengers[0];
    }

    /**
     * To get on total amount for the flight journey
     * @return
     */
    public String getTotalAmount(){
        staticWaitInSeconds(2);
        String amount = totalAmount.getText();
        logger.info("amount:"+amount);
        return amount;
    }

    /**
     * To click on continue patment
     * @return
     */
    public void clickBooking(){
        staticWaitInSeconds(1);
        scrollElementIntoView(driver,continuePayment);
        continuePayment.click();
        staticWaitInSeconds(5);
    }

    /**
     * To get on tax for the deaprture flight
     * @return
     */

    public String getDepartureFlightTicketTax(){
        String departureTax = departureFlightTax.getText();
        logger.info("departureTax:"+departureTax);
        return departureTax;
    }

    /**
     * To click on return flight ticket
     * @return
     */
    public void clickReturnTicket(){
        staticWaitInSeconds(2);
        returnFlight.click();
    }

    /**
     * To get on tax for the return flight
     * @return
     */
    public String getReturnFlightTicketTax(){
        String returnTax = returnFlightTax.getText();
        logger.info("returnTax:"+returnTax);
        return returnTax;
    }

}
