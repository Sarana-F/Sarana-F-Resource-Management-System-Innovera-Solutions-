package com.innoverasolutions.resource_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/hackathon")
    public String showHackathonPage() {
        // Return the view name for the hackathon management page
        return "hackathon";  // Make sure you have hackathon.html in the templates folder
    }
}
