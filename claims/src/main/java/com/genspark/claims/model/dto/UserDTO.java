package com.genspark.claims.model.dto;


import com.genspark.claims.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO
{
   private Long id;
   private String name;
   private String address;
   private String phone;
   private String email;
   private String password;
   private String role;
   private List<ClaimsDTO> claimsDTO = new ArrayList<>();

   public static UserDTO from(User user)
   {
      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getId());
      userDTO.setName(user.getName());
      userDTO.setAddress(user.getAddress());
      userDTO.setPhone(user.getPhone());
      userDTO.setEmail(user.getEmail());
      userDTO.setPassword(user.getPassword());
      userDTO.setRole(user.getRole());
      userDTO.setClaimsDTO(user.getClaims().stream().map(ClaimsDTO::from).collect(Collectors.toList()));
      return userDTO;
   }
}
