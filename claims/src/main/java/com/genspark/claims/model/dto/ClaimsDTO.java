package com.genspark.claims.model.dto;
import com.genspark.claims.model.Claims;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsDTO
{
   private Long id;
   private String description;
   private String status;
   @Temporal(TemporalType.DATE)
   private Date date;
   private boolean open;
   private String support;
   private PlainUserDTO plainUserDTO;

   public static ClaimsDTO from(Claims claims)
   {
      ClaimsDTO claimsDTO = new ClaimsDTO();
      claimsDTO.setId(claims.getId());
      claimsDTO.setDescription(claims.getDescription());
      claimsDTO.setStatus(claims.getStatus());
      claimsDTO.setDate(claims.getDate());
      claimsDTO.setOpen(claims.isOpen());
      claimsDTO.setSupport(claims.getSupport());
      if (Objects.nonNull(claims.getUser()))
      {
         claimsDTO.setPlainUserDTO(PlainUserDTO.from(claims.getUser()));
      }
      return claimsDTO;
   }
}
