package com.bookstore.backend.testSystem.product;

import java.math.BigDecimal;

import com.bookstore.backend.testSystem.Base;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest extends Base {
    
    @Test
    @Order(1)
    public void ProductTestSucess() {
        autoLogin("admin", "admin");
        startCategoryAuthorCompany();

        WebElement registerProductButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[1]/div/a[1]"));
        registerProductButton.click();
        waitScreen(1000);

        sendInfo("Livro Teste", "2020", "100", "30.99", "200", "Essa é a descrição do livro");
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/select")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/select/option[2]")).click();
        
    }

    private void sendInfo(String title, String yearLaunch, String pages, String price, String inventory, String description) {
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[1]")).sendKeys(title);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[2]")).sendKeys(yearLaunch);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[3]")).sendKeys(pages);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/input[1]")).sendKeys(price);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/input[2]")).sendKeys(inventory);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/textarea")).sendKeys(description);
    }
}
