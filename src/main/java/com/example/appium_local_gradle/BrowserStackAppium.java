package com.example.appium_local_gradle;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.appium_local_gradle.AppiumLocalGradleApplication.getCurrentTime;

public class BrowserStackAppium {

    static String username = "rukmangada1";
    static String accessKey = "PzpzSFEGNQUaWXpzNok5";

    static RemoteWebDriver driver = null;
    private static WebDriverWait wait;

    public static void execute() {

        try {
            // BrowserStack Appium endpoint
            String appiumUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

            driver = new RemoteWebDriver(new URL(appiumUrl), getDesiredCapabilities());
//            wait = new WebDriverWait(driver, 30);

            System.out.println(getCurrentTime() + "Appium session started" );

            executionForTE22277();

        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            if (driver != null) {
                driver.quit();
            }
            System.out.println(getCurrentTime() + "Automation stopped.");
            System.out.println(getCurrentTime() + "Appium session ended");
        } finally {
            if (driver != null) {
                driver.quit();
            }
            System.out.println(getCurrentTime() + "Automation stopped from finally.");
            System.out.println(getCurrentTime() + "Appium session ended");
        }
    }

    private static void executionForTE22277() {

    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // App details
        capabilities.setCapability("app", "bs://bab4f1c8755a4a64c9a32d7fd75f9fb8f4667076");

        // Device and platform details
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15");
        capabilities.setCapability("osVersion", "17.0");
        capabilities.setCapability("realMobile", true);

        // BrowserStack-specific options
        DesiredCapabilities bstackOptions = new DesiredCapabilities();
        bstackOptions.setCapability("username", "rukmangada1");
        bstackOptions.setCapability("accessKey", "PzpzSFEGNQUaWXpzNok5");
        bstackOptions.setCapability("sessionName", "Local Appium dev check TE-22277");
        bstackOptions.setCapability("buildName", "Local Appium dev check");
        bstackOptions.setCapability("appiumVersion", "2.0.1");
        capabilities.setCapability("bstack:options", bstackOptions);

        return capabilities;
    }

}
