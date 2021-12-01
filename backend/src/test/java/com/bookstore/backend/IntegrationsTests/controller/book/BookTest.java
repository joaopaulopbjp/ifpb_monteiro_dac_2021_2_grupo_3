package com.bookstore.backend.IntegrationsTests.controller.book;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.backend.TestsController;
import com.bookstore.backend.presentation.dto.image.ImageDTO;
import com.bookstore.backend.presentation.dto.inventory.InventoryDTO;
import com.bookstore.backend.presentation.dto.product.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class BookTest extends TestsController{

    @Test
    @Order(1)
    public void testSaveBook() throws JsonProcessingException, Exception {
        BookDTO dto = new BookDTO();
        dto.setTitle("Em busca da batata perdida");
        dto.setDescription("description");
        dto.setYearLaunch(2000);
        dto.setPages(10);
        dto.setPrice(new BigDecimal(10.50));
        InventoryDTO inventory = new InventoryDTO();
        inventory.setAmount(10);
        dto.setInventory(inventory);

        ArrayList<ImageDTO> imagemList = new ArrayList<>();
        
        ImageDTO image = new ImageDTO();
        image.setBase64("Paisagem");
        imagemList.add(image);
        dto.setImageList(imagemList);
        
        JSONObject jsonCompany = new JSONObject(this.saveCompany().getResponse().getContentAsString());
        JSONObject jsonAuthor = new JSONObject(this.saveAuthor().getResponse().getContentAsString());
        JSONObject jsonCategory = new JSONObject(this.saveCategory().getResponse().getContentAsString());

        dto.setIdCompany(jsonCompany.getLong("id"));

        ArrayList<Long> idAuthorList = new ArrayList<Long>();
        idAuthorList.add(jsonAuthor.getLong("id"));
        
        dto.setIdAuthorList(idAuthorList);

        ArrayList<Long> idCategoryList = new ArrayList<Long>();
        idCategoryList.add(jsonCategory.getLong("id"));

        dto.setIdCategoryList(idCategoryList);

        mockMvc.perform(post(URLbase + "/book")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)));

        BookDTO dtoCopy = dto;
        dto = new BookDTO();
        dto.setId(1L);
        MvcResult book = mockMvc.perform(get(URLbase + "/book/find/find-by-id")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andReturn();
            

        JSONObject myBook = new JSONObject(book.getResponse().getContentAsString());
        assertEquals(dtoCopy.getTitle(), myBook.getString("title"));
        assertEquals(10, myBook.getJSONObject("inventory").getInt("amount"));
        assertNotNull(myBook.getString("description"));
        

    }

    
}
