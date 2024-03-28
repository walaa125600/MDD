package core;

import core.constants.mdd.GeneralConstants;
import core.util.Log;
import org.testng.Assert;

public class ExceptionHandler {

    public static void onExceptionRaised(Exception e , String className , String methodName) {

        Log.error("Error occurred While logging in " + className + "." + methodName, e);
        Assert.fail(GeneralConstants.POM_EXCEPTION_ERR_MSG, e);
    }
}
