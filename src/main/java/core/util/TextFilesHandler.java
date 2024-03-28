package core.util;

import core.constants.mdd.GeneralConstants;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class TextFilesHandler {

    public static String getTextFileContentAsString(String textFilePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(textFilePath);
        String fileContent = IOUtils.toString(inputStream);
        return fileContent;
    }

    public static void createFile(String fileName) throws IOException {

        File file = new File(fileName);
        if (file.createNewFile()) {
            Log.info("File created: " + file.getName());
        } else {
            Log.info("File already exists.");
        }
    }

    public static void writeToFile(String filePath ,String fileContent) throws IOException {
        FileWriter myWriter = new FileWriter(filePath);
        myWriter.write(fileContent);
        myWriter.close();
        Log.info("Successfully wrote to the file.");
    }

    public static boolean exist(WebDriver driver ,String fileName ) {
        String  browserDefaultDownloadPath = System.getProperty("user.dir")
                + Property.fromFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME).getProperty(GeneralConstants.DEFAULT_DOWNLOAD_PATH);
        String filePath = browserDefaultDownloadPath+fileName;
        Log.info(String.format("Check if File with Path = %s is exist.",filePath));
        File f = new File(filePath);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(25)).pollingEvery(Duration.ofMillis(100));
        wait.until( x -> f.exists());
        if (f.exists() && !f.isDirectory()) {
            Log.info(String.format("File with name = %s downloaded successfully" , fileName));
            return true;
        }
        return false;
    }

    public static String readPDF(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }
}
