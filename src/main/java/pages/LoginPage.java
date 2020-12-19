package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    private Logger LOG = Logger.getLogger(getClass());

    @CacheLookup
    @FindBy(name = "_username")
    private WebElement inputLoginName;

    @CacheLookup
    @FindBy(name = "_password")
    private WebElement inputPasswordName;

    @CacheLookup
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    @CacheLookup
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
        LOG.info("User " + "with login: --" + Page.getAuthUser().get("authUser") + "-- logged in!");
    }

    public boolean isLoginBoxDisplayed () {
        return isElementDisplayed(loginBox);
    }

}