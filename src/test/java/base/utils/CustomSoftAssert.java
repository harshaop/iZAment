package base.utils;

import base.WebDriverSession;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomSoftAssert extends SoftAssert {

    @Override
    public void onAssertFailure(IAssert a, AssertionError ex) {
        takeScreenshot(ex.getMessage());
    }

    private void takeScreenshot(String ex) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        File scrFile = ((TakesScreenshot) WebDriverSession.webDriver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
            File destFile = new File(reportDirectory + "/failure_screenshots/"
                    + "message" + ex + "_" +
                    formatter.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

