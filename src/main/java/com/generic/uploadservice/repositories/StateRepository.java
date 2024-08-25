package com.generic.uploadservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.uploadservice.entities.State;
/**
 * Repository for State
 */
@Repository
public interface StateRepository extends JpaRepository<State, UUID> {

}
