package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TransactionPartiesPage;

public class TransactionPartiesPageTest {

    private WebDriver webDriver = driverInit();
    private By userMenuName = By.xpath(".//div[@class='pull-left info']");
    private String login = "Student";
    private String password = "909090";
    private String name = "Viktor";
    private String address = "Kiev";
    private String phone = "0502501256";

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

        transactionParties.goToTable();
        int rawsBefore = transactionParties.countTableRaws();
        transactionParties.addTableRecord(name, address, phone);
        int rawsAfter = transactionParties.countTableRaws();
        Assert.assertEquals("New record wasn't added to table", rawsBefore + 1, rawsAfter);
    }

    @Test
    public void deleteRecord() {
        loginPage.loginToPage(login, password);
        Assert.assertTrue(webDriver.findElement(userMenuName).isDisplayed());

        transactionParties.goToTable();
        int rawsBefore = transactionParties.countTableRaws();
        transactionParties.addTableRecord(name, address, phone);
        int rawsAfterAdding = transactionParties.countTableRaws();
        Assert.assertEquals("Record wasn't added to table", rawsBefore + 1, rawsAfterAdding);

        transactionParties.deleteTableRecord(rawsAfterAdding);
        int rawsAfterDeleting = transactionParties.countTableRaws();
        Assert.assertEquals("Record wasn't deleted from table", rawsAfterAdding - 1, rawsAfterDeleting);
    }

    @Test
    public void updateRecord() {
        loginPage.loginToPage(login, password);
        Assert.assertTrue(webDriver.findElement(userMenuName).isDisplayed());

        transactionParties.goToTable();
        int rawsBefore = transactionParties.countTableRaws();
        transactionParties.addTableRecord(name, address, phone);
        int rawsAfterAdding = transactionParties.countTableRaws();
        Assert.assertEquals("New record wasn't added to table", rawsBefore + 1, rawsAfterAdding);

        transactionParties.updateTableRecord(rawsAfterAdding, "Viktoria", "Minsk", "0505001020");
        String record = transactionParties.getRecord(rawsAfterAdding - 1);
        Assert.assertTrue(record.contains(name));
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}