package loginTests;

import org.junit.Assert;
import org.junit.Test;
import parentTests.AbstractParentTest;

public class TransactionPartiesPageTest extends AbstractParentTest {

    private String name = "Viktor";
    private String address = "Kiev";
    private String phone = "0502501256";

    @Test
    public void addRecord() {
        loginPage.loginToPage(user, password);
        checkExpectedResult("Avatar is displayed", homePage.isAvatarDisplayed());

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
        loginPage.loginToPage(user, password);
        checkExpectedResult("Avatar is displayed", homePage.isAvatarDisplayed());

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
        loginPage.loginToPage(user, password);
        checkExpectedResult("Avatar is displayed", homePage.isAvatarDisplayed());

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
}