package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.stepDefinitions.Hooks.driver;

public class P03_VideoGames {
    public P03_VideoGames(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//span[text()='Free Shipping']/preceding-sibling::div[@class='a-checkbox a-checkbox-fancy aok-float-left apb-browse-refinements-checkbox']/label/input[@type='checkbox']")
    public WebElement freeShippingCheckBox;
    @FindBy(xpath = "//div[@class='a-section a-spacing-none']//span[@class='a-size-base a-color-base' and text()='New']")
    public WebElement newConditionFilter;

}
