package web.tests.smoke;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import web.base.BaseTest;
import web.pages.*;

import static com.core.logger.CoreLogger.logger;
import static org.testng.Assert.assertTrue;


public class FlightTicketBooking extends BaseTest {

        @Parameters("browser")
        @BeforeMethod
        public void setupBeforeMethod(ITestContext iTestContext, String browser) {
                launchBrowserAndOpenUrl();
                setDriverToContext(iTestContext, driver);
        }

        @Description("To validate user is get the PNR")
        @Test(description = "To validate user is able to get PNR via Pay later", groups = {"smoke"})
        public void flightTicketBook() throws Exception {

                String originCityValue =  propMap.get("originCity");
                String destinationCityValue =propMap.get("destinationCity");
                HomePage homePage = new HomePage(driver);
                FlightTicketPage flightTicketPage = new FlightTicketPage(driver);
                PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);
                BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);
                PaymentPage paymentPage = new PaymentPage(driver);

                homePage.sourceCityClick();
                homePage.addCity(propMap.get("originCity"));
                homePage.selectOriginCity();



                homePage.destinationCityField(propMap.get("destinationCity"));
                homePage.destinationCitySelect(propMap.get("destinationCity"));

                Allure.attachment("Source City:- "+originCityValue+ " Destination Cuty:- "+propMap.get("destinationCity"),"Source and Destination City:- "+originCityValue+ " & " +destinationCityValue);

                homePage.selectStartDate();
                homePage.selectReturnDate();

                String passengerDetails = homePage.getPassengerDetails();
                String refTicket = "Reference Ticket";

                homePage.clickSearch();
                logger.info("Select Flight ticket");
                flightTicketPage.clickSelectPlane();
                String startDate = flightTicketPage.getDepartureDate();
                flightTicketPage.clickReturnPlane();
                String endDate = flightTicketPage.getReturnDate();
                flightTicketPage.clickSelectReturnPlane();
                flightTicketPage.clickFlightDetails();
                String flightOneTax = flightTicketPage.getFlightTicketTaxOne();
                String flightTwoTax = flightTicketPage.getFlightTicketTaxTwo();
                personalDetailsPage.clickPersonalDetails();
                String totalAmount = personalDetailsPage.getAmount();
                logger.info("Enter personal details");
                personalDetailsPage.enterPassengerDetails(propMap.get("firstName"),propMap.get("lastName"),propMap.get("email"),propMap.get("phoneNumber"));
                Allure.attachment("Personal Details","FirstName-  "+ propMap.get("firstName") + " LastName - " + propMap.get("lastName")+ " Email - " +propMap.get("email") +" Phone - "+ propMap.get("phoneNumber"));
                logger.info("Validate ticket Details");
                String flightOne = personalDetailsPage.getFlightDateOne();
                String flightTwo = personalDetailsPage.getFlightDateTwo();
                //Asserting dates
                Assert.assertEquals(startDate,flightOne);
                Assert.assertEquals(endDate,flightTwo);
                String Departure = bookingDetailsPage.getFlightOneDeparture();
                String Return = bookingDetailsPage.getReturnFlight();
                Allure.attachment("Asserted Start and Return date","Start and Return date ->"+ startDate + "-"+ endDate);

                //Asserting routes
                Assert.assertEquals(propMap.get("originCity"),Departure);
                Assert.assertEquals(propMap.get("destinationCity"),Return);
                Allure.attachment("Routes","Origin ->" + Departure +" Return ->"+ Return);


                String departureTax = bookingDetailsPage.getDepartureFlightTicketTax();
                bookingDetailsPage.clickReturnTicket();
                String returnTax = bookingDetailsPage.getReturnFlightTicketTax();

                //Asserting Taxes
                Assert.assertEquals(flightOneTax,departureTax);
                Assert.assertEquals(flightTwoTax,returnTax);

                Allure.attachment("Taxes for both the flights verified","Source journey tax - "+ flightOneTax + " Return journey tax -" + flightTwoTax);


                String passengers = bookingDetailsPage.getNoOfPassengerDetails();

                //Asserting Passanger count
                Assert.assertEquals(passengerDetails,passengers);
                Allure.attachment("Passanger count","Passanger count is "+ passengers);

                String amount= bookingDetailsPage.getTotalAmount();
                //Asserting amount
                Assert.assertEquals(totalAmount,amount);
                Allure.attachment("Amount verify","Amount on both pages - "+ amount);
                bookingDetailsPage.clickBooking();
                logger.info("Enter Payment details");
                paymentPage.clickPayment();
                assertTrue(paymentPage.isVerifyReferenceText(),refTicket);
        }
}