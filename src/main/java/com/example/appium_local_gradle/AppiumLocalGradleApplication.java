package com.example.appium_local_gradle;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class AppiumLocalGradleApplication {


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
//        openSafariAndSearch();
//        goUpToCheckInButtonAndClick();
//        selenium();
//        sample.main(args);
//        pdf.main(args);
//        appiumMobileWebIOS.main(args);
//        LamdatestAppium.execute();
//        LambdaTestMobileWeb.execute();
//        DragAndDropFireFox.execute();
//        BrowserStackAppium.execute();
//        LambdaTestOCR.execute();
        LambdatestSelenium.execute();
    }


    public static void goUpToCheckInButtonAndClick() throws MalformedURLException {
        AppiumDriver driver = null;

        try {
            driver = new IOSDriver(new URL("http://localhost:4723/"), getDesiredCapabilities());

            driver.findElement(By.xpath("//android.widget.TextView[@text=\"Log In with Email\"]")).click();

            driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"Login.email\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText")).click();

            driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"Login.email\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText")).sendKeys("sn2hulk+40001784@gmail.com");

            driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"Login.password\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText")).click();

            driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"Login.password\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText")).sendKeys("sN@202401&hK=");

            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Proceed\"]")).click();

            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();

            driver.findElement(By.id("button.checkin")).click();

        } catch (Exception e) {
            System.out.println("Error ::" + e + "Timing : " + getCurrentTime());
        }
    }

    public static void openSafariAndSearch() throws MalformedURLException, InterruptedException {
        AppiumDriver driver = null;
        try {

            String username = "engineeringtestsigma";
            String accessKey = "XK6ARxr0FsuYM7Uur0FdNVMMRRTrG7VGMWiEwLE5ASBSN8gqOO";
            // LambdaTest Appium endpoint
            String appiumUrl = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

            // for local devices
//            driver = new IOSDriver(new URL("http://localhost:4723/"), getDesiredCapabilities());
            driver = new IOSDriver(new URL(appiumUrl), getDesiredCapabilities());

            System.out.println("Appium session started @ " + getCurrentTime());
            System.out.println("Safari opened successfully!");

            System.out.println("Trying to go google");
            driver.get("https://www.google.com");
            System.out.println("Opened google");

            int searchCount = 1;
            while (searchCount <= 30) {
                // Perform search with different terms
                String searchTerm = "term" + searchCount;
                System.out.println("Searched for: " + searchTerm);
                searchOnGoogle(driver, searchTerm);

                // Wait for 1 minute before the next search
                Thread.sleep(40000);

                searchCount++;
            }
        } catch (Exception e) {
            Thread.sleep(10000);
            if (driver != null) {
                searchOnGoogle(driver, "checking from catch");
                driver.quit();
            }
            System.out.println("Automation stopped.");
            System.out.println("Appium session ended @ " + getCurrentTime());

        } finally {
            if (driver != null) {
                driver.quit();
            }
            System.out.println("Automation stopped.");
            System.out.println("Appium session ended @ " + getCurrentTime());
        }
    }

    public static void searchOnGoogle(RemoteWebDriver driver, String searchTerm) throws InterruptedException {
//        // Check if the element with the specified XPath is present
//        if (isElementPresent(driver, "//XCUIElementTypeStaticText[@name='Yes']")) {
//            // If present, click on it
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Yes']")).click();
//            System.out.println("Clicked on the element with XPath '//XCUIElementTypeStaticText[@name='Yes']'");
//        }
        Thread.sleep(10000);
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Search\"]")).sendKeys(searchTerm);
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[contains(@name,'Return')]")).click();
        System.out.println("completed google search. Finished @ " + getCurrentTime());
    }

    // Method to check if an element is present
    public static boolean isElementPresent(RemoteWebDriver driver, String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static String getCurrentTime() {
        // Get current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentTime.format(formatter) + "  ::  ";
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // For iOS

//        capabilities.setCapability("platformName", "iOS");
////        capabilities.setCapability("platformVersion", "16.6");
////        capabilities.setCapability("udid", "00008030-0002390E2128C02E");
////        capabilities.setCapability("deviceName", "Support Team Ipad");
//        capabilities.setCapability("appium:automationName", "XCUITest");
//
//        capabilities.setCapability("showXcodeLog", "true");
//        capabilities.setCapability("bundleId", "com.apple.mobilesafari");
//        capabilities.setCapability("noReset", "true");
//        capabilities.setCapability("usePrebuiltWDA", "true");
//
//        capabilities.setCapability("platformVersion", "14.5");
//        capabilities.setCapability("deviceName", "iPhone 11");

        // For Android - local

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "/Users/seenivasan/Downloads/parent-app-ms-qa-rel51-#13689.apk");

        return capabilities;
    }


    // for selenium browser check

    public static void selenium() {
        try {
            // Set Safari browser executable location
            System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");


            // Set Safari options
            SafariOptions safariOptions = new SafariOptions();
//        safariOptions.setCapability("autoAcceptAlerts", true); // Enable auto-accepting alerts

            // Initialize Safari driver with options
            WebDriver driver = new SafariDriver(safariOptions);

            // Open the URL locally
            driver.get("https://www.techlistic.com/p/selenium-practice-form.html");

            // Scroll to the file input element
            scrollToElement(driver, driver.findElement(By.xpath("//INPUT[@id='photo']")));

            // Locate the file input element
            WebElement fileInput = driver.findElement(By.xpath("//INPUT[@id='photo']")); // Change this according to your HTML

            // Upload file
            fileInput.sendKeys("/var/folders/72/kddk2cnj6y95q5wclc008zl40000gq/T//newImage%.bmp");

            Thread.sleep(5000);

            // Close the browser
            driver.quit();
        } catch (Exception e) {
            System.out.println("Exception ::" + e);
        }
    }

    // Function to scroll to an element
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}


