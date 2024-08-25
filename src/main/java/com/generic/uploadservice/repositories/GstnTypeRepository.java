package com.generic.uploadservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.uploadservice.entities.Gstntype;
/**
 * Repository for GSTNType
 */
@Repository
public interface GstnTypeRepository extends JpaRepository<Gstntype, UUID> {

}
