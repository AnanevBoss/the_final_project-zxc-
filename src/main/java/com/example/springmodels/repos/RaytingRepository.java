package com.example.springmodels.repos;

import com.example.springmodels.models.Copy;
import com.example.springmodels.models.Rayting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaytingRepository extends JpaRepository<Rayting, Long> {
}
