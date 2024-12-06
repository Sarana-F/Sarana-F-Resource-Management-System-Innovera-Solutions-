//package com.innoverasolutions.resource_management.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.innoverasolutions.resource_management.model.User;
//import com.innoverasolutions.resource_management.service.UserService;
//
//import java.util.Optional;
//import java.util.List;
//
//@Controller
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/register")
//    public String register(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "register";
//    }
//
//    @PostMapping("/saveUser")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "login";
//    }
//
//    @PostMapping("/authenticate")
//    public String authenticate(@ModelAttribute("user") User user, Model model) {
//        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());
//        if (authenticatedUser != null) {
//            model.addAttribute("user", authenticatedUser);
//            switch (authenticatedUser.getRole()) {
//                case "ADMIN":
//                    return "redirect:/admin";
//                case "IDEATOR":
//                    return "redirect:/ideator";
//                case "INVESTOR":
//                    return "redirect:/investor";
//                default:
//                    return "login"; // fallback
//            }
//        } else {
//            model.addAttribute("error", "Invalid email or password");
//            return "login";
//        }
//    }
//
//    @GetMapping("/admin")
//    public String admin(Model model) {
//        return "admin";
//    }
//
//    @GetMapping("/admin/users")
//    public String listUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "admin_users";
//    }
//
//    @PostMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/admin/users";
//    }
//
//    @GetMapping("/ideator")
//    public String ideator(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//        } else {
//            // handle user not found
//        }
//        return "ideator";
//    }
//
//    @GetMapping("/ideator/projects")
//    public String ideatorProjects(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//            // Add logic to retrieve and display projects
//        } else {
//            // handle user not found
//        }
//        return "ideator_projects";
//    }
//
//    @GetMapping("/ideator/newProject")
//    public String newProject(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//            // Add logic to display new project creation form
//        } else {
//            // handle user not found
//        }
//        return "new_project";
//    }
//
//    @GetMapping("/ideator/hackathons")
//    public String ideatorHackathons(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//            // Add logic to retrieve and display hackathons
//        } else {
//            // handle user not found
//        }
//        return "ideator_hackathons";
//    }
//
//    @GetMapping("/ideator/logout")
//    public String logoutIdeator() {
//        return "redirect:/logout";
//    }
//
//
//
//    @GetMapping("/investor")
//    public String investor(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//        } else {
//            // handle user not found
//        }
//        return "investor";
//    }
//
//    @GetMapping("/investor/projects")
//    public String investorProjects(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//            // Add logic to retrieve and display projects
//        } else {
//            // handle user not found
//        }
//        return "investor_projects";
//    }
//
//    @GetMapping("/investor/investments")
//    public String investorInvestments(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//            // Add logic to retrieve and display investments
//        } else {
//            // handle user not found
//        }
//        return "investor_investments";
//    }
//
//    @GetMapping("/investor/hackathons")
//    public String investorHackathons(Model model, @AuthenticationPrincipal UserDetails currentUser) {
//        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
//        if (userOpt.isPresent()) {
//            model.addAttribute("user", userOpt.get());
//            // Add logic to retrieve and display hackathons
//        } else {
//            // handle user not found
//        }
//        return "investor_hackathons";
//    }
//
//    @GetMapping("/investor/logout")
//    public String logoutInvestor() {
//        return "redirect:/logout";
//    }
//
//
//    @GetMapping("/profile/{id}")
//    public String profile(@PathVariable Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "profile";
//    }
//
//    @GetMapping("/updateProfile/{id}")
//    public String updateProfile(@PathVariable Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "updateProfile";
//    }
//
//    @PostMapping("/saveProfile")
//    public String saveProfile(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/profile/" + user.getId();
//    }
//
//    @GetMapping("/changePassword/{id}")
//    public String changePassword(@PathVariable Long id, Model model) {
//        model.addAttribute("userId", id);
//        return "changePassword";
//    }
//
//    @PostMapping("/savePassword")
//    public String savePassword(@RequestParam Long userId, @RequestParam String newPassword) {
//        userService.updatePassword(userId, newPassword);
//        return "redirect:/profile/" + userId;
//    }
//}
package com.innoverasolutions.resource_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.innoverasolutions.resource_management.model.User;
import com.innoverasolutions.resource_management.service.UserService;

import java.util.Optional;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("user") User user, Model model) {
        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            model.addAttribute("user", authenticatedUser);
            switch (authenticatedUser.getRole()) {
                case "ADMIN":
                    return "redirect:/admin";
                case "IDEATOR":
                    return "redirect:/ideator";
                case "INVESTOR":
                    return "redirect:/investor";
                default:
                    return "login"; // fallback
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_users";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/ideator")
    public String ideator(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
        } else {
            // handle user not found
        }
        return "ideator";
    }

    // Removed /ideator/projects mapping to avoid conflict

    @GetMapping("/ideator/newProject")
    public String newProject(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            // Add logic to display new project creation form
        } else {
            // handle user not found
        }
        return "new_project";
    }

    @GetMapping("/ideator/hackathons")
    public String ideatorHackathons(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            // Add logic to retrieve and display hackathons
        } else {
            // handle user not found
        }
        return "ideator_hackathons";
    }

    @GetMapping("/ideator/logout")
    public String logoutIdeator() {
        return "redirect:/logout";
    }

    @GetMapping("/investor")
    public String investor(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
        } else {
            // handle user not found
        }
        return "investor";
    }

    // Removed /investor/projects mapping to avoid conflict

    @GetMapping("/investor/investments")
    public String investorInvestments(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            // Add logic to retrieve and display investments
        } else {
            // handle user not found
        }
        return "investor_investments";
    }

    @GetMapping("/investor/hackathons")
    public String investorHackathons(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOpt = userService.findByEmail(currentUser.getUsername());
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            // Add logic to retrieve and display hackathons
        } else {
            // handle user not found
        }
        return "investor_hackathons";
    }

    @GetMapping("/investor/logout")
    public String logoutInvestor() {
        return "redirect:/logout";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/updateProfile/{id}")
    public String updateProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateProfile";
    }

    @PostMapping("/saveProfile")
    public String saveProfile(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/profile/" + user.getId();
    }

    @GetMapping("/changePassword/{id}")
    public String changePassword(@PathVariable Long id, Model model) {
        model.addAttribute("userId", id);
        return "changePassword";
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam Long userId, @RequestParam String newPassword) {
        userService.updatePassword(userId, newPassword);
        return "redirect:/profile/" + userId;
    }
}
