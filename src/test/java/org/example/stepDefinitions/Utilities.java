package org.example.stepDefinitions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static org.example.stepDefinitions.Hooks.driver;

public class Utilities {
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    public WebElement clickableFluentWait(WebElement Element, int timeoutInSeconds, int pollingIntervalInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingIntervalInSeconds))
                .ignoring(NoSuchElementException.class);


        return wait.until(ExpectedConditions.elementToBeClickable(Element));
    }
    public WebElement visabilityFluentWait(WebElement Element, int timeoutInSeconds, int pollingIntervalInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingIntervalInSeconds))
                .ignoring(NoSuchElementException.class);


        return wait.until(ExpectedConditions.visibilityOf(Element));
    }

    public void selectCheckbox(WebElement element) {
        WebElement checkbox = element;
        Actions actions = new Actions(driver);
        if (!checkbox.isSelected()) {
            actions.moveToElement(checkbox).click().perform();
        }
    }
    public void sortByDropdownList(WebElement element,String value) throws InterruptedException {
        WebElement dropdownList= element;
        Select select=new Select(dropdownList);
        select.selectByValue(value);
    }
    public void dynamicDropdownList(WebElement element, String text, By locator,int selectedItem) throws InterruptedException {
        element.sendKeys(text);
        Thread.sleep(5000);
        List<WebElement> list = driver.findElements(locator);
        list.get(selectedItem).click();
    }
}
