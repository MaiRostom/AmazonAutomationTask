package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.stepDefinitions.Hooks.driver;

public class P06_AddToCart {
    public P06_AddToCart(){
        PageFactory.initElements(driver,this);
    }
@FindBy(name="proceedToRetailCheckout")
    public WebElement proceedToBuyBTN;
    @FindBy(xpath = "//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']\n")
    public WebElement addToCartLabel;

}
