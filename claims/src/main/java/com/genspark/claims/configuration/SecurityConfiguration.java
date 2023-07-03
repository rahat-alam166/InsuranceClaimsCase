package com.genspark.claims.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

   @Bean
   public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//      http.cors((cors) -> cors.disable());
//      http.cors(Customizer.withDefaults());
//      http.csrf((csrf) -> csrf.disable());
//      http.authorizeHttpRequests(requests-> requests
//                      .requestMatchers("/authentication")
//                      .hasAuthority("ADMIN")
//                      .requestMatchers("restaurant/all")
//                      .permitAll()
//                      .requestMatchers("admin/add")
//                      .permitAll()
//                      .anyRequest().permitAll()
//              )
//              .httpBasic(Customizer.withDefaults())
//              .formLogin(Customizer.withDefaults());
//
//      return http.build();
//   }
}
