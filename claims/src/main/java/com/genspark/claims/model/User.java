package com.genspark.claims.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.genspark.claims.model.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false, updatable = false)
   private Long id;
   private String name;
   private String address;
   private String phone;
   @Column(unique = true)
   private String email;
   private String password;
   private String role;


   @OneToMany(
           cascade = CascadeType.ALL
   )
   @JoinColumn(name = "user_id")
   private List<Claims> claims = new ArrayList<>();

   public static User from(UserDTO userDTO)
   {
      User user = new User();
      user.setName(userDTO.getName());
      user.setAddress(userDTO.getAddress());
      user.setPhone(userDTO.getPhone());
      user.setEmail(userDTO.getEmail());
      user.setPassword(userDTO.getPassword());
      user.setRole(userDTO.getRole());
      return user;
   }

   public void addClaim(Claims claim)
   {
      claims.add(claim);
   }
   public void removeClaim(Claims claim)
   {
      claims.remove(claim);
   }


//private String createdAt; //TODO implement creation time
}
