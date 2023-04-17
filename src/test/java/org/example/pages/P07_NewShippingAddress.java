package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.stepDefinitions.Hooks.driver;

public class P07_NewShippingAddress {
    public P07_NewShippingAddress(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(name="address-ui-widgets-countryCode")
    public WebElement countyDropDownList;
    @FindBy(name="address-ui-widgets-enterAddressISDPhoneNumber")
    public WebElement MobileDropDownList;
    @FindBy(id="address-ui-widgets-enterAddressFullName")
    public WebElement fullName;
    @FindBy(id="address-ui-widgets-enterAddressPhoneNumber")
    public WebElement mobileNo;
    @FindBy(id="address-ui-widgets-enterAddressLine1")
    public WebElement streetName;
    @FindBy(id="address-ui-widgets-enter-building-name-or-number")
    public WebElement buildingNo;
    @FindBy(id="address-ui-widgets-landmark")
    public WebElement landMark;
    @FindBy(id="address-ui-widgets-enterAddressCity")
    public WebElement cityDropDownList;
    @FindBy(id="address-ui-widgets-enterAddressDistrictOrCounty")
    public WebElement districtDropDown;
   @FindBy(id="address-ui-widgets-addr-details-res-radio-input")
    public WebElement radioBTN;
   @FindBy(id="address-ui-widgets-form-submit-button-announce")
    public WebElement addAddressBTN;
   @FindBy(id="payChangeButtonId")
    public WebElement paymentMethodChangeBTN;
   @FindBy(id="pp-Vj5UE7-61")
    public WebElement addCard;
   @FindBy(id = "pp-x8sZGW-26-announce")
    public WebElement cancelBTN;
}
