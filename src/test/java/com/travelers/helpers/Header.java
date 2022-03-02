package com.travelers.helpers;

import com.travelers.tests.BaseSeleniumTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Header extends BaseSeleniumTest {

    @FindBy(xpath = "//button/span")
    private WebElement logInBtn;

    @FindBy(id = "Username")
    private WebElement usernameInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = ".btn")
    private WebElement logInIdentityBtn;

    @FindBy(className = "app-name")
    private WebElement appName;

    @FindBy(css = "#listItemBatchUpload .mat-line")
    private WebElement batchUploadList;

    @FindBy(id = "mat-input-1")
    private WebElement filterServiceMonth;

    @FindBy(id = "mat-input-2")
    private WebElement filterServiceYear;

    @FindBy(css = ".ng-tns-c92-6 > .mat-sort-header-content")
    private WebElement orderFileID;

    @FindBy(id = "mat-select-value-1")
    private WebElement status;

    @FindBy(css = "#mat-option-10 > .mat-option-text")
    private WebElement statusSynchronized;

    @FindBy(className = "mat-select-value-5")
    private WebElement statusSynchronizedDescription;

    private SeleniumHelper helper;
    private WebDriver driver;
    private Logger log = Logger.getLogger(Header.class);

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public Header clickLogIn() {
        helper.waitForElementToBeDisplayed(logInBtn);
        logInBtn.click();
        return this;
    }

    public Header enterCredentialsAndLogIn() {
        helper.waitForElementToBeDisplayed(usernameInput);
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("Test$123");
        logInIdentityBtn.click();
        return this;
    }

    public Header goToBatchUploadList() {
        helper.waitForElementToBeDisplayed(batchUploadList);
        batchUploadList.click();
        return this;
    }

    public Header editBatchUploadList() {
        helper.waitForElementToBeDisplayed(appName);
        if(appName.getText().equals("Eclaims Submission")) {
            helper.waitForElementToBeDisplayed(orderFileID);
            orderFileID.click();
            filterServiceMonth.click();
            filterServiceMonth.sendKeys("1");
            filterServiceMonth.clear();
            filterServiceYear.click();
            filterServiceYear.sendKeys("2021");
        }
            return this;
    }

    public Header statusFilter() throws InterruptedException {

        Actions actions = new Actions(driver);

        helper.waitForElementToBeDisplayed(status);
        status.click();
        actions.moveToElement(statusSynchronized);
        actions.perform();
        statusSynchronized.click();

        return this;
    }
}
