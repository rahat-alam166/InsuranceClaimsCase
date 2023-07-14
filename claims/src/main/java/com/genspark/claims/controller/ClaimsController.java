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
   //    @GetMapping("/authentication")
//    public boolean getAuthentication(){
//        return true;
//    }
//   @Autowired
//   public AdminService adminService;

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
//    public ResponseEntity<List<Claims>> getAllClaims() {
//        List<Claims> restaurants = restaurantService.findAllClaims();
//        return new ResponseEntity<>(restaurants, HttpStatus.OK);
//    }



   @GetMapping("claims/find/{id}")
   public ResponseEntity<ClaimsDTO> getClaimsById (@PathVariable("id") Long id) {
      Claims claim = claimsService.findClaimsById(id);
      return new ResponseEntity<>(ClaimsDTO.from(claim), HttpStatus.OK);
   }
//    public ResponseEntity<Claims> getClaimsById (@PathVariable("id") Long id) {
//        Claims restaurant = restaurantService.findClaimsById(id);
//        return new ResponseEntity<>(restaurant, HttpStatus.OK);
//    }

   @PostMapping("claims/add")
   public ResponseEntity<ClaimsDTO> addClaims(@RequestBody ClaimsDTO restaurant) {
      Claims newClaims = claimsService.addClaims(Claims.from(restaurant));
      return new ResponseEntity<>(ClaimsDTO.from(newClaims), HttpStatus.CREATED);
   }
//    public ResponseEntity<Claims> addClaims(@RequestBody Claims restaurant) {
//        Claims newClaims = restaurantService.addClaims(restaurant);
//        return new ResponseEntity<>(newClaims, HttpStatus.CREATED);
//    }

   @PutMapping("claims/update")
   public ResponseEntity<ClaimsDTO> updateClaims(@RequestBody ClaimsDTO claim) {
      Claims updateClaims = claimsService.updateClaims(Claims.from(claim));
      return new ResponseEntity<>(ClaimsDTO.from(updateClaims), HttpStatus.OK);
   }
//    public ResponseEntity<Claims> updateClaims(@RequestBody Claims restaurant) {
//        Claims updateClaims = restaurantService.updateClaims(restaurant);
//        return new ResponseEntity<>(updateClaims, HttpStatus.OK);
//    }

   @DeleteMapping("claims/delete/{id}")
   public ResponseEntity<?> deleteClaims(@PathVariable("id") Long id) {
      claimsService.deleteClaims(id);
      return new ResponseEntity<>(HttpStatus.OK);
   }

//   @GetMapping("admin/all")
//   public ResponseEntity<List<Admin>> getAllAdmins(){
//      List<Admin> a = adminService.findAllAdmins();
//      return new ResponseEntity<>(a, HttpStatus.OK);
//   }
//
//   @GetMapping("admin/find/{id}")
//   public ResponseEntity<Admin> getAdminById (@PathVariable("id") Long id){
//      Admin a = adminService.findAdminById(id);
//      return new ResponseEntity<>(a, HttpStatus.OK);
//   }
//
//   @PostMapping("admin/add")
//   public ResponseEntity<Admin> addClaims(@RequestBody Admin a) {
//      Admin newAdmin = adminService.addAdmin(a);
//      return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
//   }
//   @PutMapping("admin/update")
//   public ResponseEntity<Admin> updateAdmin(@RequestBody Admin a){
//      Admin updateAdmin = adminService.updateAdmin(a);
//      return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
//   }
//   @DeleteMapping("admin/delete/{id}")
//   public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id){
//      adminService.deleteUserById(id);
//      return new ResponseEntity<>(HttpStatus.OK);
//   }
//


}
/**

 @DeleteMapping("restaurant/delete/{id}")
 public ResponseEntity<?> deleteClaims(@PathVariable("id") Long id) {
 restaurantService.deleteClaims(id);
 return new ResponseEntity<>(HttpStatus.OK);
 }

 */
