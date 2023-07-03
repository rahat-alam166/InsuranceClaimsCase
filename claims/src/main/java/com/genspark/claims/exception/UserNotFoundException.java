package com.genspark.claims.exception;

public class UserNotFoundException extends RuntimeException{
   public UserNotFoundException(String message) {
      super(message);
   }
}
