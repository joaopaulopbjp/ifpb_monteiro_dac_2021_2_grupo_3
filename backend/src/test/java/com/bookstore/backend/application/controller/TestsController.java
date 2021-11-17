package com.bookstore.backend.application.controller;

import com.bookstore.backend.configs.ConfigClass;
import com.bookstore.backend.presentation.dto.login.CredentialsDTO;
import com.bookstore.backend.presentation.dto.person.UserDTO;
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
        
        return "Bearer " + jsonLogin.getString("response");
    }

    protected void saveUser() throws JsonProcessingException, Exception {
        UserDTO dto = new UserDTO();
        dto.setUsername("user");
        dto.setPassword("userPass");
        dto.setEmail("user@gmail.com");

        mockMvc.perform(post(URLbase + "/user/save")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }
}