package com.itconsortium.creditunion.chango.repository;
import com.itconsortium.creditunion.chango.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMsisdn(String msisdn);
}
