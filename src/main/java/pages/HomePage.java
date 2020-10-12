package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private
    @FindBy(css = "div#rechreg > a")
    WebElement findEnterpriseButton;

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);

    }

    private void openHomePage(){
        driver.get("http://www.registreentreprises.gouv.qc.ca/en/");
        wait.until(ExpectedConditions.elementToBeClickable(findEnterpriseButton));
    }

    private boolean windowsNum(){
        return driver.getWindowHandles().size() > 1;
    }

    public String getSearchURL(){
        openHomePage();
        findEnterpriseButton.click();

        System.out.println(driver.getWindowHandles());
        while(!windowsNum()){
            System.out.print("waiting...");
        }

        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));
        wait.until(ExpectedConditions.urlContains("RQAnonymeGR"));
        System.out.println(driver.getCurrentUrl());
        String sessionUrl = driver.getCurrentUrl();
        driver.close();

        driver.switchTo().window("18");
        //driver.get(sessionUrl);

        return sessionUrl;




    }



}
