package com.innoverasolutions.resource_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    // Absolute path for uploads
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    // GET endpoint to show upload form
    @GetMapping("/upload")
    public String showUploadForm() {
        return "uploadForm";
    }

    // POST endpoint to handle file upload
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "uploadForm";
        }

        try {
            // Ensure the directory exists
            File directory = new File(UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            String fileName = file.getOriginalFilename();
            File destinationFile = new File(UPLOAD_DIRECTORY + File.separator + fileName);
            file.transferTo(destinationFile);

            model.addAttribute("message", "File uploaded successfully: " + fileName);
        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }

        return "uploadForm";
    }
}
