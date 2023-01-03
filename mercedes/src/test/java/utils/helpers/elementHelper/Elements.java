package utils.helpers.elementHelper;

import org.openqa.selenium.By;

public class Elements {

    public static By getElementInfoToBy(ElementResponse element) {
        By by = null;
        if (element.getType().equals("className")) {
            by = By.className(element.getValue());
        } else if (element.getType().equals("id")) {
            by = By.id(element.getValue());
        } else if (element.getType().equals("xpath")) {
            by = By.xpath(element.getValue());
        } else if (element.getType().equals("name")) {
            by = By.name(element.getValue());
        }
        return by;
    }
}