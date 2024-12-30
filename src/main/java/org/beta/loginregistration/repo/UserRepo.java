package org.beta.loginregistration.repo;

import org.beta.loginregistration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    boolean existsByEmail(String email); //

    Optional<User> findByEmail(String email);
}
