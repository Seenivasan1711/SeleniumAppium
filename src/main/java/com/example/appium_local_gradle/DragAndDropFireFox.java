package com.example.appium_local_gradle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DragAndDropFireFox {

    public static void execute() {

//        System.setProperty("webdriver.gecko.driver", "/Users/seenivasan/.testsigma/web-drivers/geckodriver/mac-arm64/0.35.0/geckodriver");
//        System.setProperty("webdriver.gecko.driver", "/Users/seenivasan/Downloads/geckodriver");

        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver();

        try {
            System.out.println(getCurrentTime() + "Starting Selenium session");
            driver.manage().window().maximize();

            driver.get("https://demo.automationtesting.in/Static.html");

            System.out.println(getCurrentTime() + "Opened website...");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            WebElement fromElement = driver.findElement(By.id("angular"));
            WebElement toElement = driver.findElement(By.id("droparea"));

            System.out.println(getCurrentTime() + "Found elements");

            System.out.println(getCurrentTime() + "Trying to drag and drop using actions");
            // doDragAndDropUsingActions(driver, fromElement, toElement);
            Thread.sleep(1000);

            System.out.println(getCurrentTime() + "Trying to drag and drop using click and hold");
            doDragAndDropUsingClickAndHold(driver, fromElement, toElement);
            Thread.sleep(1000);

            System.out.println(getCurrentTime() + "Trying to drag and drop using offset");
            // doDragAndDropUsingOffset(driver, fromElement, toElement);
            Thread.sleep(1000);

            // Not working in any browser
//            System.out.println(getCurrentTime() + "Trying to drag and drop using robot");
//            doDragAndDropUsingRobot(driver, fromElement, toElement);
//            Thread.sleep(1000);

            // Only Working method in FireFox
            System.out.println(getCurrentTime() + "Trying to drag and drop using javascript");
            doDragAndDropUsingJavascript(driver, fromElement, toElement);
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(getCurrentTime() + "Closing Selenium session");
            driver.quit();
        }
    }

    private static void doDragAndDropUsingActions(WebDriver driver, WebElement fromElement, WebElement toElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(fromElement, toElement).perform();
        System.out.println(getCurrentTime() + "Drag and drop done");
    }

    private static void doDragAndDropUsingClickAndHold(WebDriver driver, WebElement fromElement, WebElement toElement) {
        Actions actions = new Actions(driver);

        actions.clickAndHold(fromElement)
                .moveToElement(toElement, 100, 100)
                .release()
                .perform();
    }

    private static void doDragAndDropUsingOffset(WebDriver driver, WebElement fromElement, WebElement toElement) {
        Actions actions = new Actions(driver);

        int xOffset = toElement.getLocation().getX() - fromElement.getLocation().getX();
        int yOffset = toElement.getLocation().getY() - fromElement.getLocation().getY();

        actions.clickAndHold(fromElement)
                .moveByOffset(xOffset, yOffset)
                .release()
                .build()
                .perform();
    }

    private static void doDragAndDropUsingRobot(WebDriver driver, WebElement fromElement, WebElement toElement) {
        try {
            int sourceX = fromElement.getLocation().getX();
            int sourceY = fromElement.getLocation().getY();
            int targetX = toElement.getLocation().getX();
            int targetY = toElement.getLocation().getY();

            Robot robot = new Robot();

            robot.mouseMove(sourceX, sourceY);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

            robot.mouseMove(targetX, targetY);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            System.out.println(getCurrentTime() + "Error ::" + e);
            e.printStackTrace();
        }
    }

    private static void doDragAndDropUsingJavascript(WebDriver driver, WebElement fromElement, WebElement toElement) {
        final String java_script =
                "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe" +
                        "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun" +
                        "ction(format,data){this.items[format]=data;this.types.append(for" +
                        "mat);},getData:function(format){return this.items[format];},clea" +
                        "rData:function(format){}};var emit=function(event,target){var ev" +
                        "t=document.createEvent('Event');evt.initEvent(event,true,false);" +
                        "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('" +
                        "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" +
                        "'drop',tgt);emit('dragend',src);";

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(java_script, fromElement, toElement);
    }

    private static void doDragAndDropUsingJavascriptPartially(WebDriver driver, WebElement targetElementFrom, WebElement targetElementTo) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Scroll source element into view
        js.executeScript("arguments[0].scrollIntoView(true);", targetElementFrom);

        // Step 2: Perform partial drag
        String partialDragScript =
                "var source = arguments[0];" +
                        "var rectSource = source.getBoundingClientRect();" +
                        "window.scrollBy(0, rectSource.y - 100);"; // Scroll to bring the target closer
        js.executeScript(partialDragScript, targetElementFrom);

        // Step 3: Scroll target element into view
        js.executeScript("arguments[0].scrollIntoView(true);", targetElementTo);

        // Step 4: Perform the drag-and-drop using JavaScript
        String dragAndDropScript =
                "function simulateDragDrop(sourceNode, destinationNode) {" +
                        "  var EVENT_TYPES = ['dragstart', 'dragenter', 'dragover', 'drop', 'dragend'];" +
                        "  function createCustomEvent(type) {" +
                        "    var event = new CustomEvent(type, { bubbles: true, cancelable: true });" +
                        "    event.dataTransfer = {" +
                        "      data: {}," +
                        "      setData: function(key, value) { this.data[key] = value; }," +
                        "      getData: function(key) { return this.data[key]; }" +
                        "    };" +
                        "    return event;" +
                        "  }" +
                        "  function dispatchEvent(node, type, event) {" +
                        "    node.dispatchEvent(event);" +
                        "  }" +
                        "  var dragStartEvent = createCustomEvent('dragstart');" +
                        "  dispatchEvent(sourceNode, 'dragstart', dragStartEvent);" +
                        "  var dragEnterEvent = createCustomEvent('dragenter');" +
                        "  dispatchEvent(destinationNode, 'dragenter', dragEnterEvent);" +
                        "  var dragOverEvent = createCustomEvent('dragover');" +
                        "  dispatchEvent(destinationNode, 'dragover', dragOverEvent);" +
                        "  var dropEvent = createCustomEvent('drop');" +
                        "  dispatchEvent(destinationNode, 'drop', dropEvent);" +
                        "  var dragEndEvent = createCustomEvent('dragend');" +
                        "  dispatchEvent(sourceNode, 'dragend', dragEndEvent);" +
                        "}" +
                        "simulateDragDrop(arguments[0], arguments[1]);";

        js.executeScript(dragAndDropScript, targetElementFrom, targetElementTo);
    }

    public static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentTime.format(formatter) + "  ::  ";
    }
}
