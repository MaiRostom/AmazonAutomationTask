package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.example.stepDefinitions.Hooks.driver;

public class P04_Results {
    public P04_Results() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "s-result-sort-select")
    public WebElement sortByList;

    public List<WebElement> priceList() {
        return driver.findElements(By.cssSelector("span[class=\"a-price-whole\"]"));
    }

    @FindBy(css = "a[class=\"s-pagination-item s-pagination-next s-pagination-button s-pagination-separator\"]")
    public WebElement nextPage;
}