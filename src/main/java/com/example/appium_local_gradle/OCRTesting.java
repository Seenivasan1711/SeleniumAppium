package com.example.appium_local_gradle;

//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import com.testsigma.sdk.ApplicationType;
//import com.testsigma.sdk.IOSAction;
//import com.testsigma.sdk.OCRImage;
//import com.testsigma.sdk.OCRTextPoint;
//import com.testsigma.sdk.Result;
//import com.testsigma.sdk.annotation.Action;
//import com.testsigma.sdk.annotation.OCR;
//import com.testsigma.sdk.annotation.TestData;
//
//import io.appium.java_client.ios.IOSDriver;
//import lombok.Data;
//import org.openqa.selenium.interactions.Pause;
//import org.openqa.selenium.interactions.PointerInput;
//import org.openqa.selenium.interactions.Sequence;
//
//import javax.imageio.ImageIO;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Properties;
//
//import static java.time.Duration.ofMillis;
//import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
//import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
//import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
//
//@Data
public class OCRTesting {
//
//    protected int imageWidth;
//    protected int imageHeight;
//    protected int screenWidth;
//    protected int screenHeight;
//    protected int clickLocationX;
//    protected int clickLocationY;
//
//    public OCRTextPoint textPointRedFill;
//
//    @Override
//    protected Result execute() {
//        Result result = Result.SUCCESS;
//        try {
//            logger.info("Taking screenshot");
//            File screenshot = ((TakesScreenshot) (IOSDriver) this.driver).getScreenshotAs(OutputType.FILE);
//            logger.info("Taking screenshot completed");
//
//            setImageDimensions(screenshot);
//            OCRImage imageObj = new OCRImage();
//            imageObj.setOcrImageFile(screenshot);
//            List<OCRTextPoint> textPoints = ocr.extractTextFromImage(imageObj);
//            logger.info("Extracted text from image:" + textPoints.toString());
//
//            List<OCRTextPoint> TextCompare = new ArrayList<>();
//            for (OCRTextPoint SimiText : textPoints) {
//                if (text.getValue().toString().equals(SimiText.getText())) {
//                    TextCompare.add(SimiText);
//                }
//            }
//            logger.info("Number of text available " + String.valueOf(TextCompare.size()));
//            logger.info("Available text " + TextCompare);
//
//            int selectedIndex = Integer.parseInt(index.getValue().toString());
//
//            if (selectedIndex < 0 || selectedIndex >= TextCompare.size()) {
//                logger.info("Invalid index provided: " + selectedIndex);
//                result = Result.FAILED;
//                setErrorMessage("Invalid index");
//            } else {
//                OCRTextPoint textPoint = TextCompare.get(selectedIndex);
//                textPointRedFill = textPoint;
//                logger.info("Selected text to perform click " + textPoint);
//                setScreenDimensions();
//                setRelativeClickLocations(textPoint);
//                printAllCoordinates(textPoint);
//
//                if (textPoint == null) {
//                    result = Result.FAILED;
//                    setErrorMessage("Not found");
//                } else {
//                    logger.info("Found Textpoint with text = " + textPoint.getText() + ", x1 = " + textPoint.getX1() +
//                            ", y1 = " + textPoint.getY1() + ", x2 = " + textPoint.getX2() + ", y2 = " + textPoint.getY2());
//                    performClick();
//                }
//            }
//        } catch (Exception e) {
//            setErrorMessage("inside addon - " + ExceptionUtils.getStackTrace(e));
//            result = Result.FAILED;
//        }
//        return result;
//    }
//
//
//    private void printAllCoordinates(OCRTextPoint textPoint) {
//
//        logger.info("text = " + textPoint.getText() + ", x1 = " + textPoint.getX1() + ", y1 = " + textPoint.getY1() +
//                ", x2 = " + textPoint.getX2() + ", y2 = " + textPoint.getY2() + "\n\n\n\n");
//
//    }
//
//    private void setImageDimensions(File screenshot){
//        try {
//            // Read the screenshot file into BufferedImage
//            BufferedImage image = ImageIO.read(screenshot);
//
//            // Get width and height of the image
//            int width = image.getWidth();
//            int height = image.getHeight();
//
//            System.out.println("Screenshot Width: " + width);
//            System.out.println("Screenshot Height: " + height);
//
//            this.setImageHeight(height);
//            this.setImageWidth(width);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void setScreenDimensions() {
//        Dimension dimension = ((IOSDriver) this.driver).manage().window().getSize();
//        this.screenWidth = dimension.getWidth();
//        this.screenHeight = dimension.getHeight();
//    }
//
//    protected void setRelativeClickLocations(OCRTextPoint ocrTextPoint) {
//        logger.info("Text found with coordinates according to visual server -  X1: " + ocrTextPoint.getX1()+" X2: " + ocrTextPoint.getX2()
//                + " Y1: " + ocrTextPoint.getY1() + " Y2: " + ocrTextPoint.getY2());
//        int x = (ocrTextPoint.getX1() + ocrTextPoint.getX2()) / 2;
//        int y = (ocrTextPoint.getY1() + ocrTextPoint.getY2()) / 2;
//        calculateRelativeCoordinates(x, y);
//    }
//
//    private void calculateRelativeCoordinates(int x, int y) {
//        double xRelative = ((double) x / imageWidth);
//        double yRelative = ((double) y / imageHeight);
//        logger.info("Ratio values for image and screen - xRelative ratio: " + xRelative + "yRelative ratio: " + yRelative);
//        this.setClickLocationX((int)(xRelative * screenWidth));
//        this.setClickLocationY((int)(yRelative * screenHeight));
//        logger.info("Click location values - x: " + (int)(xRelative * screenWidth) + "y: " + (int)(yRelative * screenHeight));
//    }
//
//    protected void performClick() throws AWTException, IOException {
//
//        //--------------------------------------------------------------------------------------------------------------
//
//        logger.info("Taking screenshot");
//        File screenshot = ((TakesScreenshot) (IOSDriver) this.driver).getScreenshotAs(OutputType.FILE);
//
//        logger.info("Reading image");
//        // Read the screenshot file into BufferedImage
//        BufferedImage image = ImageIO.read(screenshot);
//
//        logger.info("Assigning width and height");
//        // Get width and height of the image
//        int width = image.getWidth();
//        int height = image.getHeight();
//        logger.info("Image width: " + width + "Image height: " + height);
//
//        logger.info("Creating Graphics2D");
//        // Draw overlay for debugging
//        Graphics2D g2d = image.createGraphics();
//
//        logger.info("Drawing rectangle RED");
//        // Draw red outline for screen dimensions
//        g2d.setColor(Color.RED);
//        g2d.setStroke(new BasicStroke(5));
//        g2d.drawRect(0, 0, width - 1, height - 1);
//
//        logger.info("Drawing rectangle BLUE");
//        g2d.setColor(Color.BLUE);
//        g2d.setStroke(new BasicStroke(5));
//        g2d.drawRect(textPointRedFill.getX1(), textPointRedFill.getY1(), textPointRedFill.getX2(), textPointRedFill.getY2());
//
//        logger.info("Drawing circle CYAN");
//        g2d.setColor(Color.CYAN);
//        int markerSize2 = 20;
//        g2d.fillOval((textPointRedFill.getX1()), (textPointRedFill.getY1()), markerSize2, markerSize2);
//
//        logger.info("Drawing circle MAGENTA");
//        logger.info("Click location - x: " + this.clickLocationX + "y: " + this.clickLocationY);
//        // Draw yellow circle at click location
//        g2d.setColor(Color.MAGENTA);
//        int markerSize = 20;
//        g2d.fillOval(this.clickLocationX, this.clickLocationY, markerSize, markerSize);
//
//        g2d.dispose();
//
//        logger.info("Saving screenshot with markers");
//        // Save the modified image back to file for viewing
//        File markedImageFile = new File("marked_screenshot.png");
//        ImageIO.write(image, "png", markedImageFile);
//
//        logger.info("Screenshot with markers saved at: " + markedImageFile.getAbsolutePath());
//
//        sendEmailWithAttachment("seenivasan.a@testsigma.com",
//                "Marked Screenshot",
//                "Here is the marked screenshot for the touch location.",
//                markedImageFile.getAbsolutePath());
//
//
//        //--------------------------------------------------------------------------------------------------------------
//
//        IOSDriver androidDriver = (IOSDriver) this.driver;
//        PointerInput FINGER = new PointerInput(TOUCH, "finger");
//        Sequence tap = new Sequence(FINGER, 1)
//                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), this.clickLocationX, this.clickLocationY + 10))
//                .addAction(FINGER.createPointerDown(LEFT.asArg()))
//                .addAction(new Pause(FINGER, ofMillis(2)))
//                .addAction(FINGER.createPointerUp(LEFT.asArg()));
//        androidDriver.perform(Arrays.asList(tap));
//        logger.info("Click performed successfully..");
//    }
//
//    public void sendEmailWithAttachment(String recipient, String subject, String body, String attachmentPath) {
//        // Set up the mail server properties
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        // Authenticate the session with your email credentials
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("seenivasan.a@testsigma.com", "sbbk rrer ihdx kgps");
//            }
//        });
//
//        try {
//            // Create a new email message
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("seenivasan.a@testsigma.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//            message.setSubject(subject);
//
//            // Create the message part (text)
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(body);
//
//            // Create a multipart message for attachment
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//
//            // Second part is the attachment
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(new File(attachmentPath));
//            multipart.addBodyPart(attachmentPart);
//
//            // Set the complete message parts
//            message.setContent(multipart);
//
//            // Send the email
//            Transport.send(message);
//
//            System.out.println("Email sent successfully.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void adjustForNotch() {
//        int notchHeight = getNotchHeight();
//        this.clickLocationY += notchHeight;
//    }
//
//    private int getNotchHeight() {
//        if (isNotchedDevice()) {
//            return 44;
//        }
//        return 0;
//    }
//
//    private boolean isNotchedDevice() {
//        String model = ((IOSDriver) this.driver).getCapabilities().getCapability("deviceModel").toString();
//        return model.contains("Pro");
//    }
//
//
}
