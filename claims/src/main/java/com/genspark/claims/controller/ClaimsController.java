package com.genspark.claims.controller;


import com.genspark.claims.model.Claims;
import com.genspark.claims.model.dto.ClaimsDTO;
import com.genspark.claims.service.ClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ClaimsController {
   private final ClaimsService claimsService;

   public ClaimsController(ClaimsService claimsService) {
      this.claimsService = claimsService;
   }


   @GetMapping("")
   public String getHome(){
      return "Hello World";
   }

   @GetMapping("claims/all")
   public ResponseEntity<List<ClaimsDTO>> getAllClaims() {
      List<Claims> claim = claimsService.findAllClaims();
      List<ClaimsDTO> claimsDTOS = claim.stream().map(ClaimsDTO::from).collect(Collectors.toList());
      return new ResponseEntity<>(claimsDTOS, HttpStatus.OK);
   }



   @GetMapping("claims/find/{id}")
   public ResponseEntity<ClaimsDTO> getClaimsById (@PathVariable("id") Long id) {
      Claims claim = claimsService.findClaimsById(id);
      return new ResponseEntity<>(ClaimsDTO.from(claim), HttpStatus.OK);
   }


   @PostMapping("claims/add")
   public ResponseEntity<ClaimsDTO> addClaims(@RequestBody ClaimsDTO claim) {
      Claims newClaims = claimsService.addClaims(Claims.from(claim));
      return new ResponseEntity<>(ClaimsDTO.from(newClaims), HttpStatus.CREATED);
   }


   @PutMapping("claims/update")
   public ResponseEntity<ClaimsDTO> updateClaims(@RequestBody ClaimsDTO claim) {
      Claims updateClaims = claimsService.updateClaims(Claims.from(claim));
      return new ResponseEntity<>(ClaimsDTO.from(updateClaims), HttpStatus.OK);
   }


   @DeleteMapping("claims/delete/{id}")
   public ResponseEntity<?> deleteClaims(@PathVariable("id") Long id) {
      claimsService.deleteClaims(id);
      return new ResponseEntity<>(HttpStatus.OK);
   }



}

