package com.bookstore.backend.testSystem.registerAuthor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bookstore.backend.testSystem.Base;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterAuthorTest extends Base{
    
    public RegisterAuthorTest() {
        super(2000);
    }

    @Test
    @Order(1)
    public void RegisterAuthorSucess(){
        autoLogin("admin", "admin");
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/a")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[2]/div/button[1]")).click();
        WebElement authorElement = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/input"));
        authorElement.sendKeys("adventure");
        WebElement botao = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/button[2]"));
        botao.click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[2]/div/button[2]")).click();
        String conteudo = driver.findElement(By.xpath("/html/body/div/div/div[9]/div/div")).getText();
        this.waitScreen();
        assertFalse(conteudo.isEmpty());
        driver.quit();
    }

    @Test
    @Order(2)
    public void RegisterAuthorIsEmpty(){
        autoLogin("admin", "admin");
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/a")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[2]/div/button[1]")).click();
        WebElement authorElement = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/input"));
        authorElement.sendKeys("");
        WebElement botao = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/button[2]"));
        botao.click();
        this.waitScreen();
        WebElement isInvalid = driver.findElement(By.className("is-invalid"));
        assertNotNull(isInvalid);
        driver.quit();
    }
}
