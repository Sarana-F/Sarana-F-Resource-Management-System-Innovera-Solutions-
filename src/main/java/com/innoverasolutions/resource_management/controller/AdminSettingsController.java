package com.innoverasolutions.resource_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/settings")
public class AdminSettingsController {

    @GetMapping
    public String settingsPage() {
        // Add any required attributes to the model, if needed
        return "settings"; // Ensure this matches the template file name (settings.html)
    }

    @PostMapping("/update")
    public String updateSettings(@RequestParam("companyName") String companyName,
                                 @RequestParam("emailNotifications") String emailNotifications,
                                 @RequestParam("passwordPolicy") String passwordPolicy,
                                 RedirectAttributes redirectAttributes) {
        // Perform the logic to save these settings (e.g., save to database)
        // Example:
        System.out.println("Company Name: " + companyName);
        System.out.println("Email Notifications: " + emailNotifications);
        System.out.println("Password Policy: " + passwordPolicy);

        // Add a success message
        redirectAttributes.addFlashAttribute("successMessage", "Settings updated successfully!");

        // Redirect back to the settings page
        return "redirect:/admin/settings";
    }
}