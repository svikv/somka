package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransactionPartiesPage extends Page {

    private Wait<WebDriver> wait;
    private Logger logger = Logger.getLogger(getClass());

    private By dictionaryTab = By.xpath("//i[@class='fa fa-book']");
    private By transactionPartiesTab = By.id("prov_cus");
    private By addButton = By.xpath("//div[@class='box-tools']");
    private By customerNameField = By.id("prov_cus_proCustName");
    private By customerAddressField = By.id("prov_cus_proCustAddress");
    private By customerPhoneField = By.id("prov_cus_proCustPhone");
    private By privatePersonCheckbox = By.id("prov_cus_proCustIsFl");
    private By createButton = By.name("add");
//    private By table = By.xpath("//table[@id='device_list']//tbody//tr");
    private By table  = By.tagName("tr");
    private By deleteButton = By.name("delete");
    private By saveButton = By.name("save");

    public TransactionPartiesPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
        wait = new WebDriverWait(webDriver, 1);
    }

    public void tableView(){
        clickButton(dictionaryTab);
        waitForVisibilityOfElement(wait, transactionPartiesTab);
        clickButton(transactionPartiesTab);
    }

    public int countTableRows(){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.size();
    }

    public void deleteTableRecord(int index){
        clickButton(getEditIcon(index));
        clickButton(deleteButton);
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
    }

    public String getTableRecord (int index){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.get(index).getText();
    }

    public void addTableRecord(String name, String address, String phone){
        clickButton(addButton);
        fillField(customerNameField, name);
        fillField(customerAddressField, address);
        fillField(customerPhoneField, phone);
        clickButton(privatePersonCheckbox);
        clickButton(createButton);
    }
}