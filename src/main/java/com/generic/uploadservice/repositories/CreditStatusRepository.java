package com.generic.uploadservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.uploadservice.entities.Creditstatus;
/**
 * Repository for CreditStatus
 */
@Repository
public interface CreditStatusRepository extends JpaRepository<Creditstatus, UUID> {

}
