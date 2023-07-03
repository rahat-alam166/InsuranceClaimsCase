package com.genspark.claims.controller;


import com.genspark.claims.model.Claims;
import com.genspark.claims.model.User;
import com.genspark.claims.model.dto.ClaimsDTO;
import com.genspark.claims.model.dto.UserDTO;
import com.genspark.claims.service.ClaimsService;
import com.genspark.claims.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
   private final UserService userService;
   private final ClaimsService claimsService;

   public UserController(UserService userService, ClaimsService claimsService) {
      this.userService = userService;
      this.claimsService = claimsService;
   }

   @GetMapping("/all")
   public ResponseEntity<List<UserDTO>> getAllUsers() {
      List<User> users = userService.findAllUsers();
      List<UserDTO> userDTOS = users.stream().map(UserDTO::from).collect(Collectors.toList());
      return new ResponseEntity<>(userDTOS, HttpStatus.OK);
   }

   @GetMapping("/all/claims/{id}")
   public ResponseEntity<List<ClaimsDTO>> getAllPlates(@PathVariable("id") Long id) {
      List<Claims> claims = userService.findAllClaims(id);
      List<ClaimsDTO> claimsDTOS = claims.stream().map(ClaimsDTO::from).collect(Collectors.toList());
      return new ResponseEntity<>(claimsDTOS, HttpStatus.OK);
   }


   @GetMapping("/find/{id}")
   public ResponseEntity<UserDTO> getUserById (@PathVariable("id") Long id) {
      User user = userService.findUserById(id);
      return new ResponseEntity<>(UserDTO.from(user), HttpStatus.OK);
   }

   @PostMapping("/add")
   public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
      User newUser = userService.addUser(User.from(user));
      return new ResponseEntity<>(UserDTO.from(newUser), HttpStatus.CREATED);
   }

   @PutMapping("/update")
   public ResponseEntity<User> updateUser(@RequestBody UserDTO user) {
      User newUser = userService.addUser(User.from(user));
      return new ResponseEntity<>(newUser, HttpStatus.OK);
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
      userService.deleteUser(id);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   @PostMapping("/add/{userId}")
   public ResponseEntity<UserDTO> addClaim(@PathVariable("userId") Long userId, @RequestBody ClaimsDTO claims) {
      Claims newClaims = claimsService.addClaims(Claims.from(claims));
      User user = userService.addClaim(userId, newClaims.getId());
      return new ResponseEntity<>(UserDTO.from(user), HttpStatus.OK);
   }


   @DeleteMapping("/delete/{userId}/claim/{claimId}")
   public ResponseEntity<UserDTO> deleteClaim(@PathVariable("userId") Long userId, @PathVariable("claimId") Long claimId) {
      User user = userService.removeClaim(userId, claimId);
      return new ResponseEntity<>(UserDTO.from(user), HttpStatus.OK);
   }

}
