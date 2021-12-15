package com.bookstore.backend.testSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
      protected WebDriver driver;
      protected Integer time;

      public Base(Integer time) {
        this.time = time;
          instanciaDriver();
      }

      protected void instanciaDriver(){
          System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
          this.driver = new ChromeDriver();
          this.driver.manage().window().maximize();
          driver.get("http://localhost:8081/");
          waitScreen();
          
      }
      
      protected void startCategoryAuthorCompany() {
        openProfile();
        autoSaveAuthor("Brito");
        autoSaveCategory("Horror");
        autoSaveCompany("Saraiva");
      }

      protected void goToHome() {
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/a")).click();
        waitScreen();
      }

      protected void autoLogin(String username, String password) {
          driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
          waitScreen();
          driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/button")).click();
          waitScreen();

          WebElement usernameInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/input"));
          usernameInput.sendKeys(username);
          WebElement passwordInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/form/input"));
          passwordInput.sendKeys(password);

          driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/button[2]")).click();

          waitScreen();
      }

      protected void openProfile() {
        WebElement sideBarButton = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button"));
        sideBarButton.click();

        waitScreen();
        WebElement profileButton = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/a"));
        profileButton.click();

        waitScreen();
      }

      protected void autoSaveAuthor(String name) {
        WebElement registerButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[2]/div/button[1]"));
        registerButton.click();
        
        WebElement nameInput = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/input"));
        nameInput.sendKeys(name);
        waitScreen();

        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/button[2]")).click();
        waitScreen();
      }

      protected void autoSaveCategory(String name) {
        WebElement registerButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[3]/div/button[1]"));
        registerButton.click();
        
        WebElement nameInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div/input"));
        nameInput.sendKeys(name);
        waitScreen();

        driver.findElement(By.xpath("/html/body/div/div/div[4]/div/button[2]")).click();
        waitScreen();
      }

      protected void autoSaveCompany(String name) {
        WebElement registerButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[4]/div/button[1]"));
        registerButton.click();
        
        WebElement nameInput = driver.findElement(By.xpath("/html/body/div/div/div[5]/div/input"));
        nameInput.sendKeys(name);
        waitScreen();

        driver.findElement(By.xpath("/html/body/div/div/div[5]/div/button[2]")).click();
        waitScreen();
      }

      protected void waitScreen() {
        try {Thread.sleep(time);} catch (InterruptedException e) {e.printStackTrace();}
      }
      protected void waitScreen(Integer time) {
        try {Thread.sleep(time);} catch (InterruptedException e) {e.printStackTrace();}
      }
}
