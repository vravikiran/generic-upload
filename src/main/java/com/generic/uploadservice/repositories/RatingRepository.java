package com.generic.uploadservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.uploadservice.entities.Rating;
/**
 * Repository for Rating
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

}
