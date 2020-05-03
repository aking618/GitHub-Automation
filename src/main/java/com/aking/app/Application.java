package com.aking.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Application {

    private static String USERNAME;
    private static String PASSWORD;

    //Username - ComputerScience2FinalProject
    //Email - ComputerScience2Final@gmail.com
    //Password - QpAlZm123QpAlZm123

    public static void main(final String[] args) {

        final Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();



        // Checks format of desired File name
        while (fileName.contains(" ")) {
            System.out.println("File name must not contain any spaces.");
            fileName = scan.nextLine();
        }

        scan.close();

        // Sets USERNAME and PASSWORD to Strings provided in a text file
        try {
            Scanner fileScanner = new Scanner(new File("UserCredentials.txt"));
            USERNAME = fileScanner.nextLine();
            PASSWORD = fileScanner.nextLine();
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("UserCredentials.txt should contain GitHub user name and password.");
        }


        // Link to Chrome Driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Launch GitHub.com and login to dummy account
        final WebDriver driver = new ChromeDriver();
        driver.get("http://github.com/login");
        driver.manage().window().maximize();
        final WebElement loginLink = driver.findElement(By.xpath("/html/body/div[3]/main/div/form/div[4]/input[1]"));
        loginLink.sendKeys(USERNAME);
        final WebElement passwordLink = driver.findElement(By.xpath("/html/body/div[3]/main/div/form/div[4]/input[2]"));
        passwordLink.sendKeys(PASSWORD);
        final WebElement submitButton = driver.findElement(By.xpath("/html/body/div[3]/main/div/form/div[4]/input[9]"));
        submitButton.click();

        // Create New Repository
        final WebElement startProject = driver.findElement(By.xpath("/html/body/div[1]/header/div[6]/details/summary"));
        startProject.click();
        final WebElement newRepo = driver
                .findElement(By.xpath("/html/body/div[1]/header/div[6]/details/details-menu/a[1]"));
        newRepo.click();
        final WebElement repoName = driver
                .findElement(By.xpath("/html/body/div[4]/main/div/form/div[2]/auto-check/dl/dd/input"));
        repoName.sendKeys(fileName);
        final WebElement privateToggle = driver
                .findElement(By.xpath("/html/body/div[4]/main/div/form/div[3]/div[2]/label/input"));
        privateToggle.click();
        final WebElement includeREADME = driver
                .findElement(By.xpath("/html/body/div[4]/main/div/form/div[3]/div[4]/div[1]/label/input[2]"));
        includeREADME.click();

        // Wait for Repo info to be typed
        try {
            Thread.sleep(500);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // Continue
        final WebElement createRepo = driver.findElement(By.xpath("/html/body/div[4]/main/div/form/div[3]/button"));
        createRepo.click();

        // Close Driver
        driver.close();

        // Create batch file with chosen file name
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

        // Creates a List of commands to start Cmd and Batch file with commands
        final List<String> cmdAndArgs = new ArrayList<String>();
        cmdAndArgs.add("cmd.exe");
        cmdAndArgs.add("/C");
        cmdAndArgs.add("Start");
        cmdAndArgs.add("CmdCommands.bat");

        // ProcessBuilder with commands inserted
        final ProcessBuilder builder = new ProcessBuilder(cmdAndArgs);

        // Tell the builder the location of batch file and start process
        try {
            final File dir = new File(System.getProperty("user.dir"));
            builder.directory(dir);
            final Process p = builder.start();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}