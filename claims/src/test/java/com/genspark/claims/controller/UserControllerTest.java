package com.genspark.claims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genspark.claims.model.Claims;
import com.genspark.claims.model.User;
import com.genspark.claims.model.dto.ClaimsDTO;
import com.genspark.claims.model.dto.PlainUserDTO;
import com.genspark.claims.model.dto.UserDTO;
import com.genspark.claims.service.ClaimsService;
import com.genspark.claims.service.UserService;
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

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
   @Autowired
   private MockMvc mockMvc;
   @MockBean
   private UserService userService;

   @MockBean
   private ClaimsService claimsService;

   @Autowired
   private ObjectMapper objectMapper;

   private User user;
   private UserDTO userDTO;

   @BeforeEach
   public void init()
   {
      userDTO = new UserDTO(1l, "test n", "test a", "test p","test e","test p", "test r", new ArrayList<ClaimsDTO>());
      user = new User(1l, "test n", "test a", "test p","test e","test p", "test r", new ArrayList<Claims>());
   }

   @Test
   public void CreateUser_ReturnCreated() throws Exception
   {
      given(userService.addUser(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

      ResultActions response = mockMvc.perform(post("/user/add")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(userDTO)));
      response.andExpect(MockMvcResultMatchers.status().isCreated());
   }

   @Test
   public void GetAllUsers_ReturnOK() throws Exception
   {
      List<User> userDTOList = new ArrayList<>();
      userDTOList.add(user);
      when(userService.findAllUsers()).thenReturn(userDTOList);

      ResultActions response = mockMvc.perform(get("/user/all")
              .contentType(MediaType.APPLICATION_JSON));
      response.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(userDTOList.size())));
   }
}
