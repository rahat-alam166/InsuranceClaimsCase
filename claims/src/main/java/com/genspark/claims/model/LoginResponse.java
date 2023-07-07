package com.genspark.claims.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse
{
   String message;
   Boolean status;
   String role;
   Long id;
   String name;
}
