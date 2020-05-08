package com.aking.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Scanner;

public class Delete {

    private static String USERNAME;
    private static String PASSWORD;

    public static void delete(String fileName) {

        // Sets USERNAME and PASSWORD to Strings provided in the UserCredentials.txt file
        // in the parent directory
        try {
            Scanner fileScanner = new Scanner(new File("UserCredentials.txt"));
            USERNAME = fileScanner.nextLine();
            PASSWORD = fileScanner.nextLine();
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("UserCredentials.txt should contain GitHub user name and password.");
        }


        // Link to Chrome Driver
        // This gives Selenium access to the Chrome Driver in the parent directory
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Launches GitHub.com/login
        final WebDriver driver = new ChromeDriver();
        driver.get("http://github.com/login");

        // Maximizes Google Chrome window
        driver.manage().window().maximize();

        // Locates the login elements on GitHub.com/login and types the user provided information and then submits it
        final WebElement loginLink = driver.findElement(By.xpath("/html/body/div[3]/main/div/form/div[4]/input[1]"));
        loginLink.sendKeys(USERNAME);
        final WebElement passwordLink = driver.findElement(By.xpath("/html/body/div[3]/main/div/form/div[4]/input[2]"));
        passwordLink.sendKeys(PASSWORD);
        final WebElement submitButton = driver.findElement(By.xpath("/html/body/div[3]/main/div/form/div[4]/input[9]"));
        submitButton.click();

        // Launch Repository Settings Page
        String settingsURL = "https://github.com/" + USERNAME + "/" + fileName + "/settings";
        driver.get(settingsURL);

        // Locate "Delete this repository" option and delete repository
        final WebElement deleteRepo = driver.findElement(By.xpath("/html/body/div[4]/div/main/div[2]/div/div/div[2]/div/div[7]/ul/li[4]/details/summary"));
        deleteRepo.click();
        final WebElement repoName = driver.findElement(By.xpath("/html/body/div[4]/div/main/div[2]/div/div/div[2]/div/div[7]/ul/li[4]/details/details-dialog/div[3]/form/p/input"));
        String repoNameSequence = USERNAME.toLowerCase() + "/" + fileName;
        repoName.sendKeys(repoNameSequence);
        final WebElement deleteSubmit = driver.findElement(By.xpath("/html/body/div[4]/div/main/div[2]/div/div/div[2]/div/div[7]/ul/li[4]/details/details-dialog/div[3]/form/button"));
        deleteSubmit.click();

        //Close Google Chrome
        driver.close();
    }
}
