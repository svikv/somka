//package libs;
//
//import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Wait;
//
//public class ActionWithWebElements {
//
//    WebDriver webDriver;
//    Logger logger = Logger.getLogger(getClass());
//
//    public ActionWithWebElements(WebDriver webDriver) {
//        this.webDriver = webDriver;
//    }
//
//    public void fillField(WebElement element, String text) {
//        try {
//            element.clear();
//            element.sendKeys(text);
//            logger.info("");
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }
//
//    public void clickButton(WebElement element){
//        try {
//            element.click();
//            logger.info("");
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }
//
//    public boolean isElementDisplayed(WebElement element){
//        try {
//            return element.isDisplayed();
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("");
//            return false;
//        }
//    }
//
//    public void checkBox(WebElement element) {
//
//        try {
//            if(!element.isSelected()){
//                element.click();
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }
//
//    public void checkRadio(WebElement element) {
//
//        try {
//            if(!element.isSelected()){
//                element.click();
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }
//
//    public void selectValueFromSelector (WebElement element, String text) {
//        Select dropDownValue = new Select(element);
//        try {
//            dropDownValue.selectByVisibleText(text);
//        } catch(Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }
//
//    public void selectValueFromSelector (WebElement element, int index) {
//        Select dropDownValue = new Select(element);
//        try {
//            dropDownValue.selectByIndex(index);
//        } catch(Exception e){
//            e.printStackTrace();
//            logger.error("");
//        }
//    }
//
//    public void waitForVisibilityOfElement(Wait<WebDriver> wait, By locatorType) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorType));
//    }
//
//}
