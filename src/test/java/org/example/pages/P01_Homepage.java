package org.example.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.stepDefinitions.Hooks.driver;

public class P01_Homepage {
    public P01_Homepage(){
        PageFactory.initElements(driver,this);
    }

   @FindBy(css="span[class=\"nav-line-2 \"]")
   public WebElement accountAndList;
    @FindBy(className="nav-action-inner")
    public WebElement signIn;
    @FindBy(id="nav-hamburger-menu")
    public WebElement navigateAll;
    @FindBy(css="a[class=\"hmenu-item hmenu-compressed-btn\"]")
    public WebElement seeAll;
    @FindBy(css="a[data-menu-id=\"16\"]")
    public WebElement videoGames;
    @FindBy(css="a[href=\"/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2\"]")
    public WebElement allVideoGames;
}
