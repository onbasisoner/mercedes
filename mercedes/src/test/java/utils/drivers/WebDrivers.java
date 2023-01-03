package utils.drivers;

import org.openqa.selenium.WebDriver;
import utils.drivers.ChromeWebDriver;
import utils.helpers.PropertyManager;

public class WebDrivers {

    PropertyManager propertyManager = new PropertyManager();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


       ChromeWebDriver chromeWebDriver = new ChromeWebDriver();

    public WebDriver createAndGetDriver() {

        if (driver.get() != null) {
            driver.get().quit();
        }

        String browserName = propertyManager.getProperty("env.properties", "browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
            driver.set(chromeWebDriver.initializeAndGetChromeDriver());
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}