package com.risingCode.bibliosIO.repository;

import com.risingCode.bibliosIO.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>{

    Optional<Author> findByFirstName(String firstname);
    Page<Author> findAll(Pageable pageable);

}
