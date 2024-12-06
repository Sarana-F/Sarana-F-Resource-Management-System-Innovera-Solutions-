package com.innoverasolutions.resource_management.controller;

import com.innoverasolutions.resource_management.model.Project;
import com.innoverasolutions.resource_management.model.User;
import com.innoverasolutions.resource_management.service.ProjectService;
import com.innoverasolutions.resource_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // Endpoint for listing all projects for Admins
    @GetMapping("/admin/projects")
    public String listAllProjectsForAdmin(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "admin_projects";
    }

    // Endpoint for listing projects for Ideators
    @GetMapping("/ideator/projects")
    public String listProjectsForIdeator(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        List<Project> projects = projectService.findProjectsByUserId(user.getId());
        model.addAttribute("projects", projects);
        return "ideator_projects";
    }

    // Endpoint for listing projects for Investors
    @GetMapping("/investor/projects")
    public String listProjectsForInvestor(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "investor_projects";
    }

    // Endpoint for creating a new project
    @GetMapping("/projects/new")
    public String showProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "project_form";
    }

//    @PostMapping("/projects")
//    public String saveProject(@ModelAttribute("project") Project project, Principal principal) {
//        User user = userService.findByEmail(principal.getName()).orElseThrow();
//        project.setUser(user);
//        projectService.saveProject(project);
//        return "redirect:/projects";
//    }

    @PostMapping("/projects")
    public String saveProject(@ModelAttribute("project") Project project, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        project.setUser(user);
        projectService.saveProject(project);

        // Redirect based on the role of the user
        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/admin/projects";
        } else if (user.getRole().equalsIgnoreCase("IDEATOR")) {
            return "redirect:/ideator/projects";
        } else if (user.getRole().equalsIgnoreCase("INVESTOR")) {
            return "redirect:/investor/projects";
        }
        return "redirect:/projects";  // Fallback in case role is not recognized
    }

    @GetMapping("/projects/view/{id}")
    public String viewProjectDetails(@PathVariable Long id, Model model) {
        Project project = projectService.findProjectById(id).orElseThrow();
        model.addAttribute("project", project);
        return "project_details";
    }


    // Endpoint for editing a project
    @GetMapping("/projects/edit/{id}")
    public String showEditProjectForm(@PathVariable Long id, Model model) {
        Project project = projectService.findProjectById(id).orElseThrow();
        model.addAttribute("project", project);
        return "project_form";
    }

    @PostMapping("/projects/update")
    public String updateProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    // Endpoint for deleting a project
    @PostMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}


