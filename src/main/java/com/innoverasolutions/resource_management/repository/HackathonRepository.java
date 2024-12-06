package com.innoverasolutions.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.innoverasolutions.resource_management.model.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
}
