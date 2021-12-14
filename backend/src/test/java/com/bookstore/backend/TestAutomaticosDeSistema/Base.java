package com.bookstore.backend.TestAutomaticosDeSistema;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

    protected WebDriver driver;

    protected void instanciaDriver(){
        this.driver = new FirefoxDriver();
    }
}
