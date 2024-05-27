package web.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import web.base.BasePage;
import static com.core.logger.CoreLogger.logger;


public class PaymentPage extends BasePage {

    WebDriver driver;

    /**
     * @Description method for loading driver instance completely with PaymentPage objects
     */
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //waitForNgDriverToFinish(driver);
    }

    @FindBy(xpath = "(//div[@class='payLaterTitle tranBlueBtn ng-star-inserted'])[1]")
    WebElement payLater;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement continuePay;
    @FindBy(css = "div[class='modal-footer farecontainer__footer'] button[type='submit']")
    WebElement termsAndCondition;

    @FindBy(xpath = "//label[@id='flightBookingReferenceText']")
    WebElement referenceText;

    @FindBy(xpath = "//span[@id='spanglobalExceptionContinueButtonId']")
    WebElement errorAlert;

    /**
     * To click on paylater option
     * @return
     */
    public void clickPayment() {

        staticWaitInSeconds(5);
//        waitForElementToBeClickable(errorAlert);
//        if (errorAlert.isDisplayed()) {
//            Allure.addAttachment("Error in processing booking", "Please check and start process again");
//            Assert.assertFalse(errorAlert.isDisplayed(), "Error in processing booking");
//        } else {
//        waitForVisibilityOfElementLocated(payLater);
            payLater.isDisplayed();
            payLater.click();
            scrollElementIntoView(driver, continuePay);
            logger.info("Continue payment");
            continuePay.click();
            termsAndCondition.click();
        }
//    }
    /**
     * To assert the pnr text
     * @return
     */
    public boolean isVerifyReferenceText(){
        staticWaitInSeconds(3);
        logger.info("Reference Ticket");
        return referenceText.isDisplayed();
    }





}
