package com.bookstore.backend.testSystem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
      protected WebDriver driver;

      public Base() {
          instanciaDriver();
      }

      protected void instanciaDriver(){
          System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
          this.driver = new FirefoxDriver();
          
      }

      protected void autoLogin(String username, String password) {
          driver.get("http://localhost:8081/");
          waitScreen(2000);
          driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
          waitScreen(500);
          driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/button")).click();
          waitScreen(500);

          WebElement usernameInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/input"));
          usernameInput.sendKeys(username);
          WebElement passwordInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/form/input"));
          passwordInput.sendKeys(password);

          driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/button[2]")).click();

          waitScreen(1000);
      }

      protected void waitScreen() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      }

      protected void waitScreen(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      }

      protected void quit() {
        driver.quit();
      }
}
