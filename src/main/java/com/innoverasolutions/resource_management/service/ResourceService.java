package com.innoverasolutions.resource_management.service;

import com.innoverasolutions.resource_management.model.Resource;

public interface ResourceService {
    void saveResource(Resource resource);
    Iterable<Resource> findResources();
    Resource getResourceId(Long id);
    void deleteResource(Long id);
}
