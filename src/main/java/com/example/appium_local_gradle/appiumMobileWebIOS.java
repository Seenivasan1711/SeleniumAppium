package com.example.appium_local_gradle;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class appiumMobileWebIOS {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "Safari");
//        caps.setCapability("appium:deviceName", "iPhone 11 Pro");
        caps.setCapability("appium:deviceName", "iPhone 12 mini");

        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("udid", "00008101-000960400A30001E");
//        MutableCapabilities sauceOptions = new MutableCapabilities();
//        sauceOptions.setCapability("appiumVersion", "latest");
//        sauceOptions.setCapability("username", "krukmangada");
//        sauceOptions.setCapability("accessKey", "846508c1-b42d-4fc7-a1e1-0770df454237");
//        sauceOptions.setCapability("build", "<Upload safari test>");
//        sauceOptions.setCapability("name", "<Testing Uplaod in iPhone Safari>");
//        caps.setCapability("sauce:options", sauceOptions);

        URL url1 = new URL("http://localhost:4723/");
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        IOSDriver driver = new IOSDriver(url1, caps);
//
//        // Example: Navigate to a URL directly
//        driver.get("https://travel.testsigma.com/signup");
//        Thread.sleep(1000);
//
//        // Scroll to the bottom of the page
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//        // Find the element
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"formid\"]/div[6]/div/div[1]/input"));
//
//        // Trigger a touch event on the element
//        js.executeScript("var event = new TouchEvent('touchend', { bubbles: true }); arguments[0].dispatchEvent(event);", element);
        driver.get("https://github.com//sakinala/AutomationTesting/raw/master/samplefile.pdf");

        WebDriverWait wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

        ((JavascriptExecutor) driver).executeScript("window.onbeforeunload = function(e){};");

        driver.quit(); // Quit the driver session when done

    }

}
