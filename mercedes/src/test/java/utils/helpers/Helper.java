package utils.helpers;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.drivers.WebDrivers;
import utils.helpers.elementHelper.ElementMap;
import utils.helpers.elementHelper.ElementResponse;
import utils.helpers.elementHelper.Elements;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Helper {
    public WebDriver webDriver = null;


    WebDrivers webDrivers = new WebDrivers();
    int counter = 0;

    PropertyManager propertyManager = new PropertyManager();


    public Helper() {
        if (webDriver == null) {
            webDriver = webDrivers.createAndGetDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }
    }

    // This method waits until element is visible
    public WebElement waitUntilElementIsVisible(WebElement element, long startTime) {
        webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebDriverWait subWait = new WebDriverWait(webDriver, 1);
        if ((System.currentTimeMillis() - startTime) > 10000) {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            subWait.until(ExpectedConditions.visibilityOf(element));
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element;
        } catch (StaleElementReferenceException | TimeoutException e) {
            return waitUntilElementIsVisible(element, startTime);
        }
    }



    // This method waits until element is clickable
    public WebElement waitUntilElementIsClickable(WebElement element, long startTime) {
        WebDriver subDriver = webDriver;
        subDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebDriverWait subWait = new WebDriverWait(subDriver, 1);
        if ((System.currentTimeMillis() - startTime) > 15000) {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            subWait.until(ExpectedConditions.elementToBeClickable(element));
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element;
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
            System.out.println(e.getMessage());
            return waitUntilElementIsClickable(element, startTime);
        }
    }

    // This method centers element with JavaScript
    public WebElement centerElement(WebElement element) {

        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) webDriver).executeScript(scrollScript, element);

        waitForGivenTime(1);

        return element;
    }

    // This method waits for given time
    public void waitForGivenTime(double duration) {
        try {
            Thread.sleep((long) (duration * 1000L));
        } catch (InterruptedException exception) {
            Assert.fail(exception.getLocalizedMessage());
        }
    }

    // This method returns element with given text
    public WebElement getElementWithText(String text) {
        try {

            return webDriver.findElement(By.xpath("//*[text()='" + text + "']"));

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    // This method returns button with given text
    public WebElement getButtonWithText(String text) {
        try {

            return webDriver.findElement(By.xpath("//button[text()='" + text + "']"));

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    // This method returns element contains given text
    public WebElement getElementWithContainedText(String text) {
        try {

            return webDriver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).get(0);

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    // This method returns element with given placeholder
    public WebElement getElementWithPlaceholder(String text) {
        try {
            return webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']"));

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    // This method returns element with given label
    public WebElement getElementWithLabel(String text) {
        try {
            return webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']"));
        } catch (ElementNotFoundException e) {
            return webDriver.findElement(By.xpath("//*[@name='" + text + "']"));
        }
    }

    // This method returns nTh element contains given text
    public WebElement getElementWithTextContains(String text, int index) {
        try {
            return webDriver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).get(index - 1);

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    //This method get element with given key
    public By getBy(String key) {
        //ElementResponse elementInfo = reportHelper.getElementInfo(key); //will open when switching to new locator structure
        ElementResponse elementInfo = ElementMap.INSTANCE.findElementInfoByKey(key);
        return Elements.getElementInfoToBy(elementInfo);
    }

    public By getBy(String key, String text) {
        //ElementResponse elementInfo = reportHelper.getElementInfo(key); //will open when switching to new locator structure
        ElementMap.INSTANCE.initMap(ElementMap.INSTANCE.getFileList());
        ElementResponse elementInfo = ElementMap.INSTANCE.findElementInfoByKey(key);
        elementInfo.setValue(String.format(elementInfo.getValue(), text));
        return Elements.getElementInfoToBy(elementInfo);
    }


    public static File getSS() {
        TakesScreenshot scrShot = ((TakesScreenshot) WebDrivers.getDriver());
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        File img = new File(System.getProperty("user.dir") + "/target/ss/web_" + timeStamp + ".jpeg");
        try {
            FileUtils.copyFile(srcFile, img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }
}