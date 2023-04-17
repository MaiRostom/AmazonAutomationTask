package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.stepDefinitions.Hooks.driver;

public class P02_SignIN {
    public P02_SignIN(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "ap_email")
    public WebElement emailTxt;
    @FindBy(id = "continue")
    public WebElement continueBTN;

    @FindBy(id = "ap_password")
    public WebElement passwordTxt;
    @FindBy(id = "signInSubmit")
    public WebElement signInSubmit;
}
