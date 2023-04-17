package org.example.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver;
    @Before
    public static void openBrowser(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        //1-setup chrome driver
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.amazon.eg/-/en/ref=nav_logo");
        Cookie usernameCookie = new Cookie("email", "mai.rostom2@gmail.com");
        Cookie passwordCookie = new Cookie("password", "123456789mM");





    }
    @After
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("after Data");
        //  driver.quit();
    }
}
