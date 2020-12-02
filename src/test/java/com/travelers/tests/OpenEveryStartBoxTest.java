package com.travelers.tests;

import com.aventstack.extentreports.Status;
import com.travelers.helpers.TestListener;
import com.travelers.pages.HomePage;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.travelers.utils.PropertyLoader.loadProperties;

@Listeners(TestListener.class)
public class OpenEveryStartBoxTest extends BaseSeleniumTest {

    @Test
    public void openEveryBox() throws ConfigurationException, IOException {

        Configuration config = loadProperties();
        test = reports.createTest("Open Every Box test");

        driver.get(config.getString("baseURL"));
        HomePage homePage = new HomePage(driver);

        test.log(Status.INFO, "On PHP Travelers Home Page", getScreenshot());
        homePage.openfirstBoxReadMore();

        test.log(Status.INFO, "Opened Blog Virgin Gorda", getScreenshot());
        String blogURL = driver.getCurrentUrl();
        Assert.assertEquals(blogURL,"http://www.kurs-selenium.pl/demo/blog/Virgin-Gorda-beaches-and-lobste");
        driver.navigate().back();
        test.log(Status.INFO, "Back to Travelers Home Page", getScreenshot());

        Assert.assertEquals(driver.getCurrentUrl(),"http://www.kurs-selenium.pl/demo/");
        test.log(Status.PASS, "Box tests passed", getScreenshot());

    }
}
