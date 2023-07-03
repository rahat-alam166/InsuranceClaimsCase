package com.genspark.claims.service;

import com.genspark.claims.model.Claims;
import com.genspark.claims.model.User;
import com.genspark.claims.exception.UserNotFoundException;
import com.genspark.claims.repo.ClaimsRepo;
import com.genspark.claims.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   private final UserRepo userRepo;
   private final ClaimsRepo claimsRepo;
   private final ClaimsService claimsService;

   @Autowired
   public UserService(UserRepo repo, ClaimsRepo claimsRepo, ClaimsService claimsService) {
      this.userRepo = repo;
      this.claimsRepo = claimsRepo;
      this.claimsService = claimsService;
   }
   @Autowired
   private PasswordEncoder passwordEncoder;
   public User addUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));

      userRepo.save(user);

      return user;
   }

   public List<User> findAllUsers() {
      return userRepo.findAll();
   }


   public User findUserById(Long id) {
      return (User) userRepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
   }

   @Transactional
   public void deleteUser(Long id) {
      userRepo.deleteUserById(id);
   }
   @Transactional
   public User addClaim(Long userId, Long claimId) //Testing purposes, implement both creation and adding in same step later
   {
      User user = this.findUserById(userId);
      Claims claim = claimsService.findClaimsById(claimId);
      user.addClaim(claim);
      claim.setUser(user);
      return user;
   }
   @Transactional
   public User removeClaim(Long userId, Long claimId) //Testing purposes, implement both creation and adding in same step later
   {
      User user = this.findUserById(userId);
      Claims claim = claimsService.findClaimsById(claimId);
      user.removeClaim(claim);
      return user;
   }

   public List<Claims> findAllClaims(Long id)
   {
      return claimsRepo.findAllByuser_id(id);
   }
}
