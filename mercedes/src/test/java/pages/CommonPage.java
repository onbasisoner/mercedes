package pages;

import org.testng.Assert;
import utils.helpers.Action;

import java.util.List;

public class CommonPage extends Action {

    public void click(String key) {
        clickElement(findElement(key));
    }

    public void clearText(String key) {
        clearFilledInput(key);
    }

    public void fill(String key, String text) {
        clearAndFillInput(key, text);
    }

    public void clearValueAndFill(String key, String text) {
        clearValueAndFillInput(key, text);
    }

    public void checkEquals(String key, String text) {
        checkFilledInputEquals(key, text);
    }

    public void checkWithText(String key) {
        checkElementWithText(key);
    }

    public void checkWithPlaceholderText(String key) {
        checkWithTextOfPlaceholder(key);
    }

    public void refreshPage() {
        super.refreshPage();
    }

    public void waitForTheElementDisplay(String key) {
        waitForVisibilityOfElement(findElement(key));
    }

    public void waitForTheElementClickable(String key) {
        waitForElementToClick(findElement(key));
    }

    public void verifyUrl(String url) {
        super.verifyUrl(url);
    }

    public void verifyPageTitle(String pageTitle) {
        super.verifyPageTitle(pageTitle);
    }

    public void hoverOnElement(String key) {
        hover(findElement(key));
    }

    public void waitUntilElementVisible(String key) {
        waitUntilElementIsVisible(findElement(key), 5);
    }

    public void getAttributeValue(String key, String value) {
        getAttribute(findElement(key), value);
    }

    public void getAttributeValueAndVerify(String key, String getValue, String value) {
        getAttributeandCheck(findElement(key), getValue, value);
    }

    public void clickEscapeKey() {
        super.clickEscapeKey();
    }

    public void elementControl(List<String> e) {
        if (e.contains("isDisplayed")) {
            Assert.assertTrue(isElementDisplayed(findElement(e.get(0))), "Element " + e.get(0) + "is not displayed!");
        }
        if (e.contains("isEnabled")) {
            Assert.assertTrue(isElementEnabled(findElement(e.get(0))), "Element " + e.get(0) + " is not enabled.");
        }
        if (e.contains("isSelected")) {
            Assert.assertTrue(isElementSelected(findElement(e.get(0))), "Element " + e.get(0) + " is not selected.");
        }
        if (e.contains("isClickable")) {
            Assert.assertTrue(isElementClickable(findElement(e.get(0))), "Element " + e.get(0) + " is not clickable.");
        }

        if (e.contains("isNotEnabled")) {
            Assert.assertFalse(isElementDisplayed(findElement(e.get(0))), "Element " + e.get(0) + " is enabled.");
        }
        if (e.contains("isNotDisplayed")) {
            Assert.assertFalse(isElementEnabled(findElement(e.get(0))), "Element " + e.get(0) + " is displayed.");
        }
        if (e.contains("isNotSelected")) {
            Assert.assertFalse(isElementSelected(findElement(e.get(0))), "Element " + e.get(0) + " is selected.");
        }
        if (e.contains("isNotClickable")) {
            Assert.assertFalse(isElementClickable(findElement(e.get(0))), "Element " + e.get(0) + " is clickable.");
        }
    }

    public void checkInputEquality(String key, String text) {
        checkFilledInputEqualsToText(key, text);
    }

    public void clickEnter() {
        clickEnterButton();
    }
}