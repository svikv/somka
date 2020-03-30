package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;

public class LoginPageTest {

    private WebDriver webDriver = driverInit();
    private static String login = "Student";
    private String password = "909090";
    By userMenuName = By.xpath(".//div[@class='pull-left info']");

//    @FindBy(name = "_username")
//    private WebElement userNameField;

    private WebDriver driverInit() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    LoginPage loginPage = new LoginPage(webDriver);
//    PageFactory.initElements(webDriver, loginPage);

    @Test
    public void loginToPage() {
        loginPage.loginToPage(login, password);
        Assert.assertTrue(webDriver.findElement(userMenuName).isDisplayed());
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
