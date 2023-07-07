package com.genspark.claims.repo;
import com.genspark.claims.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
   void deleteUserById(Long id);
   Optional<User> findUserById(Long id);
   User findByEmail(String email);
   Optional<User> findOneByEmailAndPassword(String email, String password);
}
