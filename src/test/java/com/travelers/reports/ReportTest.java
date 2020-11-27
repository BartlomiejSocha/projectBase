package com.travelers.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;

public class ReportTest {

    public static void main(String[] args) throws IOException {

        ExtentHtmlReporter reporter = new ExtentHtmlReporter("index.html");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);

        ExtentTest test = reports.createTest("Our first test");
        test.log(Status.INFO, "First step");
        test.log(Status.DEBUG, "First debug");
        test.log(Status.WARNING, "First warning");
        test.log(Status.FAIL, "First fail");
        test.pass("test passed");

        String path = "C:\\Users\\Tarble\\seleniumTestPierwszy\\src\\main\\resources\\338541400.png";
        test.pass("Description", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        reports.flush();
        reporter.flush();

    }
}
