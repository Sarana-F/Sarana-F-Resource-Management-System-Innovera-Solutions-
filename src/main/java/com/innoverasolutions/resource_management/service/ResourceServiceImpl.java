package com.innoverasolutions.resource_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.innoverasolutions.resource_management.model.Resource;
import com.innoverasolutions.resource_management.repository.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public void saveResource(Resource resource) {
        resourceRepository.save(resource);
    }

    @Override
    public Iterable<Resource> findResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource getResourceId(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
