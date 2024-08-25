package com.generic.uploadservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.generic.uploadservice.entities.Brand;
/**
 * Repository for Brand
 */
@Repository
public interface BrandRepository extends CrudRepository<Brand, UUID> {
}
