package com.aking.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Create {

    private static String USERNAME;
    private static String PASSWORD;

    public static void create(String fileName) {

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

        // Locates the necessary buttons to navigate to creating a new repository using the WebElement's Xpath
        final WebElement startProject = driver.findElement(By.xpath("/html/body/div[1]/header/div[6]/details/summary"));
        startProject.click();
        final WebElement newRepo = driver
                .findElement(By.xpath("/html/body/div[1]/header/div[6]/details/details-menu/a[1]"));
        newRepo.click();

        // Types in the user desired repository name
        final WebElement repoName = driver
                .findElement(By.xpath("/html/body/div[4]/main/div/form/div[2]/auto-check/dl/dd/input"));
        repoName.sendKeys(fileName);

        // Makes the new repository "Private"
        final WebElement privateToggle = driver
                .findElement(By.xpath("/html/body/div[4]/main/div/form/div[3]/div[2]/label/input"));
        privateToggle.click();

        // Includes a README.md file
        final WebElement includeREADME = driver
                .findElement(By.xpath("/html/body/div[4]/main/div/form/div[3]/div[4]/div[1]/label/input[2]"));
        includeREADME.click();

        // Wait for Repo info to be typed
        // Otherwise the repo would not be created properly because Selenium executes commands quickly
        try {
            Thread.sleep(500);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // Create Repository
        final WebElement createRepo = driver.findElement(By.xpath("/html/body/div[4]/main/div/form/div[3]/button"));
        createRepo.click();

        // Close Driver
        driver.close();

        // Create a batch file that navigates to the parent directory of the current drive and creates a folder
        // with the same name as the repository and navigates into it.
        // GitHub commands are used to link the local folder with the GitHub repository
        // "git pull orign master" copies all files from the repository to the local folder
        // "code ." Opens Visual Studio Code in the project directory
        try {
            final PrintWriter pWriter = new PrintWriter(new File("CmdCommands.bat"));
            pWriter.write("@echo off\n");
            pWriter.write("cd /\n");
            pWriter.write("mkdir " + fileName + "\n");
            pWriter.write("cd " + fileName + "\n");
            pWriter.write("git init\n");
            pWriter.write("git remote add origin https://github.com/" + USERNAME + "/" + fileName + ".git\n");
            pWriter.write("git pull origin master\n");
            pWriter.write("code .\n");
            pWriter.flush();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // Creates a List of commands to start Cmd and execute CmdCommands.bat
        final List<String> cmdAndArgs = new ArrayList<String>();
        cmdAndArgs.add("cmd.exe");
        cmdAndArgs.add("/C");
        cmdAndArgs.add("Start");
        cmdAndArgs.add("CmdCommands.bat");

        // ProcessBuilder with commands inserted
        final ProcessBuilder builder = new ProcessBuilder(cmdAndArgs);

        // Tells the builder the current working directory and starts running the commands
        try {
            final File dir = new File(System.getProperty("user.dir"));
            builder.directory(dir);
            final Process p = builder.start();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
