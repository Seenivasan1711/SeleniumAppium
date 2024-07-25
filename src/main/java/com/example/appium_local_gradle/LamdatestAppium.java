package com.example.appium_local_gradle;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.example.appium_local_gradle.AppiumLocalGradleApplication.getCurrentTime;

public class LamdatestAppium {

    static String username = "engineeringtestsigma";
    static String accessKey = "XK6ARxr0FsuYM7Uur0FdNVMMRRTrG7VGMWiEwLE5ASBSN8gqOO";

    static AppiumDriver driver = null;
    private static WebDriverWait wait;
    public static void execute() {

        try {
            // LambdaTest Appium endpoint
            String appiumUrl = "https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";

            driver = new AndroidDriver(new URL(appiumUrl), getDesiredCapabilities());
            wait = new WebDriverWait(driver, 30);

            System.out.println(getCurrentTime() + "Appium session started" );

            executionForTE19487();

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

    private static DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 6");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("app", "lt://APP10160361821720077340941779");
        capabilities.setCapability("build", "Local Appium dev check TE-19487");
        capabilities.setCapability("name", "MFS Loading issue");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("username", username);
        capabilities.setCapability("accessKey", accessKey);
        capabilities.setCapability("appiumVersion", "2.2.1");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("network", true);
        capabilities.setCapability("network.full.har", true);
        capabilities.setCapability("network.har", true);
        capabilities.setCapability("newCommandTimeout", 500000);
        capabilities.setCapability("region", "us");
        capabilities.setCapability("w3c", true);
        capabilities.setCapability("dedicatedProxy", false);
        capabilities.setCapability("devicelog", true);

        DesiredCapabilities capabilities1 = new DesiredCapabilities();

        capabilities1.setCapability("LT:Options", capabilities);

        return capabilities1;
    }

    private static void executionForTE19487() {
        String progressBar = "//*[@class=\"android.widget.ProgressBar\"]";
        waitUntilElementDisappear(progressBar);

        String loginButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView";
        takeScreenshot();
        LamdatestAppium.clickOnElement(loginButtonXpath);

        // making few drivers call to reproduce the issue
        takeScreenshot();
        dummyElement();

        // waiting for email input to appear
        String emailInputClass = "android.widget.EditText";
        takeScreenshot();
        waitUntilElementIsPresent(emailInputClass);
    }

    private static void clickOnElement(String xpath) {
        try {
            System.out.println(getCurrentTime() + "Trying to click on " + xpath);
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static void waitUntilElementIsPresent(String className) {
        try {
            System.out.println(getCurrentTime() + "Trying to wait for " + className);
            // Wait until the element is present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            throw new RuntimeException(e);
        }
    }

    private static void waitUntilElementDisappear(String xpath) {
        try {
            System.out.println(getCurrentTime() + "Trying to wait for " + xpath);
            // Wait until the element is disappeared
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            throw new RuntimeException(e);
        }
    }

    private static void dummyElement() {
        try {
            System.out.println(getCurrentTime() + "Dummy element");
            driver.findElement(By.id("testSigma-random-12345"));
        } catch (Exception e) {
            // System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static void takeScreenshot() {

        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot.png");

        try {
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

}
