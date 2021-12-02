package com.bookstore.backend.IntegrationsTests.controller.evaluate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.backend.TestsController;
import com.bookstore.backend.presentation.dto.evaluate.EvaluateDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class EvaluateTest extends TestsController {

    @Test
    @Order(1)
    public void saveEvaluateSucess() throws JsonProcessingException, Exception {
        this.saveUser();
        this.saveBook();

        EvaluateDTO dto = new EvaluateDTO(0l, 5, "Muito bom", 1l, null);
        
        mockMvc.perform(post(URLbase + "/evaluate")
            .header("Authorization", this.getToken("user", "userPass"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void saveEvaluateUnauthorized() throws JsonProcessingException, Exception {
        EvaluateDTO dto = new EvaluateDTO(0l, 5, "Muito bom", 1l, null);
        
        mockMvc.perform(post(URLbase + "/evaluate")
            .header("Authorization", this.getToken("admin", "admin"))
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(3)
    public void saveEvaluateForbidden() throws JsonProcessingException, Exception {
        EvaluateDTO dto = new EvaluateDTO(0l, 5, "Muito bom", 1l, null);
        
        mockMvc.perform(post(URLbase + "/evaluate")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isForbidden());
    }

    @Test
    @Order(4)
    public void saveEvaluateBadRequest() throws JsonProcessingException, Exception {
        EvaluateDTO[] list = {new EvaluateDTO(0l, 0, "Muito bom", 1l, null),
            new EvaluateDTO(0l, 6, "Muito bom", 1l, null),
            new EvaluateDTO(0l, 5, "M", 1l, null),
            new EvaluateDTO(0l, 5, "", 1l, null),
            new EvaluateDTO(0l, 5, null, 1l, null),
            new EvaluateDTO(0l, 5, "Muito bom", 0l, null)};

        for (EvaluateDTO evaluateDTO : list) {
            mockMvc.perform(post(URLbase + "/evaluate")
                .header("Authorization", this.getToken("user", "userPass"))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(evaluateDTO)))
                .andExpect(status().isBadRequest());
        }
        
    }
}
