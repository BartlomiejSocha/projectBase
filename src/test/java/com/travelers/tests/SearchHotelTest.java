package com.travelers.tests;

import com.aventstack.extentreports.ExtentTest;
import com.travelers.helpers.ExcelHelper;
import com.travelers.helpers.TestListener;
import com.travelers.pages.HomePage;
import com.travelers.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Listeners(TestListener.class)
public class SearchHotelTest extends BaseSeleniumTest {

    @Test(dataProvider = "getData")
    public void searchHotelTest(String city, String checkInDate, String checkOutDate, String fHotel, String fPrice,
                                String sHotel, String sPrice, String tHotel, String tPrice) throws IOException {
        ExtentTest test = reports.createTest("Search Hotel test");
        driver.get("http://www.kurs-selenium.pl/demo/");
        HomePage homePage = new HomePage(driver);

        test.info("On PHP Travelers Home Page", getScreenshot());
        homePage.setCityHotel(city)
                .setDateRange(checkInDate, checkOutDate)
                .openTravellersModal()
                .addAdult()
                .addChild()
                .addChild();
        String infoText = "Before performing search for city %s check in %s and check out %s";
        test.info(String.format(infoText, city, checkInDate, checkOutDate), getScreenshot());
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

        test.info("Checking hotel names and prices", getScreenshot());
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
