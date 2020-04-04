package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class LoginPage extends Page {

    @FindBy(name = "_username")
    private WebElement inputLoginName;

    @FindBy(name = "_password")
    private WebElement inputPasswordName;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//div[@class='login-box-body']")
    private WebElement loginBox;

    public LoginPage(WebDriver webDriver){
        super(webDriver);
    }

    public void loginToPage(String user, String password){
        webDriver.navigate().to(Page.getPageUrl());
        inputLoginName.sendKeys(user);
        inputPasswordName.sendKeys(password);
        submitButton.click();
    }

    public boolean isLoginBoxDisplayed () {
        return isElementDisplayed(loginBox);
    }

}