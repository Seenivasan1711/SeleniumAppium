package com.example.appium_local_gradle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.example.appium_local_gradle.AppiumLocalGradleApplication.getCurrentTime;

public class LambdatestSelenium {

    private static WebDriver driver;

    static String username = "engineeringtestsigma";
    static String accessKey = "XK6ARxr0FsuYM7Uur0FdNVMMRRTrG7VGMWiEwLE5ASBSN8gqOO";
    private final static String lambdaTestUrl = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

    public static void execute() {
        try {
            driver = new RemoteWebDriver(new URL(lambdaTestUrl), getDesiredCapabilities());
            System.out.println(getCurrentTime() + "Selenium session started");

            navigate();

        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            if (driver != null) {
                driver.quit();
            }
            System.out.println(getCurrentTime() + "Automation stopped.");
            System.out.println(getCurrentTime() + "Selenium session ended");
        } finally {
            if (driver != null) {
                driver.quit();
            }
            System.out.println(getCurrentTime() + "Automation stopped from finally.");
            System.out.println(getCurrentTime() + "Selenium session ended");
        }
    }

    public static void navigate() throws InterruptedException {
        // Navigate to a URL
        driver.get("https://www.behr.com/consumer/colorstudio");
        System.out.println(getCurrentTime() + "Navigated to color studio page");

        // find and click element
        String elementXpath = "//a[text()='Start Discovering']";
        scrollToElement(driver.findElement(By.xpath(elementXpath)));

        driver.findElement(By.xpath(elementXpath)).click();
        System.out.println(getCurrentTime() + "Navigated to welcome page");

        //navigate back
        driver.navigate().back();
        Thread.sleep(2000);
        System.out.println(getCurrentTime() + "Clicked back button");

        boolean elementNotFound = true;
        int count = 0;

        //finding element
        while (elementNotFound) {
            count += 1;
            driver.navigate().back();

            Thread.sleep(2000);
            System.out.println(getCurrentTime() + "Clicked back button inside while loop for the time of " + count);
            try {
                WebElement element = driver.findElement(By.xpath(elementXpath));
                if (element.isDisplayed()) {
                    elementNotFound = false;
                    System.out.println(getCurrentTime() + "Navigated back successfully and found the element");
                }
                if (count >= 10) {
                    elementNotFound = false;
                }
            } catch (Exception e) {
                System.out.println(getCurrentTime() + "Error ::" + e);
            }
        }
    }

    private static DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("build", "LambdaTestSampleBuild");
        capabilities.setCapability("name", "LambdaTestSampleTest");
        capabilities.setCapability("build", "Local Selenium dev check TE-20632");
        capabilities.setCapability("name", "behr.com Navigate back issue");

        DesiredCapabilities capabilities1 = new DesiredCapabilities();

        capabilities1.setCapability("LT:Options", capabilities);

        return capabilities1;
    }

    protected static void scrollToElement(WebElement element) {
        String scrollToElement = "try{ "
                + "arguments[0].scrollIntoView({"
                + " behavior: 'auto', block: 'center', inline: 'center'"
                + "}); return false;"
                + "}catch(e){"
                + "return true;"
                + "}";
        Object result = ((JavascriptExecutor) driver).executeScript(scrollToElement, element);
        System.out.println(getCurrentTime() + "result:: " + result);

        if (result instanceof Boolean && (Boolean) result) {
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, "
                    + "window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
        }
    }

}
