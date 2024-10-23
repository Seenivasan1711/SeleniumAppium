package com.example.appium_local_gradle;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.example.appium_local_gradle.AppiumLocalGradleApplication.getCurrentTime;

public class LamdatestAppium {

    static String username = "engineeringtestsigma";
    static String accessKey = "XK6ARxr0FsuYM7Uur0FdNVMMRRTrG7VGMWiEwLE5ASBSN8gqOO";

    static AppiumDriver driver = null;
    private static WebDriverWait wait;

    enum SwipeType {
        UP, DOWN
    }

    public static void execute() {

        try {
            // LambdaTest Appium endpoint
            String appiumUrl = "https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";

            driver = new AndroidDriver(new URL(appiumUrl), getDesiredCapabilities());
            wait = new WebDriverWait(driver, 30);

            System.out.println(getCurrentTime() + "Appium session started" );

            executionForTE21448();

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
//        capabilities.setCapability("app", "lt://APP10160361821720077340941779");
        capabilities.setCapability("app", "lt://APP10160381691725265950386412");
        capabilities.setCapability("build", "Local Appium dev check TE-21448");
        capabilities.setCapability("name", "SpaceWell");
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



    private static void executionForTE21448() {
        navigateToSelectDate();
        checkSwipeOrScroll();
    }

    private static void navigateToSelectDate() {
        String email_id = "fm.mcs.pav1t1:id/login_email_edittxt";
        sendKeys(email_id, "axxtest1.user1");
        String login_next = "fm.mcs.pav1t1:id/login_next_btn";
        clickOnElementByID(login_next);
        String password_txt = "fm.mcs.pav1t1:id/login_password_edittxt";
        waitUntilElementIsPresentById(password_txt);
        sendKeys(password_txt, "Mcs@123");
        clickOnElementByID(login_next);
        String india = "fm.mcs.pav1t1:id/listItemContainer";
        waitUntilElementIsPresentById(india);
        clickOnElementByID(india);
        String dont_allow = "com.android.permissioncontroller:id/permission_deny_button";
        clickOnElementByID(dont_allow);
        String book_room = "fm.mcs.pav1t1:id/mainContent";
        waitUntilElementIsPresentById(book_room);
        clickOnElementByID(book_room);
        String multi_day = "fm.mcs.pav1t1:id/multiDayCheckUnCheckImageView";
        waitUntilElementIsPresentById(multi_day);
        clickOnElementByID(multi_day);
        String select_date = "fm.mcs.pav1t1:id/selectFromDayTimeTextView";
        clickOnElementByID(select_date);
    }

    private static void checkSwipeOrScroll() {
        try {
            String currentDaySelected = "//*[@resource-id=\"fm.mcs.pav1t1:id/daysList\"]//*[@class=\"android.widget.LinearLayout\" and @index=\"3\"]";
            String currentHourSelected = "//*[@resource-id=\"fm.mcs.pav1t1:id/hoursList\"]//*[@class=\"android.widget.LinearLayout\" and @index=\"3\"]";
            String currentMinSelected = "//*[@resource-id=\"fm.mcs.pav1t1:id/minutesList\"]//*[@class=\"android.widget.LinearLayout\" and @index=\"3\"]";
            String currentAMOrPMSelected = "//*[@resource-id=\"fm.mcs.pav1t1:id/meridiemList\"]//*[@class=\"android.widget.LinearLayout\" and @index=\"3\"]";

            String expectedDay = "//*[@text=\"Fri 20 Sep\"]";
            String expectedHour = "//*[@text=\"1\"]";
            String expectedMin = "//*[@text=\"15\"]";
            String expectedMeridiem = "//*[@text=\"PM\"]";

            scrollToElementAtPosition(expectedDay, currentDaySelected);
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static void hideKeyBoard() {
        driver.execute("hideKeyboard");
    }

    private static void sendKeys(String id, String text) {
        try {
            System.out.println(getCurrentTime() + "Trying to sendKeys on id:: " + id);
            driver.findElement(By.id(id)).sendKeys(text);
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
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

    private static void clickOnElementByID(String id) {
        try {
            System.out.println(getCurrentTime() + "Trying to click on id:: " + id);
            driver.findElement(By.id(id)).click();
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
        }
    }

    private static void waitUntilElementIsPresentById(String id) {
        try {
            System.out.println(getCurrentTime() + "Trying to wait for " + id);
            // Wait until the element is present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        } catch (Exception e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            throw new RuntimeException(e);
        }
    }

    // ---------------------------------------------------------------------------------------------------------------

    // Scroll the expected element into the target location on the screen
    public static void scrollToElementAtPosition(String expectedElementXpath, String expectedElementLocationXpath) {
        int swipeAttempts = 0;
        WebElement initialSelectedElement = driver.findElement(By.xpath(expectedElementLocationXpath));
        WebElement initialSelectedElementTextView = initialSelectedElement.findElement(By.id("fm.mcs.pav1t1:id/timeIntervalTxtView"));

        // Get the coordinates of the area where the expected element needs to appear
        Map<String, Integer> expectedElementLocationCoOrdinates = getElementCoordinates(initialSelectedElement);

        while (true) {
            WebElement dynamicLocationElement = driver.findElement(By.xpath(expectedElementLocationXpath));
            boolean isExpectedElementAtPosition = areElementCoordinatesMatching(expectedElementXpath, expectedElementLocationCoOrdinates);

            if (isExpectedElementAtPosition) {
                System.out.println("Expected element found at the expected location.");
                break;
            }

            SwipeType swipeType = SwipeType.UP;
            if (swipeAttempts == 1 && isSameElement(dynamicLocationElement, initialSelectedElementTextView)) {
                swipeType = SwipeType.DOWN;
            }

            swipeElement(expectedElementLocationCoOrdinates, swipeType);
            swipeAttempts++;
        }
    }

    // Check if the expected element is in the correct location by comparing coordinates
    private static Boolean areElementCoordinatesMatching(String expectedElementXpath, Map<String, Integer> expectedElementLocationCoOrdinates) {
        WebElement expectedElement = driver.findElement(By.xpath(expectedElementXpath));
        Map<String, Integer> actualElementCoordinates = getElementCoordinates(expectedElement);

        for (String key : expectedElementLocationCoOrdinates.keySet()) {
            if (!actualElementCoordinates.get(key).equals(expectedElementLocationCoOrdinates.get(key))) {
                return false;
            }
        }

        return true;
    }

    // Check if both elements are the same by comparing their text values
    private static Boolean isSameElement(WebElement currentElement, WebElement previousTextView) {
        // Find the child TextView within the current and previous elements
        WebElement currentTextView = currentElement.findElement(By.id("fm.mcs.pav1t1:id/timeIntervalTxtView"));

        // Get the text from the TextView elements
        String currentText = currentTextView.getText();
        String previousText = previousTextView.getText();

        // Compare the text values
        return currentText.equals(previousText);
    }


    // Get the coordinates (top-left and bottom-right) of the given element
    public static Map<String, Integer> getElementCoordinates(WebElement element) {
        Map<String, Integer> coordinates = new HashMap<>();

        // Get the location and size of the element
        Point location = element.getLocation();
        Dimension size = element.getSize();

        // Calculate and store the four corners' coordinates
        coordinates.put("topLeftX", location.getX());
        coordinates.put("topLeftY", location.getY());
        coordinates.put("topRightX", location.getX() + size.getWidth());
        coordinates.put("topRightY", location.getY());
        coordinates.put("bottomLeftX", location.getX());
        coordinates.put("bottomLeftY", location.getY() + size.getHeight());
        coordinates.put("bottomRightX", location.getX() + size.getWidth());
        coordinates.put("bottomRightY", location.getY() + size.getHeight());

        return coordinates;
    }

    // Perform a swipe action based on the given coordinates and direction
    private static void swipeElement(Map<String, Integer> elementCoordinates, SwipeType swipeType) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Get starting X and Y coordinates from the element's location
        int startX = elementCoordinates.get("topLeftX");
        int startY = elementCoordinates.get("bottomLeftY");
        int endY = elementCoordinates.get("topLeftY");

        // Calculate the swipe distance (height of one element, with buffer)
        int swipeDistance = (int) ((startY - endY) * 1.5);

        // Create a swipe action using the W3C Actions API
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)); // Move to the start point
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Press down
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, swipeType == SwipeType.UP ? (startY - swipeDistance) : (startY + swipeDistance))); // Move to the end point based on swipe type
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release the touch

        // Execute the swipe action
        driver.perform(Collections.singletonList(swipe));
        System.out.println("Swiped one element " + swipeType);
    }


    // -------------------------------------------------------------------------------------------------------------------

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

    // Scroll the element into view
    public static void scrollToElement(String expectedElementXpath) {
        WebElement expectedElement = driver.findElement(By.xpath(expectedElementXpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", expectedElement);
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
}
