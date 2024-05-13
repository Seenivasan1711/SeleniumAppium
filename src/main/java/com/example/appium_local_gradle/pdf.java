package com.example.appium_local_gradle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdf {

    public static void main(String[] args) {
        // Set system properties for Chrome driver
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Enable headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new --disable-gpu");
        options.setCapability("goog:chromeOptions", "{\"args\":[\"--headless=new --disable-gpu\"]}");

        // Initialize ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the URL
        driver.get("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");

        StringBuffer sb = new StringBuffer();
        URL url;
        BufferedInputStream fileParse;
        PDDocument doc = null;
        try {
            url = new URL("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
            InputStream is = url.openStream();   //Converting url as input
            fileParse = new BufferedInputStream(is);   //reads data from file
            doc = PDDocument.load(fileParse); //loads the file as pdf
            sb.append(new PDFTextStripper().getText(doc));
            Assert.assertTrue(sb.toString().contains("Dummy")); // Change this to your text
            System.out.println("Text found in PDF");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            try {
                if (doc != null) {
                    doc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Close the browser
            driver.quit();
        }
    }
}
