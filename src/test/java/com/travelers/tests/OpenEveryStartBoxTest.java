package com.travelers.tests;

import com.aventstack.extentreports.ExtentTest;
import com.travelers.helpers.TestListener;
import com.travelers.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(TestListener.class)
public class OpenEveryStartBoxTest extends BaseSeleniumTest {

    @Test
    public void openEveryBox() throws IOException {
        ExtentTest test = reports.createTest("Click Every Box test");
        driver.get("http://www.kurs-selenium.pl/demo/");
        HomePage homePage = new HomePage(driver);

        test.info("On PHP Travelers Home Page", getScreenshot());

        homePage.openfirstBoxReadMore();

        test.info("Opened Blog Virgin Gorda", getScreenshot());
        String blogURL = driver.getCurrentUrl();
        Assert.assertEquals(blogURL,"http://www.kurs-selenium.pl/demo/blog/Virgin-Gorda-beaches-and-lobste");
        driver.navigate().back();
        test.info("Back to Travelers Home Page", getScreenshot());

        Assert.assertEquals(driver.getCurrentUrl(),"http://www.kurs-selenium.plzxc/demo/");
        test.pass("Box tests passed", getScreenshot());

    }
}
