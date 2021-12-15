package com.bookstore.backend.testSystem;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
      protected WebDriver driver;

      protected void instanciaDriver(){
          System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
          this.driver = new FirefoxDriver();
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
