package com.example.appium_local_gradle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

//            driver.get("https://demo.automationtesting.in/Static.html");
//
//            System.out.println(getCurrentTime() + "Opened website...");
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//            WebElement fromElement = driver.findElement(By.id("angular"));
//            WebElement toElement = driver.findElement(By.id("droparea"));
//
//            System.out.println(getCurrentTime() + "Found elements");
//
//            System.out.println(getCurrentTime() + "Trying to drag and drop using actions");
            // doDragAndDropUsingActions(driver, fromElement, toElement);
//            Thread.sleep(1000);
//
//            System.out.println(getCurrentTime() + "Trying to drag and drop using click and hold");
//            doDragAndDropUsingClickAndHold(driver, fromElement, toElement);
//            Thread.sleep(1000);

//            System.out.println(getCurrentTime() + "Trying to drag and drop using offset");
            // doDragAndDropUsingOffset(driver, fromElement, toElement);
//            Thread.sleep(1000);

            // Not working in any browser
//            System.out.println(getCurrentTime() + "Trying to drag and drop using robot");
//            doDragAndDropUsingRobot(driver, fromElement, toElement);
//            Thread.sleep(1000);

            // Only Working method in FireFox
//            System.out.println(getCurrentTime() + "Trying to drag and drop using javascript");
//            doDragAndDropUsingJavascript(driver, fromElement, toElement);
//            Thread.sleep(1000);

            // ------------ cx scenario starts

            driver.get("https://mas-e2e-cp-48.rnd.ishop.novomind.dev/login/");

            driver.findElement(By.id("username")).sendKeys("Testsigma1");
            driver.findElement(By.id("password")).sendKeys("iSHOP12345");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            driver.get("https://novosales-e2e-cp-48.rnd.ishop.novomind.dev/bo-client/app/vue-bo/#/en/novosales-de-de/content-management/categories-and-pages");
            Thread.sleep(5000);

            driver.findElement(By.xpath("/html/body/div[1]/div/div/main/div/div/div[2]/div[1]/div[1]/div/div/div[3]/button/span[2]/i")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("/html/body/div[1]/div/div/main/div/div/div[2]/div[1]/div[1]/div/div/div[3]/div[2]/div/div/div/div/ul/li[5]/div[2]/span/span/i")).click();

            String dragElementCSS = "ul.nm-tree-list:nth-child(3) > li:nth-child(1)";
            String dropElementCSS = "li.nm-tree-entry:nth-child(6) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1)";

            String dragElementXpath = "//li[contains(@class, 'nm-tree-entry') and @draggable='true']";
            String dropElementXpath = "//div[contains(@class, 'navigation-tree-entry') and .//span[text()='TS Drop']]";

            WebElement dragElement = driver.findElement(By.xpath(dragElementXpath));
            WebElement dropElement = driver.findElement(By.xpath(dropElementXpath));

            System.out.println(getCurrentTime() + "Trying to drag and drop using javascript2");
//            doDragAndDropUsingJavaScriptWithCssSelectors(driver, dragElementCSS, dropElementCSS);
//            doDragAndDropUsingJavaScriptWithXpath(driver, dragElementXpath, dropElementXpath);
            doDragAndDropUsingJavaScriptWithElements(driver, dragElement, dropElement);
            Thread.sleep(10000);

            // ------------ cx scenario ends

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

    private static void doDragAndDropUsingJavaScriptWithCssSelectors(WebDriver driver, String fromCssSelector, String toCssSelector) {
        String jsScript =
                "(function simulateHtml5DragAndDrop(sourceSelector, targetSelector) {\n" +
                        "  function getElement(selector) {\n" +
                        "    return document.querySelector(selector);\n" +
                        "  }\n" +
                        "\n" +
                        "  function createDragEvent(eventType) {\n" +
                        "    const event = new CustomEvent(eventType, { bubbles: true, cancelable: true });\n" +
                        "    event.dataTransfer = {\n" +
                        "      data: {},\n" +
                        "      setData: function (key, value) {\n" +
                        "        this.data[key] = value;\n" +
                        "      },\n" +
                        "      getData: function (key) {\n" +
                        "        return this.data[key];\n" +
                        "      }\n" +
                        "    };\n" +
                        "    return event;\n" +
                        "  }\n" +
                        "\n" +
                        "  const source = getElement(sourceSelector);\n" +
                        "  const target = getElement(targetSelector);\n" +
                        "  console.log('[DEBUG] source', source);\n" +
                        "  console.log('[DEBUG] target', target);\n" +
                        "\n" +
                        "  if (!source || !target) {\n" +
                        "    console.error('Could not find source or target element using selectors.');\n" +
                        "    return;\n" +
                        "  }\n" +
                        "\n" +
                        "  source.scrollIntoView({ block: 'center' });\n" +
                        "  target.scrollIntoView({ block: 'center' });\n" +
                        "\n" +
                        "  const dragStartEvent = createDragEvent('dragstart');\n" +
                        "  source.dispatchEvent(dragStartEvent);\n" +
                        "\n" +
                        "  const dragEnterEvent = createDragEvent('dragenter');\n" +
                        "  dragEnterEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dragEnterEvent);\n" +
                        "\n" +
                        "  const dragOverEvent = createDragEvent('dragover');\n" +
                        "  dragOverEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dragOverEvent);\n" +
                        "\n" +
                        "  const dropEvent = createDragEvent('drop');\n" +
                        "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dropEvent);\n" +
                        "\n" +
                        "  const dragEndEvent = createDragEvent('dragend');\n" +
                        "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  source.dispatchEvent(dragEndEvent);\n" +
                        "\n" +
                        "  console.log('HTML5 drag-and-drop simulation complete.');\n" +
                        "})(arguments[0], arguments[1]);";

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(jsScript, fromCssSelector, toCssSelector);
    }

    private static void doDragAndDropUsingJavaScriptWithXpath(WebDriver driver, String fromCssSelector, String toCssSelector) {
        String jsScript =
                "(function simulateHtml5DragAndDrop(sourceSelector, targetSelector) {\n" +
                        "  function getElement(xpath) {\n" +
                        "    return document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\n" +
                        "  }\n" +
                        "\n" +
                        "  function createDragEvent(eventType) {\n" +
                        "    const event = new CustomEvent(eventType, { bubbles: true, cancelable: true });\n" +
                        "    event.dataTransfer = {\n" +
                        "      data: {},\n" +
                        "      setData: function (key, value) {\n" +
                        "        this.data[key] = value;\n" +
                        "      },\n" +
                        "      getData: function (key) {\n" +
                        "        return this.data[key];\n" +
                        "      }\n" +
                        "    };\n" +
                        "    return event;\n" +
                        "  }\n" +
                        "\n" +
                        "  const source = getElement(sourceSelector);\n" +
                        "  const target = getElement(targetSelector);\n" +
                        "  console.log('[DEBUG] source', source);\n" +
                        "  console.log('[DEBUG] target', target);\n" +
                        "\n" +
                        "  if (!source || !target) {\n" +
                        "    console.error('Could not find source or target element using selectors.');\n" +
                        "    return;\n" +
                        "  }\n" +
                        "\n" +
                        "  source.scrollIntoView({ block: 'center' });\n" +
                        "  target.scrollIntoView({ block: 'center' });\n" +
                        "\n" +
                        "  const dragStartEvent = createDragEvent('dragstart');\n" +
                        "  source.dispatchEvent(dragStartEvent);\n" +
                        "\n" +
                        "  const dragEnterEvent = createDragEvent('dragenter');\n" +
                        "  dragEnterEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dragEnterEvent);\n" +
                        "\n" +
                        "  const dragOverEvent = createDragEvent('dragover');\n" +
                        "  dragOverEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dragOverEvent);\n" +
                        "\n" +
                        "  const dropEvent = createDragEvent('drop');\n" +
                        "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dropEvent);\n" +
                        "\n" +
                        "  const dragEndEvent = createDragEvent('dragend');\n" +
                        "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  source.dispatchEvent(dragEndEvent);\n" +
                        "\n" +
                        "  console.log('HTML5 drag-and-drop simulation complete.');\n" +
                        "})(arguments[0], arguments[1]);";

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(jsScript, fromCssSelector, toCssSelector);
    }

    private static void doDragAndDropUsingJavaScriptWithElements(WebDriver driver, WebElement sourceElement, WebElement targetElement) {
        String jsScript =
                "function createDragEvent(eventType) {\n" +
                        "  const event = new CustomEvent(eventType, { bubbles: true, cancelable: true });\n" +
                        "  event.dataTransfer = {\n" +
                        "    data: {},\n" +
                        "    setData: function(key, value) { this.data[key] = value; },\n" +
                        "    getData: function(key) { return this.data[key]; }\n" +
                        "  };\n" +
                        "  return event;\n" +
                        "}\n" +
                        "\n" +
                        "function simulateDragAndDrop(source, target) {\n" +
                        "  if (!source || !target) {\n" +
                        "    console.error('Source or target element not found');\n" +
                        "    return;\n" +
                        "  }\n" +
                        "\n" +
                        "  source.scrollIntoView({ block: 'center' });\n" +
                        "  target.scrollIntoView({ block: 'center' });\n" +
                        "\n" +
                        "  const dragStartEvent = createDragEvent('dragstart');\n" +
                        "  source.dispatchEvent(dragStartEvent);\n" +
                        "\n" +
                        "  const dragEnterEvent = createDragEvent('dragenter');\n" +
                        "  dragEnterEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dragEnterEvent);\n" +
                        "\n" +
                        "  const dragOverEvent = createDragEvent('dragover');\n" +
                        "  dragOverEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dragOverEvent);\n" +
                        "\n" +
                        "  const dropEvent = createDragEvent('drop');\n" +
                        "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  target.dispatchEvent(dropEvent);\n" +
                        "\n" +
                        "  const dragEndEvent = createDragEvent('dragend');\n" +
                        "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
                        "  source.dispatchEvent(dragEndEvent);\n" +
                        "\n" +
                        "  console.log('HTML5 drag-and-drop simulation complete.');\n" +
                        "}\n" +
                        "\n" +
                        "simulateDragAndDrop(arguments[0], arguments[1]);";

        ((JavascriptExecutor) driver).executeScript(jsScript, sourceElement, targetElement);
    }

    public static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentTime.format(formatter) + "  ::  ";
    }
}