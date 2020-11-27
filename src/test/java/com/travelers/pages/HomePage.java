package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchCityInput;

    @FindBy(name = "checkin")
    private WebElement checkInInput;

    @FindBy(name = "checkout")
    private WebElement checkOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement selectResult;

    private SeleniumHelper helper;

    private WebDriver driver;

    private Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public HomePage setCityHotel(String cityName) {
        log.info("Setting city name ");
        searchSpan.click();
        searchCityInput.sendKeys(cityName);
        helper.waitForElementToBeDisplayed(selectResult);
        searchCityInput.sendKeys(Keys.ENTER);
        log.info("City name set");
        return this;
    }

    public HomePage setDateRange(String checkInDate, String checkOutDate) {
        log.info("Setting date range ");
        checkInInput.sendKeys(checkInDate);
        checkOutInput.sendKeys(checkOutDate);
        checkOutInput.click();
        log.info("Date range is set");
        return this;
    }

    public HomePage openTravellersModal() {
        log.info("Opening travellers modal");
        travellersInput.click();
        helper.waitForElementToBeDisplayed(adultPlusBtn);
        log.info("Travellers modal is opened");
        return this;
    }

    public HomePage addAdult() {
        log.info("Adding adult");
        adultPlusBtn.click();
        log.info("Adult added");
        return this;
    }

    public HomePage addChild() {
        log.info("Adding child");
        childPlusBtn.click();
        log.info("Child added");
        return this;
    }

    public ResultPage performSearch() {
        searchButton.click();
        log.info("Search performed");
        return new ResultPage(driver);
    }

}
