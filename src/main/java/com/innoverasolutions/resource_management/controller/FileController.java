package com.innoverasolutions.resource_management.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileController {

    // Directory where the uploaded files are stored
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    // Endpoint to display the list of files
    @GetMapping("/files")
    public String listFiles(Model model) {
        File folder = new File(UPLOAD_DIRECTORY);

        // Ensure directory exists
        if (!folder.exists()) {
            model.addAttribute("message", "No files available for download.");
            return "fileList";
        }

        List<String> fileNames = Arrays.stream(folder.listFiles())
                .filter(File::isFile) // Filter only files (exclude directories)
                .map(File::getName)  // Extract file names
                .collect(Collectors.toList());

        model.addAttribute("files", fileNames); // Pass file names to the model
        return "fileList"; // Return the Thymeleaf template fileList.html
    }

    // Endpoint to handle file downloads
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIRECTORY).resolve(fileName).normalize(); // Build the file path
            Resource resource = new UrlResource(filePath.toUri()); // Load the file as a resource

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource); // Serve the file as a download
            } else {
                return ResponseEntity.notFound().build(); // Return 404 if the file does not exist
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build(); // Return 400 for bad file paths
        }
    }
}
