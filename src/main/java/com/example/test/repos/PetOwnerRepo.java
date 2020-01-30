package com.example.test.repos;

import com.example.test.model.pets.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepo extends JpaRepository<PetOwner, Long> {
}
