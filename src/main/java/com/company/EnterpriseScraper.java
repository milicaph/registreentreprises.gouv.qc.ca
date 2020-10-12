package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EnterprisePage;
import pages.HomePage;
import pages.SearchPage;
import parameters.ReadWriteExcel;
import settings.InitDriver;
import utils.Utilities;

import java.io.IOException;
import java.util.ArrayList;

public class EnterpriseScraper {

    public static void main(String[] args) throws IOException {
        InitDriver initiateDriver = new InitDriver();

        WebDriver driver = initiateDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();

        ArrayList<String> enterpriseList = ReadWriteExcel.getEnterpriseList();
        HomePage homePage = new HomePage(driver, wait);
        String sessionURL = homePage.getSearchURL();
        //String sessionURL = homePage.getSearchURL();
        SearchPage searchPage = new SearchPage(driver, wait);
        EnterprisePage enterprisePage = new EnterprisePage(driver, wait);
        String enterpriseName = "";
        int i = 0;
        for(String enterprise : enterpriseList) {
            enterpriseName = enterprise;
            if(enterpriseName.contains("/"))
                enterpriseName = enterpriseName.substring(0, enterpriseName.indexOf("/"));

            ArrayList<String> statut = new ArrayList<>();
            ArrayList<String> addressINC = new ArrayList<>();
            try {
                searchPage.inputEnterpriseName(enterprise, sessionURL, statut, addressINC, i);
            } catch (Exception ignored) {}

            ArrayList<String> lastNames = new ArrayList<>();
            ArrayList<String> firstNames = new ArrayList<>();
            ArrayList<String> addresses = new ArrayList<>();

            try {
                enterprisePage.setAdministratorValues(lastNames, firstNames, addresses);
            } catch (Exception ignored){}

            String statString = "";
            String addString = "";
            try {
                statString = statut.get(0);
            } catch (IndexOutOfBoundsException ignored){}
            try {
                addString = addressINC.get(0);
            } catch (IndexOutOfBoundsException ignored){}

            ReadWriteExcel.writeExcel(enterpriseName, statString, addString, lastNames, firstNames, addresses);
            Utilities.clearLists(lastNames, firstNames, addresses);

            System.out.println(">>>>>>>>>>>>" + i + "INC NAME: " + enterprise + "<<<<<<<<<<<<<");
            try{ Thread.sleep(5000); } catch (Exception ignored) {}
            i++;
        }
        try {
            enterprisePage.quitSession();
        } catch(Exception ignored) {}

        driver.quit();

    }
}
