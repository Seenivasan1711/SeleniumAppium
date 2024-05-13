package com.example.appium_local_gradle;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class sample {

    public static void main(String[] args) {
        // Set headless mode
        System.setProperty("java.awt.headless", "true");

        // Check if the current environment is headless
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Running in headless environment.");
        } else {
            System.out.println("Running in graphical environment.");
        }

        // Path to the sample PDF file
        String pdfFilePath = "/Users/seenivasan/Downloads/pdf_actions/dummy.pdf";

        try {
            // Load the PDF document
            PDDocument document = PDDocument.load(new File(pdfFilePath));

            // Create PDFRenderer object
            PDFRenderer renderer = new PDFRenderer(document);

            // Render each page of the PDF
            for (int pageNum = 0; pageNum < document.getNumberOfPages(); pageNum++) {
                // Render the page
                renderer.renderImage(pageNum);

                // You can optionally save the rendered image to file
                // For example:
                // String outputFileName = "page_" + (pageNum + 1) + ".png";
                // ImageIO.write(renderer.renderImage(pageNum), "png", new File(outputFileName));
            }

            // Close the document
            document.close();

            System.out.println("PDF rendering successful.");

        } catch (IOException e) {
            // Handle any potential IO exceptions
            e.printStackTrace();
            System.err.println("Error rendering PDF: " + e.getMessage());
        }
    }
}
