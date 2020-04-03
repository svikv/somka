package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransactionsPage extends Page {

    private Wait<WebDriver> wait;
    private By transactionsTab = By.id("deal");
    private By addButton = By.xpath("//div[@class='box-tools']");
    private By dateSelector = By.id("deal_dealDate_date_day");
    private By monthSelector = By.id("deal_dealDate_date_month");
    private By yearSelector = By.id("deal_dealDate_date_year");
    private By typeSelector = By.id("deal_dealType");
    private By buyerSelector = By.id("deal_customer");
    private By supplierSelector = By.id("deal_provider");
    private By createButton = By.name("add");
//    private By table = By.xpath("//table[@id='device_list']//tbody//tr");
    private By table  = By.tagName("tr");
    private By saveButton = By.name("save");

    public TransactionsPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
        wait = new WebDriverWait(webDriver, 1);
    }

    public void tableView(){
        clickButton(transactionsTab);
    }

    public int countTableRaws(){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.size();
    }

    public void addTableRecord(String date, String month, String year, String type, String buyer, String supplier){
        clickButton(addButton);
        selectValueFromSelector(dateSelector, date);
        selectValueFromSelector(monthSelector, month);
        selectValueFromSelector(yearSelector, year);
        selectValueFromSelector(typeSelector, type);
        selectValueFromSelector(buyerSelector, buyer);
        selectValueFromSelector(supplierSelector, supplier);
        clickButton(createButton);
    }

    public String getTableRecord (int index){
        List<WebElement> rows = webDriver.findElements(table);
        return rows.get(index).getText();
    }

    private By getRow(int index){
        return By.xpath("//tr[" + index + "]");
    }

    public void updateTableRecord(int index, String newBuyer, String newSupplier){
        clickButton(getRow(index));
        selectValueFromSelector(buyerSelector, newBuyer);
        selectValueFromSelector(supplierSelector, newSupplier);
        clickButton(saveButton);
    }
}