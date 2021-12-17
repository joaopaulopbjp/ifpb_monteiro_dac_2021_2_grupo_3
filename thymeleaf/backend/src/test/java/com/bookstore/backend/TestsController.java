package com.bookstore.backend;

import com.bookstore.backend.configs.ConfigClass;
import com.bookstore.backend.presentation.dto.author.AuthorDTO;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
import com.bookstore.backend.presentation.dto.company.PublishingCompanyDTO;
import com.bookstore.backend.presentation.dto.image.ImageDTO;
import com.bookstore.backend.presentation.dto.inventory.InventoryDTO;
import com.bookstore.backend.presentation.dto.login.CredentialsDTO;
import com.bookstore.backend.presentation.dto.person.UserDTO;
import com.bookstore.backend.presentation.dto.product.BookDTO;
import com.bookstore.backend.presentation.dto.sale.ItemOrderDTO;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestsController {

    protected String URLbase = new ConfigClass().getURLbase();

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    public MvcResult login(String username, String password) throws JsonProcessingException, Exception {
        CredentialsDTO credentials = new CredentialsDTO(username, password);
        
        MvcResult result = mockMvc.perform(post(URLbase + "/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(credentials)))
            .andExpect(status().isOk())
            .andReturn();

        return result;
    }

    public String getToken(String username, String password) throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        JSONObject jsonLogin = new JSONObject(this.login(username, password).getResponse().getContentAsString());
        
        return "Bearer " + jsonLogin.getString("token");
    }

    protected MvcResult saveUser() throws JsonProcessingException, Exception {
        UserDTO dto = new UserDTO();
        dto.setUsername("user");
        dto.setPassword("userPass");
        dto.setEmail("userTest@gmail.com");

        MvcResult result = mockMvc.perform(post(URLbase + "/user/save")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn();

        return result;
    }

    protected MvcResult saveBook() throws JsonProcessingException, Exception {
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

        MvcResult result = mockMvc.perform(post(URLbase + "/book")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn();

        return result;
    }

    protected MvcResult saveCompany() throws JsonProcessingException, Exception {
        PublishingCompanyDTO dto = new PublishingCompanyDTO();
        dto.setName("saraiva");
        
        MvcResult result = mockMvc.perform(post(URLbase + "/company")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn();
        
        return result;
    }

    protected MvcResult saveAuthor() throws JsonProcessingException, Exception {
        AuthorDTO dto = new AuthorDTO();
        dto.setName("brito Author");
        
        MvcResult result = mockMvc.perform(post(URLbase + "/author")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn();

        return result;
    }

    protected MvcResult saveCategory() throws JsonProcessingException, Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("adventure");
        
        MvcResult result = mockMvc.perform(post(URLbase + "/category")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andReturn();

        return result;
    }

    protected MvcResult saveProductShoppingCart() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception {
        this.saveUser();

        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 3, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        
        MvcResult result = mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andReturn();
        return result;
    }

    public MvcResult addShoppingCart() throws JsonProcessingException, JSONException, UnsupportedEncodingException, Exception{        
        /* UserDTO user = new UserDTO();
        user.setUsername("user");
        user.setPassword("user123456");
        user.setEmail("user@gmail.com");

        mockMvc.perform(post(URLbase + "/user/save")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated()); */
        this.saveUser();
        ShoppingCartDTO dto = new ShoppingCartDTO();
        
        JSONObject jsonBook = new JSONObject(this.saveBook().getResponse().getContentAsString());

        List<ItemOrderDTO> list = new ArrayList<>();
        list.add(new ItemOrderDTO(0l, 5, null, jsonBook.getLong("id")));

        dto.setItemList(list);
        
        MvcResult result = mockMvc.perform(post(URLbase + "/shopping-cart/add")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk()).andReturn();

        return result;
    }
}