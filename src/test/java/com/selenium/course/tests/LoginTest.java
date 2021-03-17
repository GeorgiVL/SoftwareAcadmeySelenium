package com.selenium.course.tests;


import com.opencsv.exceptions.CsvException;
import com.selenium.course.base.TestUtil;
import com.selenium.course.utils.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends TestUtil {

    @DataProvider(name="login-data")
    public Object[] dataProviderHardcodedData() {
        return new Object[][] {
                {"user1","pass1"},
                {"user2","pass2"}
        };
    }

    @DataProvider(name="login-data-file")
    public static Object[][] dataProvidedFromCvsFile() throws IOException, CsvException {
         return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-data-file")
    public void executeSimpleTest(String userName, String password) throws InterruptedException {
        driver.get("https://www.saucedemo.com");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("#login-button"));
        loginBtn.click();
    }
}

