package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TransactionPartiesPage extends Page {

    private Logger LOG = Logger.getLogger(getClass());

    @FindBy(xpath = "//i[@class='fa fa-book']")
    private WebElement dictionaryTab;

    @FindBy(id = "prov_cus")
    private WebElement transactionPartiesTab;

    @FindBy(xpath = "//div[@class='box-tools']")
    private WebElement addButton;

    @FindBy(id = "prov_cus_proCustName")
    private WebElement customerNameField;

    @FindBy(id = "prov_cus_proCustAddress")
    private WebElement customerAddressField;

    @FindBy(id = "prov_cus_proCustPhone")
    private WebElement customerPhoneField;

    @FindBy(id = "prov_cus_proCustIsFl")
    private WebElement privatePersonCheckbox;

    @FindBy(name = "add")
    private WebElement createButton;

    @FindBy(xpath = "//table[@id='device_list']//tbody")
    private WebElement table;

    @FindBy(name = "delete")
    private WebElement deleteButton;

    @FindBy(name = "save")
    private WebElement saveButton;

    public TransactionPartiesPage(WebDriver webDriver){
        super(webDriver);
    }

    public void tableView(){
        clickButton(dictionaryTab);
        clickButton(transactionPartiesTab);
    }

    public int countTableRows(){
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.size();
    }

    public void deleteTableRecord(int index){
        clickButton(getEditIcon(index));
        clickButton(deleteButton);
        LOG.info("Record was deleted form TransactionParties table");
    }

    private By getEditIcon(int index){
        return By.xpath("//tr[" + (index-1) + "]//td[4]");
    }

    public void updateTableRecord(int index, String name, String address, String phone){
        clickButton(getEditIcon(index));
        fillField(customerNameField, name);
        fillField(customerAddressField, address);
        fillField(customerPhoneField, phone);
        clickButton(saveButton);
        LOG.info("Record was updated in TransactionParties table");
    }

    public String getTableRecord (int index){
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.get(index).getText();
    }

    public void addTableRecord(String name, String address, String phone){
        clickButton(addButton);
        fillField(customerNameField, name);
        fillField(customerAddressField, address);
        fillField(customerPhoneField, phone);
        clickButton(privatePersonCheckbox);
        clickButton(createButton);
        LOG.info("Record with name: " + name + ", address: " + address + " and phone: " + phone + " was added to TransactionParties table");
    }
}