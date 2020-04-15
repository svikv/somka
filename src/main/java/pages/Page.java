package pages;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import utilities.HttpCaller;
import utilities.JsonReader;

import java.util.HashMap;
import java.util.Map;

public class Page {

    protected WebDriver webDriver;
    private Logger LOG = Logger.getLogger(getClass());
    private static JSONObject credentials = JsonReader.getJsonObject("credentials.json");
    private static String URL = (String) credentials.get("BASE_URL");
    private static String authUser = (String) credentials.get("AUTH_USER");
    private static String authPass = (String) credentials.get("AUTH_PASSWORD");

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
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

    public void fillField(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickButton(WebElement element){
        try {
            element.click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickButton(By element){
        try {
            webDriver.findElement(element).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean isElementDisplayed(WebElement element){
        try {
            return element.isDisplayed();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void checkBox(WebElement element) {

        try {
            if(!element.isSelected()){
                element.click();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void checkRadio(WebElement element) {

        try {
            if(!element.isSelected()){
                element.click();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectValueFromSelector (WebElement element, String text) {
        Select dropDownValue = new Select(element);
        try {
            dropDownValue.selectByVisibleText(text);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitForVisibilityOfWebElement(Wait<WebDriver> wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfElement(Wait<WebDriver> wait, By locatorType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorType));
    }

    public static String truncateStringByCharacters(String string, int max) {
        return string.length() > 1028 ? string.substring(0, max) : string;
    }

    public static void serverHealthCheck() {
        HttpCaller httpCaller = new HttpCaller();
        httpCaller.get(Page.getPageUrl());
        Assert.assertEquals("Server is not returning success load code 200!", 200, httpCaller.getResponseCode());
    }
}