package pages;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.function.Exp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class EnterprisePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private
    @FindBy(css = "div > span > span")
    WebElement allData;
    private
    @FindBy(id = "K1Bandeau1_Cadr_ctl00_btnQuitter")
    WebElement quitSession;
    private
    @FindBy(id = "ConfirmationFermerSession_ctl00_btnNon")
    WebElement confirmQuit;

    public EnterprisePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    private String htmlToParse(){
        String html = allData.getAttribute("innerHTML");
        String neededHTML = StringUtils.substringBetween(html, "Liste des administrateurs",
                                                    "Dirigeants non membres du conseil d'administration");
        //System.out.println(neededHTML);
        return neededHTML;
    }

    private Document htmlDoc(){
        return Jsoup.parse(htmlToParse());
    }

    public void quitSession(){
        quitSession.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmQuit));
        confirmQuit.click();
        quitSession.click();

    }

    public void setAdministratorValues(ArrayList<String> firstNames, ArrayList<String> lastNames,
                                       ArrayList<String> addresses){
        Document doc = htmlDoc();
        Elements fieldsets = doc.select("fieldset");
        /*ArrayList<String> lastNames = new ArrayList<>();
        ArrayList<String> firstNames = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();*/

        int i = 0;
        for(Element fieldset : fieldsets){
            Document subdoc = Jsoup.parse(fieldset.html());
            Elements labels = subdoc.select("label");
            Elements values = subdoc.select("p");


            if(i < 3){
                System.out.println(i);
                String labelName1 = "";
                try {
                    labelName1 = labels.get(0).text();
                } catch(Exception ignored) {}

                String labelName2 = "";
                String labelAddress = "";
                try {
                    labelAddress = labels.get(labels.size()-1).text();
                } catch(Exception ignored) {}
                String valueName1 = "";
                try {
                    valueName1 = values.get(0).text();
                    //System.out.println(valueName1);
                } catch(Exception ignored) {}
                String valueName2 = "";
                String valueAddress = "";
                try {
                    valueAddress = values.get(values.size()-1).text();
                } catch(Exception ignored) {}

                if(labelName1.contains("famille")){
                    try {
                        labelName2 = labels.get(1).text();
                    } catch(Exception ignored) {}
                    try {
                        valueName2 = values.get(1).text();
                    } catch(Exception ignored) {}
                } else{
                    try {
                        valueName2 = valueName1.substring(valueName1.indexOf(",") + 1).trim();
                    } catch(Exception ignored) {}
                    try {

                        valueName1 = valueName1.substring(0, valueName1.indexOf(","));
                    } catch(Exception ignored) {}
                }

                lastNames.add(valueName1);
                System.out.println(valueName1);
                System.out.println(lastNames.size());
                firstNames.add(valueName2);
                System.out.println(valueName2);
                System.out.println(firstNames.size());
                addresses.add(valueAddress);
                System.out.println(valueAddress);
                System.out.println(addresses.size());


            }

            i++;
        }

    }






}
