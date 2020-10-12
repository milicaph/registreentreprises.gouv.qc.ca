package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends HomePage{
    private final WebDriver driver;
    private final WebDriverWait wait;

    private
    @FindBy(css = "input[title=Nom]")
    WebElement searchInput;
    private
    @FindBy(css = "input[type=checkbox]")
    WebElement checkbox;
    private
    @FindBy(css = "input[type=submit]")
    WebElement submitButton;
    private
    @FindBy(css = "table[id*=IdSectionResultat")
    WebElement resultsTable;
    private
    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    WebElement resultAddress;
    private
    @FindBy(xpath = "//tbody/tr[1]/td[4]")
    WebElement resultStatut;
    private
    @FindBy(css = "div.erreurfonctionnelle")
    WebElement errorSearch;



    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    private void setStatutAddress(ArrayList<String> statut, ArrayList<String> address){
        statut.add(resultStatut.getText());
        address.add(resultAddress.getText());
    }


    public void inputEnterpriseName(String enterpriseName, String sessionURL, ArrayList<String> statut, ArrayList<String> address, int i){

        /*if(Utilities.checkpoints(i)){
            sessionURL = getSearchURL();
        }*/
        driver.get(sessionURL);
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchInput.sendKeys(enterpriseName);
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if((i == 0))
            checkbox.click();
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

        try{ Thread.sleep(2000);} catch (Exception ignored){}
        if(!Utilities.checkIfElementExists(driver, "div.erreurfonctionnelle")) {
            wait.until(ExpectedConditions.visibilityOf(resultsTable));
            List<WebElement> links = driver.findElements(By.cssSelector("td > a"));

            setStatutAddress(statut, address);

            System.out.print(statut);
            System.out.print(address);


            links.get(0).click();

        }
    }








}
