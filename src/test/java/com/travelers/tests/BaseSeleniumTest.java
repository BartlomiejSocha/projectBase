package com.travelers.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.travelers.exceptions.NoSuchDriverException;
import com.travelers.helpers.SeleniumHelper;
import com.travelers.utils.DriverFactory;
import com.travelers.utils.DriverType;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

import static com.travelers.utils.PropertyLoader.loadProperties;

public class BaseSeleniumTest {

    protected WebDriver driver;
    protected ExtentReports reports;
    protected ExtentSparkReporter sparkReporter;
    protected ExtentTest test;

    @BeforeTest
    public void setUpReporter() {
        reports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("src//test//resources//reports//index.html");
        sparkReporter.config().setTheme(Theme.DARK);
        reports.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void setUp() throws NoSuchDriverException, ConfigurationException {
        Configuration config = loadProperties();
        System.out.println("Before method");
        driver = DriverFactory.getDriver(DriverType.valueOf(config.getString("browser")));
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("After method");
        driver.quit();
        DriverFactory.driverInstance.remove();
    }

    @AfterTest
    public void tearDownReporter() {
        System.out.println("tear down reporter");
        reports.flush();
    }

    protected Media getScreenshot() throws IOException {
        return MediaEntityBuilder.createScreenCaptureFromPath(SeleniumHelper.takeScreenshot(driver)).build();
    }
}
