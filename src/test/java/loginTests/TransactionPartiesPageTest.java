package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TransactionPartiesPage;

public class TransactionPartiesPageTest {

    private WebDriver webDriver = driverInit();
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
        loginPage.loginToPage();
        Assert.assertTrue(loginPage.isUserMenuNameDisplayed());

        transactionParties.tableView();
        int rawsBefore = transactionParties.countTableRows();
        transactionParties.addTableRecord(name, address, phone);
        int rawsAfter = transactionParties.countTableRows();
        Assert.assertEquals(rawsBefore + 1, rawsAfter);

        String actualRecord = transactionParties.getTableRecord(rawsAfter - 1);
        String expectedRecord = name + " " + address + " " + phone;
        Assert.assertTrue("Table record wasn't updated", actualRecord.contains(expectedRecord));
    }

    @Test
    public void deleteRecord() {
        loginPage.loginToPage();
        Assert.assertTrue(loginPage.isUserMenuNameDisplayed());

        transactionParties.tableView();
        int rawsBefore = transactionParties.countTableRows();
        transactionParties.addTableRecord(name, address, phone);
        int rawsAfterAdding = transactionParties.countTableRows();
        Assert.assertEquals("Record wasn't added to table", rawsBefore + 1, rawsAfterAdding);

        transactionParties.deleteTableRecord(rawsAfterAdding);
        int rawsAfterDeleting = transactionParties.countTableRows();
        Assert.assertEquals("Record wasn't deleted from table", rawsAfterAdding - 1, rawsAfterDeleting);
    }

    @Test
    public void updateRecord() {
        loginPage.loginToPage();
        Assert.assertTrue(loginPage.isUserMenuNameDisplayed());

        transactionParties.tableView();
        int rawsBefore = transactionParties.countTableRows();
        transactionParties.addTableRecord(name, address, phone);
        int rawsAfterAdding = transactionParties.countTableRows();
        Assert.assertEquals("Record wasn't added to table", rawsBefore + 1, rawsAfterAdding);

        String newName = "Oleg";
        String newAddress = "Minsk";
        String newPhone = "0505001020";

        transactionParties.updateTableRecord(rawsAfterAdding, newName, newAddress, newPhone);
        String actualRecord = transactionParties.getTableRecord(rawsAfterAdding - 1);
        String expectedRecord = newName + " " + newAddress + " " + newPhone;
        Assert.assertTrue("Table record wasn't updated", actualRecord.contains(expectedRecord));
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}