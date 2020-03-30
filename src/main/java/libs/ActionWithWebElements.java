package libs;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ActionWithWebElements {
//    private static LOGGER = LoggerFactory.getLogger(ActionWithWebElements.class);

    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public ActionWithWebElements(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillField(By element, String text) {
        try {
            webDriver.findElement(element).clear();
            webDriver.findElement(element).sendKeys(text);
            logger.info("");
        } catch (Exception e){
            e.printStackTrace();
            logger.error("");
        }
    }

    public void clickButton(By element){
        try {
            webDriver.findElement(element).click();
            logger.info("");
        } catch (Exception e){
            e.printStackTrace();
            logger.error("");
        }
    }

    public boolean isElementDisplayed(By element){
        try {
            return webDriver.findElement(element).isDisplayed();
        } catch (Exception e){
            e.printStackTrace();
            logger.error("");
            return false;
        }
    }

//    public void setCheckBox(By element, boolean state){
//        WebElement checkBox = webDriver.findElement(element);
//        try {
//            if(checkBox.isSelected() == state) {
//                logger.info("");
//            }else {
//                checkBox.click();
//            }
//            logger.info("");
//
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }

    public void checkBox(By element) {
        WebElement checkBox = webDriver.findElement(element);

        try {
            if(!checkBox.isSelected()){
                checkBox.click();
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("");
        }
    }

    public void checkRadio(By element) {
        WebElement radio = webDriver.findElement(element);

        try {
            if(!radio.isSelected()){
                radio.click();
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("");
        }
    }

    private void selectValueFromSelector (By element, String text) {
        Select dropDownValue = new Select(webDriver.findElement(element));
        try{
            dropDownValue.selectByVisibleText(text);
        } catch(Exception e){
            e.printStackTrace();
            logger.error("");
        }
    }

    private void selectValueFromSelector (By element, int index) {
        Select dropDownValue = new Select(webDriver.findElement(element));
        try{
            dropDownValue.selectByIndex(index);
        } catch(Exception e){
            e.printStackTrace();
            logger.error("");
        }
    }


}
