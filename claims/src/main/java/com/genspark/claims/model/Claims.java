package com.genspark.claims.model;
import com.genspark.claims.model.dto.ClaimsDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Claims implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false, updatable = false)
   private Long id;
   private String description;
   private String status;
   @Temporal(TemporalType.DATE)
   private Date date;
   private boolean open = true;
   private String support;

   @ManyToOne
   private User user;

   public static Claims from(ClaimsDTO claimsDTO)
   {
      Claims claim = new Claims();
      claim.setDescription(claimsDTO.getDescription());
      claim.setStatus(claimsDTO.getStatus());
      claim.setDate(claimsDTO.getDate());
      claim.setOpen(claimsDTO.isOpen());
      claim.setSupport(claimsDTO.getSupport());
      return claim;
   }

}
