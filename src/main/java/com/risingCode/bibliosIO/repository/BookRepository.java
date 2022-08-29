package com.risingCode.bibliosIO.repository;

import com.risingCode.bibliosIO.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Page<Book> findAll(Pageable pageable);
}
