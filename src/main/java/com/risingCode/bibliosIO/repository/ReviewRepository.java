package com.risingCode.bibliosIO.repository;

import com.risingCode.bibliosIO.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
