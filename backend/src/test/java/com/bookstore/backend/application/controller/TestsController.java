package com.bookstore.backend.application.controller;

import com.bookstore.backend.configs.ConfigClass;
import com.bookstore.backend.presentation.dto.login.CredentialsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class TestsController {

    protected String URLbase = new ConfigClass().getURLbase();

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


//     @Autowired
//     protected jwtAuthentication jwtAuthentication;

    public MvcResult login(String username, String password) throws JsonProcessingException, Exception {
        CredentialsDTO credentials = new CredentialsDTO(username, password);
        
        MvcResult result = mockMvc.perform(post(URLbase + "/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(credentials)))
            .andExpect(status().isOk())
            .andReturn();

        return result;
    }

//     protected String getToken() {
//     	TenantLocalStorage.setTenantId("test-um");
//         String bearerToken ="Bearer " + jwtAuthentication.getAuthSystemUser().getToken();
//         this.deslogar();
//         return bearerToken;
//     }

//     protected String changeToken(String tenant) {
//     	TenantLocalStorage.setTenantId(tenant);
//         String bearerToken ="Bearer " + jwtAuthentication.getAuthSystemUser().getToken();
//         this.deslogar();
//         return bearerToken;
//     }

//     protected void deslogar() {
//         SecurityContextHolder.getContext().setAuthentication(null);
//     }

//     protected String getTokenExpirado() {
//         return "Bearer eyJraWQiOiJRUXN1RFNZb1FDNW5rallCOEhnSUJHWWJaMFQ3YXlIdWpnb2x1OTNLRmVNPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI2ZDI4ZDNiZC00ZjliLTRjY2EtOWFmMC03NzE2ZDBmYzg1NDEiLCJjdXN0b206cm9sZXMiOiJbS0VBUl9IVUJfU0lTVEVNQV0iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xXzQyemFqRkFsayIsImNvZ25pdG86dXNlcm5hbWUiOiJ0ZXN0LXVtLnNpc3RlbWEiLCJhdWQiOiIxMGVnYjc5ZjNkcHFyNDViMjM1ZjJwY2s2MSIsImV2ZW50X2lkIjoiNTQ3ZGU1ZWItNGJmNy00YTk3LWJjYWUtM2RjODMyNDM5Y2VlIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2MjIxMzcyODAsIm5hbWUiOiJzaXN0ZW1hIiwiY3VzdG9tOnRlbmFudCI6InRlc3QtdW0iLCJleHAiOjE2MjIyMjM2ODAsImlhdCI6MTYyMjEzNzI4MCwiZmFtaWx5X25hbWUiOiJ0ZXN0LXVtIiwiZW1haWwiOiJ0ZXN0LXVtLnNpc3RlbWFAa2xvay50ZWNoIn0.Lrad7fcLWvFzyHqkaeUo6rWxNU4DQxLuQ_q8_k7iJcSFiSVuHKrN4LiLgzpA8xRrGSN1Wwunx4aeWNt7eIeB1W_P-kjvtvAe1HbPvvFk1oqH6cwK8ckm89SIqsbDBmQFwTOldwLwgBIoETA7SdR23RRRBcPCoZ89mrb14hcT8FSAKwLiVEzTidXSATMsAMk_ytBhQtMAmgtdtowJp6vPc7w_ARY6vK54W6MbJL0C7xFdyZQPeL0gpYMGgaOHl5FDRm6B2Z2RWQrOPor-raZf0-p6UFfi_JS0tCBpg-9cC23AijfsLciPqe8h6SkM16PXk6NbUGVZHDqXroLDwiZlIw";
//     }

//     public <T> int cadastrar(T request, String baseUrl) throws Exception {
//         MvcResult resultado =
//           mockMvc.perform(post(baseUrl)
//               .header("Authorization", this.getToken())
//               .contentType("application/json")
//               .content(objectMapper.writeValueAsString(request)))
//           .andExpect(status().isCreated())
//           .andReturn();

//         return JsonPath.read(resultado.getResponse().getContentAsString(), "$.data.id");
//     }

//     public void desativar(int idEntidade, String baseUrl) throws Exception {
//         mockMvc.perform(patch(baseUrl + "/{id}/ativo", idEntidade)
//           .header("Authorization", this.getToken()))
//         .andExpect(status().isOk())
//         .andExpect(MockMvcResultMatchers.jsonPath("$.data.ativo").value("false"));
//     }


//     public void ativar(int idEntidade, String baseUrl) throws Exception {
//         mockMvc.perform(patch(baseUrl + "/{id}/ativo", idEntidade)
//           .header("Authorization", this.getToken()))
//         .andExpect(status().isOk())
//         .andExpect(MockMvcResultMatchers.jsonPath("$.data.ativo").value("true"));
//     }
}