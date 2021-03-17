package com.selenium.course.base;

import com.selenium.course.driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestUtil {
    public WebDriver driver;
    private String url;
    private int implicitlyWait;
    private String browser;

    @BeforeSuite
    public void readConfigProperties() {
        try {
            FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitlyWait = Integer.parseInt(config.getProperty("implicitlyWait"));
            // Homework - to add chrome browser
            browser = config.getProperty("browser");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setUp() throws InterruptedException {
        setupBrowserDriver();
        loadUrl();
    }

    private void loadUrl() {
        driver.get(url);
    }

    private void setupBrowserDriver() throws InterruptedException {

        if(browser.equals("chrome")) {
            driver = DriverFactory.getChromeDriver(implicitlyWait);
        } else if(browser.equals("firefox")) {
            driver = DriverFactory.getFirefoxDriver(implicitlyWait);
        } else {
            throw new InterruptedException("The browser doesn't exist!");
        }
        // browsers implementation

    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
    }
}
