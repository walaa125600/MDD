package eastnets.mdd.control;

import core.util.Log;
import eastnets.mdd.gui.detectionManager.DetectionEditor;
import eastnets.mdd.gui.detectionManager.DuplicationManager;
import org.openqa.selenium.WebDriver;

public class DetectionEditorControl {

    public static String readDetectionStatus(WebDriver driver) {
        String detectionStatus = DetectionEditor.getDetectionStatus(driver);
        Log.info("Detection status: "+ detectionStatus);
        return detectionStatus;
    }
    public static String clickAccept(WebDriver driver) throws Exception {
        Log.info("click accept button");
        DetectionEditor.clickAccept(driver);
        Log.info("click dialog accept button");
        DetectionEditor.clickDialogAccept(driver);
        String alertMessage = DetectionEditor.getAlertMessage(driver);
        Log.info("alert message: "+ alertMessage);
        return alertMessage;
    }
}
