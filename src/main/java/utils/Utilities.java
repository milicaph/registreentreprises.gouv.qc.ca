package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class Utilities {

    public static void clearLists(ArrayList<String> firstNames,
                                  ArrayList<String> lastNames,
                                  ArrayList<String> addresses) {
        firstNames.clear();
        lastNames.clear();
        addresses.clear();

    }
/*
    public static boolean checkpoints(int i){
        return i == 0 || i == 400 || i == 800 || i == 1200 || i == 1600;
    }
    public static boolean checkpointsQuit(int i){
        return i == 400 || i == 800 || i == 1200 || i == 1600;
    }
*/
    public static boolean checkIfElementExists(WebDriver driver, String cssSelector){
        return driver.findElements(By.cssSelector(cssSelector) ).size() != 0;
    }



}
