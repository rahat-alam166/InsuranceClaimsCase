package com.genspark.claims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genspark.claims.model.Claims;
import com.genspark.claims.model.User;
import com.genspark.claims.model.dto.ClaimsDTO;
import com.genspark.claims.model.dto.PlainUserDTO;
import com.genspark.claims.service.ClaimsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ClaimsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClaimsControllerTest
{
   @Autowired
   private MockMvc mockMvc;
   @MockBean
   private ClaimsService claimsService;

   @Autowired
   private ObjectMapper objectMapper;

   private Claims claim;
   private ClaimsDTO claimsDTO;

   @BeforeEach
   public void init()
   {
      claimsDTO = new ClaimsDTO(1l, "test d", "test s", Date.valueOf("1999-01-25"),true,"test sup", new PlainUserDTO());
      claim = new Claims(1l, "test d", "test s", Date.valueOf("1999-01-25"),true,"test sup", new User());
   }

   @Test
   public void CreateClaim_ReturnCreated() throws Exception
   {
      given(claimsService.addClaims(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

      ResultActions response = mockMvc.perform(post("/claims/add")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(claimsDTO)));
      response.andExpect(MockMvcResultMatchers.status().isCreated());
   }

   @Test
   public void GetAllClaims_ReturnOK() throws Exception
   {
      List<Claims> claimsDTOList = new ArrayList<>();
      claimsDTOList.add(claim);
      when(claimsService.findAllClaims()).thenReturn(claimsDTOList);

      ResultActions response = mockMvc.perform(get("/claims/all")
              .contentType(MediaType.APPLICATION_JSON));
      response.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(claimsDTOList.size())));
   }
}
