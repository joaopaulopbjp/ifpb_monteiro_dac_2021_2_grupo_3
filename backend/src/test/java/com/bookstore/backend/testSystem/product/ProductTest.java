package com.bookstore.backend.testSystem.product;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.testSystem.Base;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ProductTest extends Base {
    
    public ProductTest() {
        super(1000);
    }

    @Test
    public void registerProductTestSucess() {
        autoLogin("admin", "admin");
        startCategoryAuthorCompany();

        WebElement registerProductButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[1]/div/a[1]"));
        registerProductButton.click();
        waitScreen();

        String title = "Livro Teste";
        String price = "30.99";

        sendbookInfoOnRegister(title, "2020", "100", price, "200", "C:\\Users\\Thauanzin\\Downloads\\livro-o-hobbit-edicao-com-capa-do-filme.png", "Essa é a descrição do livro");
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[5]/button")).click();
        waitScreen(3000);

        goToHome();
        waitScreen(3000);

        String titleReturn = driver.findElement(By.xpath("/html/body/div/div/div[6]/div/h6")).getText();
        String priceReturn = driver.findElement(By.xpath("/html/body/div/div/div[6]/div/b-form-rating/div/span")).getText();
        String imageBookSrc = driver.findElement(By.xpath("/html/body/div/div/div[6]/div/button/img")).getDomProperty("src");

        assertEquals(title, titleReturn);
        assertEquals(("R$: " + price).replace(".", ","), priceReturn);
        assertNotNull(imageBookSrc);
    }

    @Test
    public void registerProductTestError() {
        autoLogin("admin", "admin");
        startCategoryAuthorCompany();

        WebElement registerProductButton = driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[1]/div/a[1]"));
        registerProductButton.click();
        waitScreen();

        List<String> xpaths = new ArrayList<String>();
        xpaths.add("html/body/div/div/div[3]/div/div/div[2]/input[1]");
        xpaths.add("html/body/div/div/div[3]/div/div/div[2]/input[2]");
        xpaths.add("html/body/div/div/div[3]/div/div/div[2]/input[3]");
        xpaths.add("html/body/div/div/div[3]/div/div/div[3]/input[1]");
        xpaths.add("html/body/div/div/div[3]/div/div/div[3]/input[2]");
        xpaths.add("/html/body/div/div/div[3]/div/div/div[4]/textarea");

        WebElement registerButton = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[5]/button"));
        for (String string : xpaths) {
            WebElement element = driver.findElement(By.xpath(string));
            element.sendKeys("");
            registerButton.click();
            assertTrue(element.getAttribute("class").contains("is-invalid"));
        }
        
        sendbookInfoOnRegister("novo livro", "2000", "300", "300.30", "100", "C:\\Users\\Thauanzin\\Downloads\\117013112_1GG.jpg", "descriptiondo livro não pode ficar vazia");
        waitScreen();
        registerButton.click();
        waitScreen();
        assertEquals("http://localhost:8081/#/Profile", driver.getCurrentUrl());

    }

    @Test
    public void deleteProductTestSucess() {
        registerProductTestSucess();
        openProfile();

        driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[1]/div/button")).click();
        waitScreen(500);
        driver.findElement(By.xpath("/html/body/div/div/div[12]/div/div/span/input")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[12]/div/button[2]")).click();
        waitScreen(100);

        goToHome();
        waitScreen(3000);

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.xpath("/html/body/div/div/div[6]/div/h6")));
    }

    @Test
    public void deleteProductTestError() {
        autoLogin("admin", "admin");
        openProfile();

        driver.findElement(By.xpath("/html/body/div/div/div[15]/div/div/div[2]/div[1]/div/button")).click();
        waitScreen(500);
        driver.findElement(By.xpath("/html/body/div/div/div[12]/div/button[2]")).click();
        waitScreen(500);

        assertEquals("1px solid rgb(255, 0, 0)", driver.findElement(By.xpath("/html/body/div/div/div[12]/div/div")).getCssValue("border"));
    }

    private void sendbookInfoOnRegister(String title, String yearLaunch, String pages, String price, String inventory, String imageUrl, String description) {
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[1]")).sendKeys(title);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[2]")).sendKeys(yearLaunch);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/input[3]")).sendKeys(pages);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/input[1]")).sendKeys(price);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/input[2]")).sendKeys(inventory);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/select/option[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[3]/div/input")).sendKeys(imageUrl);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/div[1]/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/div[2]/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[4]/textarea")).sendKeys(description);
    }
}
