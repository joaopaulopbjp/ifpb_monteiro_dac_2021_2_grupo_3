package com.bookstore.backend.testSystem;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
      protected WebDriver driver;

      protected void instanciaDriver(){
          System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
          this.driver = new FirefoxDriver();
      }
}
