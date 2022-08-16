package com.risingCode.bibliosIO.repository;

import com.risingCode.bibliosIO.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
    Optional<User>  findByUserName(String userName);
}
