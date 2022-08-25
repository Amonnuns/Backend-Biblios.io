package com.risingCode.bibliosIO.repository;

import com.risingCode.bibliosIO.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, UUID> {
    Optional<Reader> findByUsernameAndPassword(String userName, String password);
    Optional<Reader> findByUsername(String userName);
}
