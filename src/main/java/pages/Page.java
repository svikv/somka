package pages;

import libs.JsonReader;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.LoggerFactory;

public class Page {

    WebDriver webDriver;
    protected static final Logger LOG = Logger.getLogger(Page.class);
    private static JSONObject credentials = JsonReader.getJsonObject("credentials.json");
    private final static String  URL = "http://v3.test.itpmgroup.com";
    private static String authUser = (String) credentials.get("AUTH_USER");
    private static String authPass = (String) credentials.get("AUTH_PASSWORD");

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static Map<String, String> getAuthUser(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("authUser", authUser);
        map.put("authPass", authPass);
        return map;
    }

    public static String getPageUrl() {
        return URL;
    }

    public void fillField(By element, String text) {
        try {
            webDriver.findElement(element).clear();
            webDriver.findElement(element).sendKeys(text);
            LOG.info("");
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("");
        }
    }

    public void clickButton(By element){
        try {
            webDriver.findElement(element).click();
            LOG.info("");
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("");
        }
    }

    public boolean isElementDisplayed(By element){
        try {
            return webDriver.findElement(element).isDisplayed();
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("");
            return false;
        }
    }

    public void checkBox(By element) {
        WebElement checkBox = webDriver.findElement(element);

        try {
            if(!checkBox.isSelected()){
                checkBox.click();
            }
        } catch (Exception e){
            e.printStackTrace();
            LOG.error("");
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
            LOG.error("");
        }
    }

    public void selectValueFromSelector (By element, String text) {
        Select dropDownValue = new Select(webDriver.findElement(element));
        try {
            dropDownValue.selectByVisibleText(text);
        } catch(Exception e){
            e.printStackTrace();
            LOG.error("");
        }
    }

    public void selectValueFromSelector (By element, int index) {
        Select dropDownValue = new Select(webDriver.findElement(element));
        try {
            dropDownValue.selectByIndex(index);
        } catch(Exception e){
            e.printStackTrace();
            LOG.error("");
        }
    }

    public void waitForVisibilityOfElement(Wait<WebDriver> wait, By locatorType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorType));
    }
}