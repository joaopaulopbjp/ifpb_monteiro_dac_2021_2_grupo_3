package com.bookstore.backend.testSystem.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bookstore.backend.testSystem.Base;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest extends Base {
    
    public ProductTest() {
        super(1000);
    }

    @Test
    @Order(1)
    public void ProductTestSucess() {
        autoLogin("admin", "admin");
        startCategoryAuthorCompany();

        WebElement registerProductButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[1]/div/a[1]"));
        registerProductButton.click();
        waitScreen();

        String title = "Livro Teste";
        String price = "30.99";

        sendbookInfoOnRegister(title, "2020", "100", price, "200", "Essa é a descrição do livro");
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/select/option[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/div/input"))
        .sendKeys("D://Thauan//Jurassic_World_Evolution_2_Screenshot_2021.11.28_-_22.46.45.19.png");
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/div[1]/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/div[2]/div/div/input")).click();

        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[5]/button")).click();
        waitScreen(3000);

        goToHome();
        waitScreen(3000);

        String titleReturn = driver.findElement(By.xpath("/html/body/div/div/div[6]/div/h6")).getText();
        String priceReturn = driver.findElement(By.xpath("/html/body/div/div/div[6]/div/b-form-rating/div/span")).getText();

        assertEquals(title, titleReturn);
        assertEquals("R$: " + price, priceReturn);
    }

    private void sendbookInfoOnRegister(String title, String yearLaunch, String pages, String price, String inventory, String description) {
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[1]")).sendKeys(title);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[2]")).sendKeys(yearLaunch);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[3]")).sendKeys(pages);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/input[1]")).sendKeys(price);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/input[2]")).sendKeys(inventory);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/textarea")).sendKeys(description);
    }
}
