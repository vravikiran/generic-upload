package com.generic.uploadservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.uploadservice.entities.Customertype;
/**
 * Repository for CustomerType
 */
@Repository
public interface CustomerTypeRepository extends JpaRepository<Customertype, UUID> {
}
