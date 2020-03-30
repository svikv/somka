package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TransactionPartiesPage;

public class TransactionPartiesPageTest {

    private WebDriver webDriver = driverInit();
    private String login = "Student";
    private String password = "909090";
    private By userMenuName = By.xpath(".//div[@class='pull-left info']");

    private WebDriver driverInit() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    private LoginPage loginPage = new LoginPage(webDriver);
    private TransactionPartiesPage transactionParties = new TransactionPartiesPage(webDriver);

    @Test
    public void addRecord() {
        loginPage.loginToPage(login, password);
        Assert.assertTrue(webDriver.findElement(userMenuName).isDisplayed());

        int rawsBefore = transactionParties.countTableRawsBefore();
        transactionParties.addRecordToTable();
        int rawsAfter = transactionParties.countTableRawsAfter();
        Assert.assertEquals("New record wasn't added to table", rawsBefore + 1, rawsAfter);
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}