package com.genspark.claims.service;


import com.genspark.claims.model.Claims;
import com.genspark.claims.exception.UserNotFoundException;
import com.genspark.claims.repo.ClaimsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimsService
{
   private final ClaimsRepo claimsRepo;

   @Autowired
   public ClaimsService(ClaimsRepo claimsRepo) {
      this.claimsRepo = claimsRepo;
   }

   public Claims addClaims(Claims claims) {
      return claimsRepo.save(claims);
   }

   public List<Claims> findAllClaims() {
      return claimsRepo.findAll();
   }

   public Claims updateClaims(Claims claims) {
      System.out.println(claims);
      Claims change = this.findClaimsById(claims.getId());
      change.setId(claims.getId());
      change.setDescription(claims.getDescription());
      change.setStatus(claims.getStatus());
      change.setOpen(claims.isOpen());
      change.setSupport(claims.getSupport());
      return claimsRepo.save(change);
   }

   public Claims findClaimsById(Long id) {
      System.out.println(id);
      return (Claims) claimsRepo.findClaimsById(id).orElseThrow(() -> new UserNotFoundException("Claim by id " + id + " was not found"));
   }

   @Transactional
   public void deleteClaims(Long id) {
      claimsRepo.deleteClaimsById(id);
   }
}
