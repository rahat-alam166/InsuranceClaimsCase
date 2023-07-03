package com.genspark.claims.repo;
import com.genspark.claims.model.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClaimsRepo extends JpaRepository<Claims, Long> {
   void deleteClaimsById(Long id);

   Optional<Claims> findClaimsById(Long id);

   List<Claims> findAllByuser_id(Long user_id);
}
