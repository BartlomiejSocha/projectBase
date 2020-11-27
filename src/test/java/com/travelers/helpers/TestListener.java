package com.travelers.helpers;


import com.travelers.exceptions.NoSuchDriverException;
import com.travelers.utils.DriverFactory;
import com.travelers.utils.DriverType;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    Logger log = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.debug("On test start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.debug("On test success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            log.debug("On test failure");
            SeleniumHelper.takeScreenshot(DriverFactory.getDriver(DriverType.CHROME));
        } catch (IOException | NoSuchDriverException e) {
            log.error(e.getStackTrace());    }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.debug("On test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.debug("On test failure but within percentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.debug("On test failure without timeout");
    }

    @Override
    public void onStart(ITestContext context) {
        log.debug("On start");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.debug("On finish");
    }
}
