package com.risingCode.bibliosIO.repository;

import com.risingCode.bibliosIO.models.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LibrarianRepository extends JpaRepository<Librarian, UUID> {

    Optional<Librarian> findByUsernameAndPassword(String username, String password);
    Optional<Librarian> findByUsername(String username);
}
