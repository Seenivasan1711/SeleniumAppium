package com.example.appium_local_gradle;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.appium_local_gradle.AppiumLocalGradleApplication.getCurrentTime;

public class LambdaTestMobileWeb {

    static String username = "engineeringtestsigma";
    static String accessKey = "XK6ARxr0FsuYM7Uur0FdNVMMRRTrG7VGMWiEwLE5ASBSN8gqOO";

    static AppiumDriver driver = null;
    private static WebDriverWait wait;

    public static void execute() {

        try {
            // LambdaTest web endpoint
            String appiumUrl = "https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";
//            String appiumUrl = "https://" + username + ":" + accessKey + "@mobile-hub-oregon.lambdatest.com:443/wd/hub";

            driver = new IOSDriver(new URL(appiumUrl), getDesiredCapabilities());
            wait = new WebDriverWait(driver, 30);

            System.out.println(getCurrentTime() + "Appium session started");

            executionForTE21127();

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

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14 Pro Max");
        capabilities.setCapability("platformVersion", "16.0");
        capabilities.setCapability("build", "Local Mobile Web dev check TE-21127");
        capabilities.setCapability("name", "OAT IOS 17 check");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("username", username);
        capabilities.setCapability("accessKey", accessKey);
        capabilities.setCapability("network", true);
        capabilities.setCapability("network.full.har", true);
        capabilities.setCapability("network.har", true);
        capabilities.setCapability("newCommandTimeout", 500000);
        capabilities.setCapability("w3c", true);
        capabilities.setCapability("dedicatedProxy", false);
        capabilities.setCapability("devicelog", true);

        DesiredCapabilities capabilities1 = new DesiredCapabilities();

        capabilities1.setCapability("LT:Options", capabilities);
        capabilities1.setCapability("browserName", "Safari");
        capabilities1.setCapability("platformName", "IOS");

        return capabilities1;
    }

    private static void executionForTE21127() throws InterruptedException {

        driver.get("https://www.oattravel.com/jpn?at_preview_token=44lVO98ijQ8SYypB7O3tyqNp90jz31bx3PqJ7l2Yndo&at_preview_index=1_2&at_preview_listed_activities_only=true&at_preview_evaluate_as_true_audience_ids=7282356");

        String close = " //div[@id=\"newsletterWebPopup\" and contains(@class,'active')]//div[@class=\"web-popup-form form-wrap row\"]//a[@title=\"Close\"]";
        LambdaTestMobileWeb.waitUntilElementIsPresent(close);

        if (LambdaTestMobileWeb.verifyIfElementPresent(close)) {
            LambdaTestMobileWeb.clickOnElement(close);
        }

        String acceptButton = "//*[@id=\"btnAcceptConsent\"]";
        LambdaTestMobileWeb.waitUntilElementIsPresent(acceptButton);
        LambdaTestMobileWeb.clickOnElement(acceptButton);

        String video = "(//div[@class=\"vjs-poster\"])[2]";
        LambdaTestMobileWeb.waitUntilElementIsPresent(video);
        LambdaTestMobileWeb.scrollToElement(video);
        LambdaTestMobileWeb.clickOnElement(video);

        // waiting for video to play in full screen
        Thread.sleep(10000);

        // double tap on screen center
        LambdaTestMobileWeb.doubleTapOnScreenCenter();

    }

    private static void clickOnElement(int x, int y) {
        try {
            System.out.println(getCurrentTime() + "Trying to click at coordinates (" + x + ", " + y + ")");

            // Perform the click at the specified coordinates
            Actions actions = new Actions(driver);
            actions.moveByOffset(x, y).click().perform();

            System.out.println(getCurrentTime() + "Clicked on element at coordinates (" + x + ", " + y + ")");
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static void doubleTapOnScreenCenter() {
        try {
            Dimension screenDimension = driver.manage().window().getSize();
            Map<String, Object> params = new HashMap<>();
            params.put("x", screenDimension.getWidth() / 2);
            params.put("y", screenDimension.getHeight() / 2);
            System.out.println(getCurrentTime() + "Performing double Tap with values " + screenDimension.toString());
            System.out.println(getCurrentTime() + "Performing double Tap with params " + params.toString());
            driver.executeScript("mobile: doubleTap", params);
        } catch (Exception error) {
            System.out.println(getCurrentTime() + "Error ::" + error);
        }
    }

    private static void clickOnElement(String xpath) {
        try {
            System.out.println(getCurrentTime() + "Trying to click on " + xpath);
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static Boolean verifyIfElementPresent(String xpath) {
        try {
            System.out.println(getCurrentTime() + "Verifying if " + xpath + " is present.");
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            return false;
        }
    }

    private static void scrollToElement(String xpath) {
        try {
            System.out.println(getCurrentTime() + "Trying to scroll to " + xpath);
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(30,0);", element);
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static void executeJavaScript(String script) {
        try {
            System.out.println(getCurrentTime() + "Executing JavaScript: " + script);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript(script);
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error executing JavaScript:: " + e);
        }
    }

    private static void waitUntilElementIsPresent(String className) {
        try {
            System.out.println(getCurrentTime() + "Trying to wait for " + className);
            // Wait until the element is present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(className)));
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

}
