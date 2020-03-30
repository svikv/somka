package pages;

import libs.ActionWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {

    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected ActionWithWebElements actionWithWebElements;
    private final static String  URL = "http://v3.test.itpmgroup.com";

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

    public void loginToPage(String login, String password){
        webDriver.get(URL);
        userNameField.sendKeys(login);
        userNamePassword.sendKeys(password);
        submitButton.click();
    }
}
