package pages;

import libs.ActionWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected ActionWithWebElements actionWithWebElements;
    String url = "http://v3.test.itpmgroup.com";
//    By inputLoginName = By.name("_username");
//    By inputPasswordName = By.name("_password");
//    By inputButtonXpath = By.xpath(".//button[@type='submit']");

    @FindBy(name = "_username")
    private WebElement userNameField;

    @FindBy(name = "_password")
    private WebElement userNamePassword;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actionWithWebElements = new ActionWithWebElements(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public void openPage(){
        webDriver.get(url);
    }

//    public void inputLogin(String text){
//        actionWithWebElements.fillField(inputLoginName, text);
//    }

    public void inputLogin(String text){
        userNameField.sendKeys(text);
    }

//    public void inputPassword(String text){
//        actionWithWebElements.fillField(inputPasswordName, text);
//    }

    public void inputPassword(String text){
        userNamePassword.sendKeys(text);
    }

//    public void clickSubmitButton(){
//        actionWithWebElements.clickButton(inputButtonXpath);
//    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void loginToPage(String login, String password){
        openPage();
        inputLogin(login);
        inputPassword(password);
        clickSubmitButton();
    }
}
