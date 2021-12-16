package com.bookstore.backend.testSystem.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.bookstore.backend.testSystem.Base;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends Base{

    public LoginTest(){
        super(2000);
    }
    
    @Test
    @Order(1)
    public void LoginSucess(){
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/button")).click();
        WebElement usernameElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/input"));
        usernameElement.sendKeys("admin");
        WebElement senhaElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/form/input"));
        senhaElement.sendKeys("admin");
        WebElement botao = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/button[2]"));
        botao.click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        String nomeUser = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/h4")).getAttribute("innerText");
        assertEquals("admin", nomeUser);
        driver.quit();
    }

    @Test
    @Order(2)
    public void LoginError(){
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        this.waitScreen();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/div/button")).click();
        String[] usernames = {"teste", "admin"};
        int naoPassou = 0;
        String[] password = {"admin","teste"};
        for(int i = 0;i<usernames.length;i++){
            WebElement usernameElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/input"));
            usernameElement.sendKeys(usernames[i]);
            WebElement senhaElement = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/form/input"));
            senhaElement.sendKeys(password[i]);
            WebElement botao = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/button[2]"));
            botao.click();
            this.waitScreen();
            String error = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[3]/div[1]/p")).getAttribute("innerText");
            if(error.equals("Username or password is incorrect")){
                naoPassou++;
            }
        }
        assertEquals(2, naoPassou);
        //driver.findElement(By.xpath("/html/body/div/div/div[1]/div/nav/ul/li[2]/a/button")).click();
        driver.quit();
    }


}
