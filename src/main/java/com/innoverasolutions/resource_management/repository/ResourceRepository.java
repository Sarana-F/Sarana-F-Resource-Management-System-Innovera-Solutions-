package com.innoverasolutions.resource_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.innoverasolutions.resource_management.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
