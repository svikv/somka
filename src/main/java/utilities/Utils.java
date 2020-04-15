package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private Logger LOG = Logger.getLogger(getClass());

    /**
     * Taking screenshot into .//target// + pathToScreenShot
     * @param pathToScreenShot
     * @param driver
     */
    public void captureScreenshot(String pathToScreenShot, WebDriver driver){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(pathToScreenShot));
            LOG.info("ScreenShot taken to: " + pathToScreenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hard wait
     * @param second
     */
    public static void waitABit(int second){
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     * @return
     */
    public static String getDateAndTimeFormated(){

        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    /**
     * Method returned SystemDateAndTime
     * @return
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String dateFormated = dateFormat.format(date);
        System.out.println(dateFormated);
        return dateFormated;
    }

}
