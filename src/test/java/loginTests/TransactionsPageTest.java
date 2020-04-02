package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TransactionsPage;

public class TransactionsPageTest {

    private WebDriver webDriver = driverInit();
    private String date = "05";
    private String month = "марта";
    private String year = "2020";
    private String type = "Obmen";
    private String buyer = "Sergey";
    private String supplier = "EuroTruckService";

    private WebDriver driverInit() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    private LoginPage loginPage = new LoginPage(webDriver);
    private TransactionsPage transactions = new TransactionsPage(webDriver);

    @Test
    public void addRecord() {
        loginPage.loginToPage();
        Assert.assertTrue(loginPage.isUserMenuNameDisplayed());

        transactions.tableView();
        int rawsBefore = transactions.countTableRaws();
        transactions.addTableRecord(date, month, year, type, buyer, supplier);
        int rawsAfter = transactions.countTableRaws();
        Assert.assertEquals(rawsBefore + 1, rawsAfter);

        String actualRecord = transactions.getTableRecord(rawsAfter - 1);
        String expectedRecord = type + " " + buyer + " " + supplier;
        Assert.assertTrue("Record wasn't added to table", actualRecord.contains(expectedRecord));
    }

    @Test
    public void updateRecord() {
        loginPage.loginToPage();
        Assert.assertTrue(loginPage.isUserMenuNameDisplayed());

        transactions.tableView();
        int rawsBefore = transactions.countTableRaws();
        transactions.addTableRecord(date, month, year, type, buyer, supplier);
        int rawsAfter = transactions.countTableRaws();
        Assert.assertEquals(rawsBefore + 1, rawsAfter);

        String actualRecord = transactions.getTableRecord(rawsAfter - 1);
        String expectedRecord = type + " " + buyer + " " + supplier;
        Assert.assertTrue("Record wasn't added to table", actualRecord.contains(expectedRecord));

        String newBuyer = "QATestLab";
        String newSupplier = "Sergey";

        transactions.updateTableRecord(rawsAfter, newBuyer, newSupplier);
        actualRecord = transactions.getTableRecord(rawsAfter - 1);
        expectedRecord = newBuyer + " " + newSupplier;
        Assert.assertTrue("Table record wasn't updated", actualRecord.contains(expectedRecord));
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}