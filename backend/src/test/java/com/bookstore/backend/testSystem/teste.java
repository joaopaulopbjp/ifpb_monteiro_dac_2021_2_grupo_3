package com.bookstore.backend.testSystem;

import org.junit.jupiter.api.Test;

public class teste extends Base {
    
    @Test
    public void test() {
        instanciaDriver();
        driver.get("http://localhost:8081/");
    }

}
