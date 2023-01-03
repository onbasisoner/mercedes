package utils.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.helpers.PropertyManager;
import java.io.File;
import java.io.IOException;

public class ChromeWebDriver {

    PropertyManager propertyManager = new PropertyManager();
    WebDriver driver;
    String domain = propertyManager.getProperty("env.properties", "domain");


    public WebDriver initializeAndGetChromeDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", (new File(System.getProperty("user.dir") + "/src/test/resources/chromedriver").getCanonicalPath()));
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, (new File(System.getProperty("user.dir") + "/src/test/resources/chromedriver").getCanonicalPath()));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--test-type");
            options.addArguments("--disable-extensions");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-translate");
            options.addArguments("--always-authorize-plugins");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--incognito");
            options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation", "disable-client-side-phishing-detection"});

            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

            driver.get(domain);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return driver;
    }

}