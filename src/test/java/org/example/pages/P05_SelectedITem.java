package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.stepDefinitions.Hooks.driver;

public class P05_SelectedITem {
    public P05_SelectedITem(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id ="add-to-cart-button")
    public WebElement addToCartBTN;
}
