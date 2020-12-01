package com.travelers.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.travelers.helpers.ExcelHelper;
import com.travelers.helpers.TestListener;
import com.travelers.pages.HomePage;
import com.travelers.pages.ResultPage;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.travelers.utils.PropertyLoader.loadProperties;

@Listeners(TestListener.class)
public class SearchHotelTest extends BaseSeleniumTest {

    @Test(dataProvider = "getData")
    public void searchHotelTest(String city, String checkInDate, String checkOutDate, String fHotel, String fPrice,
                                String sHotel, String sPrice, String tHotel, String tPrice) throws ConfigurationException, IOException {

        Configuration config = loadProperties();
        ExtentTest test = reports.createTest("Search Hotel test");
        driver.get(config.getString("baseURL"));
        HomePage homePage = new HomePage(driver);

        test.log(Status.INFO,"On PHP Travelers Home Page", getScreenshot());
        homePage.setCityHotel(city)
                .setDateRange(checkInDate, checkOutDate)
                .openTravellersModal()
                .addAdult()
                .addChild()
                .addChild();
        String infoText = "Before performing search for city %s check in %s and check out %s";
        test.log(Status.INFO, String.format(infoText, city, checkInDate, checkOutDate), getScreenshot());
        ResultPage resultPage = homePage.performSearch();
        /*Annotations
        Before Suite
            before test
                before class
                    before method
                        first test
                    after method
                    before method
                        second test
                    after method
                after class
              test z drugiej klasy
            after test
        after Suite
        */

        test.log(Status.INFO,"Checking hotel names and prices", getScreenshot());
        List<String> hotelNames = resultPage.getHotelNames();
        Assert.assertEquals(fHotel,hotelNames.get(0));
        Assert.assertEquals(sHotel,hotelNames.get(1));
        Assert.assertEquals(tHotel,hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

        List<String> hotelPrices = resultPage.getHotelPrices();
        Assert.assertEquals(fPrice,hotelPrices.get(0));
        Assert.assertEquals(sPrice,hotelPrices.get(1));
        Assert.assertEquals(tPrice,hotelPrices.get(2));
        Assert.assertEquals("$150",hotelPrices.get(3));
        test.log(Status.PASS, "Hotel Search test passed", getScreenshot());
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = null;
        try {
            data = ExcelHelper.readExcelFile(new File("src//test//resources//files//Dane.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
