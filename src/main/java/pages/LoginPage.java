package pages;

import libs.ActionWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage extends Page {

    private String user = Page.getAuthUser().get("authUser");
    private String password = Page.getAuthUser().get("authPass");
    protected Logger logger = Logger.getLogger(getClass());

    @FindBy(name = "_username")
    private WebElement userNameField;

    @FindBy(name = "_password")
    private WebElement userPasswordField;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//div[@class='pull-left info']")
    private WebElement userMenuName;

    public LoginPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public void loginToPage(){
        webDriver.navigate().to(Page.getPageUrl());
        userNameField.sendKeys(user);
        userPasswordField.sendKeys(password);
        submitButton.click();
    }

    public boolean isUserMenuNameDisplayed(){
        return userMenuName.isDisplayed();
    }
}