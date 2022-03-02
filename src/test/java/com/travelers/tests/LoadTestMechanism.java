package com.travelers.tests;

import com.aventstack.extentreports.ExtentTest;
import com.travelers.helpers.Header;
import com.travelers.helpers.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListener.class)
public class LoadTestMechanism extends BaseSeleniumTest {

    @Test(invocationCount = 2)
    public void EditLists() {

        Header header = new Header(driver);

        ExtentTest test = reports.createTest("Enter Batch Upload and edit List");

        header.clickLogIn();
        test.pass("Logged in");
        header.enterCredentialsAndLogIn();
        test.pass("Enter Credentials and Log In");
        header.goToBatchUploadList();
        test.getModel();
        header.editBatchUploadList();
        test.pass("Enter Batch Upload and Edit List");
    }

    @Test(invocationCount = 2)
    public void StatusFilters() throws InterruptedException {

        Header header = new Header(driver);

        ExtentTest test = reports.createTest("Enter Batch Upload and edit Status Filters");

        header.clickLogIn();
        test.pass("Logged in");
        header.enterCredentialsAndLogIn();
        test.pass("Enter Credentials and Log In");
        header.goToBatchUploadList();
        test.pass("Go to Batch Upload List");
        header.statusFilter();
        test.pass("Filter List of batch files");
    }
}
