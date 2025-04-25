package com.pm.patientService.Repository;

import com.pm.patientService.Modal.patientModal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface patientRepository extends JpaRepository<patientModal, UUID> {
    boolean existsByEmail(String email);
}
