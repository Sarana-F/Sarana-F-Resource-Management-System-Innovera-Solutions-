package com.innoverasolutions.resource_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.innoverasolutions.resource_management.model.Resource;
import com.innoverasolutions.resource_management.service.ResourceService;

@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resources")
    public String resources(Model model) {
        model.addAttribute("resources", resourceService.findResources());
        return "resources";
    }

    @GetMapping("/registerResource")
    public String registerResource(Model model) {
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        return "registerResource";
    }

    @PostMapping("/saveResource")
    public String saveResource(@ModelAttribute("resource") Resource resource) {
        resourceService.saveResource(resource);
        return "redirect:/resources";
    }

    @GetMapping("/updateResource/{id}")
    public String updateResource(Model model, @PathVariable Long id) {
        Resource resource = resourceService.getResourceId(id);
        model.addAttribute("resource", resource);
        return "updateFormResource";
    }

    @GetMapping("/deleteResource/{id}")
    public String deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return "redirect:/resources";
    }
}
