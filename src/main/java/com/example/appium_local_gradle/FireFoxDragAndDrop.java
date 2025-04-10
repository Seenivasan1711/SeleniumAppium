//package com.example.appium_local_gradle;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//
//import java.time.Duration;
//
//public class FirefoxDragAndDrop extends SeleniumBase {
//
//    String js = "(function simulateHtml5DragAndDrop() {\n" +
//            "  /**\n" +
//            "   * Utility to create a CustomEvent with a mock dataTransfer.\n" +
//            "   */\n" +
//            "  function createDragEvent(eventType) {\n" +
//            "    const event = new CustomEvent(eventType, { bubbles: true, cancelable: true });\n" +
//            "    event.dataTransfer = {\n" +
//            "      data: {},\n" +
//            "      setData: function (key, value) {\n" +
//            "        this.data[key] = value;\n" +
//            "      },\n" +
//            "      getData: function (key) {\n" +
//            "        return this.data[key];\n" +
//            "      }\n" +
//            "    };\n" +
//            "    return event;\n" +
//            "  }\n" +
//            "\n" +
//            "  // 1) Pick the source (draggable) and target (droppable) elements.\n" +
//            "  const sourceSelector = '#draggable';  // <-- Replace with your draggable element's CSS selector\n" +
//            "  const targetSelector = '#droppable';  // <-- Replace with your droppable element's CSS selector\n" +
//            "\n" +
//            //  "  const source = document.querySelector('li.nm-tree-entry--expanded:nth-child(6) > ul:nth-child(3) > li:nth-child(4)');\n" +
//            //  "  const target = document.querySelector('li.nm-tree-entry--expanded:nth-child(7) > ul:nth-child(3) > li:nth-child(1)');\n" +
//            "const source = arguments[0]; " +
//            "const target =arguments[1];" +
//            "if (!source || !target) {\n" +
//            "    console.error('Could not find source or target element. Check your selectors.');\n" +
//            "    return;\n" +
//            "  }\n" +
//            "\n" +
//            "  // 2) Scroll them into view (optional, but often helpful).\n" +
//            "  source.scrollIntoView({ block: 'center' });\n" +
//            "  target.scrollIntoView({ block: 'center' });\n" +
//            "\n" +
//            "  // 3) Dispatch the HTML5 drag-and-drop events in order.\n" +
//            "  \n" +
//            "  // dragstart on source\n" +
//            "  const dragStartEvent = createDragEvent('dragstart');\n" +
//            "  source.dispatchEvent(dragStartEvent);\n" +
//            "\n" +
//            "  // dragenter on target (not always necessary, but can be required by some apps)\n" +
//            "  const dragEnterEvent = createDragEvent('dragenter');\n" +
//            "  dragEnterEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//            "  target.dispatchEvent(dragEnterEvent);\n" +
//            "\n" +
//            "  // dragover on target\n" +
//            "  const dragOverEvent = createDragEvent('dragover');\n" +
//            "  dragOverEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//            "  target.dispatchEvent(dragOverEvent);\n" +
//            "\n" +
//            "  // drop on target\n" +
//            "  const dropEvent = createDragEvent('drop');\n" +
//            "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//            "  target.dispatchEvent(dropEvent);\n" +
//            "\n" +
//            "  // dragend on source\n" +
//            "  const dragEndEvent = createDragEvent('dragend');\n" +
//            "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//            "  source.dispatchEvent(dragEndEvent);\n" +
//            "\n" +
//            "  console.log('HTML5 drag-and-drop simulation complete.');\n" +
//            "})();\n";
//
//    public static void main(String... a) {
//        try {
//            FirefoxDragAndDrop firefoxDragAndDrop = new FirefoxDragAndDrop();
//            firefoxDragAndDrop.test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (driver != null) driver.quit();
//        }
//    }
//
//    private void test() throws Exception {
//        WebDriverManager wdm = WebDriverManager.chromedriver().cachePath("C:\\Users\\rajesh\\Downloads\\chromedriver_win32");
//        wdm.setup();
//        initFirefoxDriver();
//        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
//        driver.get("https://mas-e2e-cp-48.rnd.ishop.novomind.dev/login/");
//        enterData(By.id("username"), "Testsigma1");
//        enterData(By.id("password"), "iSHOP12345");
//        click(By.xpath("//button[@type='submit']"));
//        Thread.sleep(5000);
//        driver.get("https://novosales-e2e-cp-48.rnd.ishop.novomind.dev/bo-client/app/vue-bo/#/en/novosales-de-de/content-management/categories-and-pages");
//        clickIgnore("//span[contains(text(),'TS-Drag')]//ancestor::div[contains(@class,'navigation')]/../span[contains(@class,'expand')]");
//        clickIgnore("//span[contains(text(),'TS-Drop')]//ancestor::div[contains(@class,'navigation')]/../span[contains(@class,'expand')]");
//        Thread.sleep(6000);
//        String t = "(function simulateHtml5DragAndDrop() {\n" +
//                "  /**\n" +
//                "   * Utility to create a CustomEvent with a mock dataTransfer.\n" +
//                "   */\n" +
//                "  function createDragEvent(eventType) {\n" +
//                "    const event = new CustomEvent(eventType, { bubbles: true, cancelable: true });\n" +
//                "    event.dataTransfer = {\n" +
//                "      data: {},\n" +
//                "      setData: function (key, value) {\n" +
//                "        this.data[key] = value;\n" +
//                "      },\n" +
//                "      getData: function (key) {\n" +
//                "        return this.data[key];\n" +
//                "      }\n" +
//                "    };\n" +
//                "    return event;\n" +
//                "  }\n" +
//                "\n" +
//                "  // 1) Pick the source (draggable) and target (droppable) elements.\n" +
//                "  const sourceSelector = '#draggable';  // <-- Replace with your draggable element's CSS selector\n" +
//                "  const targetSelector = '#droppable';  // <-- Replace with your droppable element's CSS selector\n" +
//                "\n" +
//                "  const source = document.querySelector('li.nm-tree-entry--expanded:nth-child(1) > ul:nth-child(3) > li');\n" +
//                "  const target = document.querySelector('li.nm-tree-entry:nth-child(12) > ul:nth-child(3) > li:nth-child(1)');\n" +
//                "  if (!source || !target) {\n" +
//                "    console.error('Could not find source or target element. Check your selectors.');\n" +
//                "    return;\n" +
//                "  }\n" +
//                "\n" +
//                "  // 2) Scroll them into view (optional, but often helpful).\n" +
//                "  source.scrollIntoView({ block: 'center' });\n" +
//                "  target.scrollIntoView({ block: 'center' });\n" +
//                "\n" +
//                "  // 3) Dispatch the HTML5 drag-and-drop events in order.\n" +
//                "  \n" +
//                "  // dragstart on source\n" +
//                "  const dragStartEvent = createDragEvent('dragstart');\n" +
//                "  source.dispatchEvent(dragStartEvent);\n" +
//                "\n" +
//                "  // dragenter on target (not always necessary, but can be required by some apps)\n" +
//                "  const dragEnterEvent = createDragEvent('dragenter');\n" +
//                "  dragEnterEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//                "  target.dispatchEvent(dragEnterEvent);\n" +
//                "\n" +
//                "  // dragover on target\n" +
//                "  const dragOverEvent = createDragEvent('dragover');\n" +
//                "  dragOverEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//                "  target.dispatchEvent(dragOverEvent);\n" +
//                "\n" +
//                "  // drop on target\n" +
//                "  const dropEvent = createDragEvent('drop');\n" +
//                "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//                "  target.dispatchEvent(dropEvent);\n" +
//                "\n" +
//                "  // dragend on source\n" +
//                "  const dragEndEvent = createDragEvent('dragend');\n" +
//                "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;\n" +
//                "  source.dispatchEvent(dragEndEvent);\n" +
//                "\n" +
//                "  console.log('HTML5 drag-and-drop simulation complete.');\n" +
//                "})();\n";
//        jsDriver.executeScript(t);
//        WebElement dragElement = null;
//        WebElement dropElement = null;
//        dragElement = driver.findElement(By.xpath("(//span[contains(text(),'Drag Element')]/ancestor::li)[last()]"));
//        dropElement = driver.findElement(By.xpath("(//span[contains(text(),'Drop Element')]/ancestor::li)[last()]"));
//        String dragIndicatorXpath = "div[contains(@class,'drag-over-indicator')]";
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(By.xpath("(//span[contains(text(),'Drag Element')]/ancestor::li)[last()]"))).pause(Duration.ofSeconds(3)).click().build().perform();
//        Thread.sleep(3000);
//        Actions actions1 = new Actions(driver);
//        actions1.moveToElement(driver.findElement(By.xpath("(//span[contains(text(),'Drag Element')]/ancestor::li)[last()]"))).pause(Duration.ofSeconds(3)).click().build().perform();
//
//
//        actions.moveToElement(driver.findElement(By.xpath(dragIndicatorXpath))).pause(Duration.ofSeconds(3)).click().pause(Duration.ofSeconds(3)).clickAndHold(driver.findElement(By.xpath(dragIndicatorXpath))).pause(Duration.ofSeconds(3)).moveToElement(driver.findElement(By.xpath("(//span[contains(text(),'Drop Element')]/ancestor::li)[last()]"))).pause(Duration.ofSeconds(3)).release().build().perform();
//
//
//        jsDriver.executeScript(js, dragElement, dropElement);
//        dragAndDrop(dragElement, dropElement);
//        Thread.sleep(10000);
//        System.out.println("Drag and drop successful");
//    }
//}
