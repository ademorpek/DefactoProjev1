package com.thoughtworks.gauge.maven;

import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    
    protected static WebDriver driver;
    protected static Actions actions;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;


    String browserName = "chrome";
    String selectPlatform = "mac";

    @BeforeScenario
    public void setUp() {
        String BaseUrl = "https://www.defacto.com.tr/";
        DesiredCapabilities capabilities;
        capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY,options);
        System.setProperty("webdriver.chrome.driver","web_driver/chromedriver");
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.get(BaseUrl);
    }

    private ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        //add extension
        chromeOptions.addExtensions(new File("web_driver/3.1.27_0.crx"));
        //chromeOptions.addArguments("--kiosk");
        //chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.addArguments("--allowed-ips");
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver");
        chromeOptions.merge(capabilities);


        return chromeOptions;

    }

}