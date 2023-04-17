package org.example.stepDefinitions;

import com.beust.ah.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.example.pages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.lang.model.element.Element;
import java.time.Duration;
import static org.example.stepDefinitions.Hooks.driver;
public class D01_Login {
    P01_Homepage homePage=new P01_Homepage();
    P02_SignIN signInPage=new P02_SignIN();
    @Given("user hover on Account and List")
    public void hoverOnAccountList() throws InterruptedException {
        Actions action = new Actions(Hooks.driver);
        WebElement accountList=homePage.accountAndList;
        action.moveToElement(accountList).perform();
        Thread.sleep(2000);    }
    @When("user click on SignIN")
    public void clickOnSignIN() throws InterruptedException {
        Thread.sleep(3000);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector('span[class=\"nav-action-inner\"]').click();");
        homePage.signIn.click();
    }
    @And("user Enter the Email {string}")
    public void userEnterTheEmail(String arg0) {
     signInPage.emailTxt.sendKeys(arg0);
    }

    @And("user click continue")
    public void continueClick(){
        signInPage.continueBTN.click();
    }
    @And("user write the password{string}")
    public void userWriteThePassword(String arg0) {
      signInPage.passwordTxt.sendKeys(arg0);
    }
    @Then("user click signIn")
    public void signIN(){
        signInPage.signInSubmit.click();
    }



}
