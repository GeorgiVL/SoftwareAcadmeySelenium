package com.selenium.course.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SimpleTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp(){
        // System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        // System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
       // driver = new ChromeDriver();
      //  driver = new FirefoxDriver();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void executeSimpleTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("#login-button"));
        loginBtn.click();

        Thread.sleep(2000);

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        Select list = new Select(selectElement);
        list.selectByValue("az");
        list.selectByVisibleText("Name (A to Z)");

        WebElement addToCart = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']//button"));
        addToCart.click();

        WebElement badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));

        Assert.assertEquals(badge.getText(), "1");


    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
    }
 }
