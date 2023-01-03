package utils.helpers;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Action extends Helper {

    Actions actions;
    WebDriverWait wait = new WebDriverWait(webDriver, 30);

    public Action() {
        actions = new Actions(webDriver);
    }

    // This method clicks on element with given element
    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        centerElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // This method forcefully click on an element
    public void forceClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    // This method double click on an element
    public void doubleClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Actions action = new Actions(webDriver);
        action.moveToElement(element).doubleClick().perform();
    }

    // This method submits on element with given element
    public void submitElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).submit();
    }

    //This method finds element with given key
    public WebElement findElement(String key) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 20000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        return null;
    }


    //This method finds element with given key
    public WebElement findElement(String key,String text) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 20000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key,text)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    //This method finds WebElementLists that is created by key
    public List<WebElement> findElements(String key) {
        return webDriver.findElements(getBy(key));
    }

    // This method check on List element with given key and index
    public void checkElementOnList(String key, int index, String text) {
        try {
            String innerText = findElements(key).get(index - 1).getAttribute("text");
            Assert.assertEquals(innerText, text);

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method check existence of elements text on list
    public void checkElementExistenceOnList(WebElement key, String text) {
        try {
            String innerTexts = key.getAttribute("text");
            Assert.assertTrue(innerTexts.contains(text));

        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method clicks on List element with given key
    public void clickElementOnList(String key, int index) {
        try {
            centerElement(waitUntilElementIsClickable(findElements(key).get(index - 1), System.currentTimeMillis())).click();
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method returns List size
    public int getListSize(String key) {
        int size = 0;
        try {
            size = findElements(key).size();
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
        return size;
    }

    // This method clear and fill text field with given text
    public void clearAndFillInput(String key, String text) {
        try {
            centerElement(waitUntilElementIsVisible(findElement(key),System.currentTimeMillis())).clear();
            centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).sendKeys(text);
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method clear and fill value on the field with given text
    public void clearValueAndFillInput(String key, String text) {
        try {
            centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis()));
            setAttribute(findElement(key));
            findElement(key).sendKeys(text);
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    //This method clears an input field /w style
    public WebElement clearInputFieldWithKeyboard(WebElement element) {
        centerElement(waitUntilElementIsVisible(element, System.currentTimeMillis()));
        int textLength = element.getAttribute("value").length();
        for (int i = 0; i < textLength; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        return element;
    }

    // This method checks text field's inner text with given key
    //Clear attribute value of the text field
    public static void setAttribute(WebElement element) {
        element.clear();
        element.sendKeys("");
    }

    // This method checks text field's inner text equality via getAttribute with given key
    public void checkFilledInputEquals(String key, String text) {
        try {
            Assert.assertTrue(centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).getAttribute("value").equalsIgnoreCase(text));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method checks text field's inner text contains via getAttribute with given key
    public void checkFilledInputContains(String key, String text) {
        String innerText = findElement(key).getAttribute("value");
        try {
            Assert.assertTrue(innerText.contains(text));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method checks text field's inner text equality via getText with given key
    public void checkFilledInputEqualsToText(String key, String text) {
        try {
            Assert.assertEquals(centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).getText(),(text));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method checks text field's inner text contains via getText with given key
    public void checkFilledInputContainsToText(String key, String text) {
        try {
            Assert.assertTrue(centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).getText().contains(text));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }



    // This method clears text field with given key
    public void clearFilledInput(String key) {
        try {
            findElement(key).clear();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method checks element with text is displayed on page
    public void checkElementWithText(String key) {
        try {
            Assert.assertTrue(centerElement(waitUntilElementIsVisible(getElementWithText(key), System.currentTimeMillis())).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public void checkElementWithoutText(String key) {
        try {
            Assert.assertFalse(centerElement(waitUntilElementIsVisible(getElementWithText(key),
                    System.currentTimeMillis())).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method checks button existence with text the on page
    public void checkButtonWithText(String key) {
        try {
            Assert.assertTrue(centerElement(waitUntilElementIsVisible(getButtonWithText(key), System.currentTimeMillis())).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method checks field with label is displayed on page
    public void checkWithTextOfPlaceholder(String key) {
        try {
            waitForVisibilityOfElement(findElement(key));
//            centerElement(waitUntilElementIsVisible(findElement(key),
//                    System.currentTimeMillis())).isDisplayed();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public void checkWithTextOfPlaceholderNotexistence(String key) {
        try {
            Assert.assertFalse(findElement(key).isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method clicks element with text
    public void clickElementWithText(String value) {
        try {
            centerElement(waitUntilElementIsClickable(getElementWithText(value), System.currentTimeMillis())).click();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // This method clicks nTh element contains given text
    public void clickNthElementContainsText(int index, String value) {
        try {
            centerElement(waitUntilElementIsVisible(getElementWithTextContains(value, index), System.currentTimeMillis())).click();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //This method makes the thread wait for a certain while
    public void waitFor(double duration) {
        waitForGivenTime(duration);
    }

    // This method is explicitly wait for element to be displayed
    public void waitForVisibilityOfElement(WebElement key) {
        try {
            wait.until(ExpectedConditions.visibilityOf(key));
        } catch (Exception e) {
            System.out.println("Given element " + key + " did not displayed in 30 seconds!!");
        }
    }

    // This method is explicitly wait for element to be clickable
    public void waitForElementToClick(WebElement key) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(key));
        } catch (Exception e) {
            System.out.println("Given element " + key + " did not clickable in 30 seconds!!");
        }
    }

    //This method verifies current url with given url
    public void verifyUrl(String url) {
        Assert.assertTrue(webDriver.getCurrentUrl().contains(url));
    }

    //This method verifies the page title
    public void verifyPageTitle(String pageTitle) {
        Assert.assertTrue(webDriver.getTitle().contains(pageTitle));
    }

    //This method hovers over an element
    public void hover(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    // This method refreshes page
    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    //This method goes back to previous page
    public void goBack() {
        webDriver.navigate().back();
    }


    // This method teardown the driver
    public void tearDown() {
        webDriver.quit();
    }

    // This method checks that given element display on screen
    public boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception var2) {
            return false;
        }
    }

    // This method checks that given element enable
    public boolean isElementEnabled(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
        } catch (Exception var3) {
            System.out.println(var3);
            return false;
        }
    }

    // This method checks that given element is select
    public boolean isElementSelected(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            return wait.until(ExpectedConditions.visibilityOf(element)).isSelected();
        } catch (Exception var4) {
            return false;
        }
    }

    // This method checks that is element clickable
    public boolean isElementClickable(WebElement element) {

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    //This method upload file for given path
    public void uploadFileWithGivenPath(WebElement key, String text) {
        File file = new File("src/test/java/utils/files/" + text);
        key.sendKeys(file.getAbsolutePath());
    }

    // This method switch windows tabs to the given index
    public void switchToTheTab(Integer index) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(index));
    }

    // This method switch to window by title
    public void switchToWindowByTitle(String windowTitle) throws Exception {
        String old_win = webDriver.getWindowHandle();
        boolean winExist = false;
        for (String winHandle : webDriver.getWindowHandles()) {
            String str = webDriver.switchTo().window(winHandle).getTitle();
            if (str.equals(windowTitle)) {
                winExist = true;
                break;
            }
        }
        if (!winExist)
            throw new Exception("Window having title " + windowTitle + " not exist");
    }

    // This method select given text on a dropdown
    public void dropDownSelect(WebElement key, String value) {
        centerElement(waitUntilElementIsVisible(key, System.currentTimeMillis())).click();
        clickElementWithText(value);
    }

    // This method controls the selected text on the dropdown
    public void checkDropDown(String value, WebElement key) {
        centerElement(waitUntilElementIsVisible(key, System.currentTimeMillis())).getAttribute("value");
        String actualValue = key.getText();
        Assert.assertTrue(actualValue.equalsIgnoreCase(value));
    }

    // This method check given text on a dropdown
    public void checkDropDownElement(WebElement key, String value) {
        centerElement(waitUntilElementIsVisible(key, System.currentTimeMillis())).click();
        checkElementWithText(value);
    }

    // This method check check-box
    public void checkCheckbox(String key) {
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOf(findElement(key)));
        if (!checkbox.isSelected())
            checkbox.click();
    }

    // This method uncheck check-box
    public void uncheckCheckbox(String key) {
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOf(findElement(key)));
        if (!isElementSelected(checkbox))
            checkbox.click();
    }

    // This method check radio button
    public void selectRadioButton(String key) {
        WebElement radioButton = wait.until(ExpectedConditions.visibilityOf(findElement(key)));
        if (!radioButton.isSelected())
            radioButton.click();
    }

    // This method uncheck radio button
    public void unselectRadioButton(String key) {
        WebElement radioButton = wait.until(ExpectedConditions.visibilityOf(findElement(key)));
        if (radioButton.isSelected())
            radioButton.click();
    }





    // This method get element attribute with given value and check with given value
    public void getAttribute(WebElement key, String value) {
        String attributeValue = wait.until(ExpectedConditions.visibilityOf(key)).getAttribute(value);
        System.out.println("Value of " + value + "attribute: " + attributeValue);
    }

    // This method get element attribute with given value
    public void getAttributeandCheck(WebElement key, String getValue, String value) {
        String attributeValue = wait.until(ExpectedConditions.visibilityOf(key)).getAttribute(getValue);
        Assert.assertEquals(attributeValue, value);
    }

    // This method hover on an element and click with text
    public void hoverClick(WebElement key, String value) {
        hover(key);
        clickElementWithText(value);
    }

    public void clickEscapeKey() {
        actions.sendKeys(Keys.ESCAPE).build().perform();
    }

    public void firmFoundList(String key, String text) {
        List<WebElement> find = findElements(key);
        for (int i = 0; i < find.size(); i++) {
            if ((find.get(i).findElements(By.cssSelector("td")).get(0).getText().equals(text))) {
                find.get(i).findElements(By.cssSelector("td")).get(4).findElement(By.cssSelector("[data-testid='button']")).click();
                break;
            }
        }
    }

    public void firmDeleteOnList(String key, String text) {
        List<WebElement> find = findElements(key);
        for (int i = 0; i < find.size(); i++) {
            if ((find.get(i).findElements(By.cssSelector("td")).get(0).getText().equals(text))) {
                find.get(i).findElements(By.cssSelector("td")).get(4).findElement(By.name("delete-1")).click();
                break;
            }
        }
    }

    public void brandFoundList(String key, String text) {
        List<WebElement> find = findElements(key);
        for (int i = 0; i < find.size(); i++) {
            if ((find.get(i).findElements(By.cssSelector("td")).get(0).getText().equals(text))) {
                find.get(i).findElements(By.cssSelector("td")).get(2).findElement(By.xpath("//button[@class='style__Button-sc-6ivys6-0 gJPAcU' and text()='Düzenle']")).click();
                break;
            }
        }
    }

    public void brandDeleteOnList(String key, String text) {
        List<WebElement> find = findElements(key);
        for (int i = 0; i < find.size(); i++) {
            if ((find.get(i).findElements(By.cssSelector("td")).get(0).getText().equals(text))) {
                find.get(i).findElements(By.cssSelector("td")).get(2).findElement(By.name("delete-1")).click();
                break;
            }
        }
    }

    public void clickWithElementWithText2(String key, String text) {
        clickElement(centerElement(findElement(key,text)));
    }

    public void clickEnterButton(){
        actions.sendKeys(Keys.ENTER).build().perform();
    }
}