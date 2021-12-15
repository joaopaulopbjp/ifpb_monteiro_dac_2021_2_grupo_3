package com.bookstore.backend.testSystem.product;

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

        autoSaveAuthor("Luquinha da rolimã");
    }

}
