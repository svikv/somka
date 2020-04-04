package loginTests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import parentTests.AbstractParentTest;

public class TransactionsPageTest extends AbstractParentTest {

    private String date = "05";
    private String month = "марта";
    private String year = "2020";
    private String type = "Obmen";
    private String buyer = "Sergey";
    private String supplier = "EuroTruckService";

    @Ignore
    @Test
    public void addRecord() {
        loginPage.loginToPage(user, password);
        checkExpectedResult("Avatar is displayed", homePage.isAvatarDisplayed());

        transactions.tableView();
        int rawsBefore = transactions.countTableRows();
        transactions.addTableRecord(date, month, year, type, buyer, supplier);
        int rawsAfter = transactions.countTableRows();
        Assert.assertEquals(rawsBefore + 1, rawsAfter);

        String actualRecord = transactions.getTableRecord(rawsAfter - 1);
        String expectedRecord = type + " " + buyer + " " + supplier;
        Assert.assertTrue("Record wasn't added to table", actualRecord.contains(expectedRecord));
    }

    @Test
    public void updateRecord() {
        loginPage.loginToPage(user, password);
        checkExpectedResult("Avatar is displayed", homePage.isAvatarDisplayed());

        transactions.tableView();
        int rawsBefore = transactions.countTableRows();
        transactions.addTableRecord(date, month, year, type, buyer, supplier);
        int rawsAfter = transactions.countTableRows();
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
}