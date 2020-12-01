package com.travelers.utils;

import com.travelers.exceptions.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class DriverFactory {

    public static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    public static WebDriver getDriver(DriverType driverType) throws NoSuchDriverException {

        if(driverInstance.get() == null) {
            getSpecificDriver(driverType);
            driverInstance.get().manage().window().maximize();
        }
        return driverInstance.get();
    }

    public static void getSpecificDriver(DriverType driverType) throws NoSuchDriverException {

        switch (driverType){
            case IE:
                File ieExe = new File("src//test//resources//executables//drivers//IEDriverServer.exe");
                InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(ieExe)
                        .usingAnyFreePort().build();
                driverInstance.set(new InternetExplorerDriver(ieService));
                break;

            case CHROME:
                File chromeExe = new File("src//test//resources//executables//drivers//chromedriver.exe");
                ChromeDriverService chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeExe)
                        .usingAnyFreePort().build();
                driverInstance.set(new ChromeDriver(chromeService));
                break;

            case FIREFOX:
                File firefoxExe = new File("src//test//resources//executables//drivers//geckodriver.exe");
                GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(firefoxExe)
                        .usingAnyFreePort().build();
                driverInstance.set(new FirefoxDriver(geckoDriverService));
                break;

            default:
                System.out.println("No such driver");
                throw new NoSuchDriverException();
        }
    }
}
