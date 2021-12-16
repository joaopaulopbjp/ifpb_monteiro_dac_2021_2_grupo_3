package com.bookstore.backend.testSystem.registerUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bookstore.backend.testSystem.Base;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterUserTest extends Base{

    public RegisterUserTest(){
        super(2000);
    }

    @Test
    @Order(1)
    public void RegisterUserSucess(){
        String username = "testeasd";
        String password = "teste12345678";
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/button")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/div[4]/p[2]")).click();
        WebElement usernameElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/input[1]"));
        usernameElement.sendKeys(username);
        WebElement emailElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/input[2]"));
        emailElement.sendKeys("teste123@gmail.com");
        WebElement senhaElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/input[3]"));
        senhaElement.sendKeys(password);
        this.waitScreen();
        WebElement botao = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/button[2]"));
        botao.click();
        WebElement usernameInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/input"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/form/input"));
        passwordInput.sendKeys(password);
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/button[2]")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[3]/a/button")).click();
        this.waitScreen();
        String nomeUser = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/h4")).getAttribute("innerText");
        assertEquals(username, nomeUser);
        driver.quit();
    }

    @Test
    @Order(2)
    public void RegisterUserLimite(){
        String password = "teste12345678";
        String[] username = {"aa", "aaaaaaaaaaaaaaa"};
        int permitiu = 0;
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/button")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/div[4]/p[2]")).click();
        WebElement emailElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/input[2]"));
        emailElement.sendKeys("teste123@gmail.com");
        WebElement senhaElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/input[3]"));
        senhaElement.sendKeys(password);
        for(int i = 0;i < username.length;i++){
            WebElement usernameElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/input[1]"));
            usernameElement.sendKeys(username[i]);
            this.waitScreen();
            WebElement botao = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/button[2]"));
            botao.click();
            WebElement isInvalid = driver.findElement(By.className("is-invalid"));
            //instanciaDriver();
            if(isInvalid!=null){
                permitiu++;
            }
        }
        driver.quit();
        assertEquals(username.length, permitiu);
    }
}
