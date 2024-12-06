//package com.innoverasolutions.resource_management.config;
//
//import com.innoverasolutions.resource_management.model.User;
//import com.innoverasolutions.resource_management.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void init() {
//        try {
//            // Create the admin user if it doesn't exist
//            if (!userRepository.findByEmail("admin@example.com").isPresent()) {
//                User admin = new User();
//                admin.setName("Admin");
//                admin.setEmail("admin@example.com");
//                admin.setPassword(passwordEncoder.encode("adminpassword"));
//                admin.setRole("ADMIN");
//                userRepository.save(admin);
//                logger.info("Admin user created successfully.");
//            } else {
//                logger.info("Admin user already exists.");
//            }
//        } catch (Exception e) {
//            logger.error("Error initializing admin user: ", e);
//            throw e;
//        }
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/register", "/saveUser", "/static/**", "/css/**", "/error").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/ideator/**").hasRole("IDEATOR")
//                        .requestMatchers("/investor/**").hasRole("INVESTOR")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/perform_login")
//                        .defaultSuccessUrl("/profile", true)
//                        .successHandler(authenticationSuccessHandler())
//                        .failureUrl("/login?error=true")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")   // Ensures Spring Security listens on this URL for logout
//                        .logoutSuccessUrl("/login?logout") // Redirects to the login on successful logout
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                )
//                .csrf()   // Ensure CSRF is enabled
//                .and()
//                .build();
//    }
//
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return (request, response, authentication) -> {
//            String role = authentication.getAuthorities().stream()
//                    .findFirst().get().getAuthority();
//            if (role.equals("ROLE_ADMIN")) {
//                response.sendRedirect("/admin");
//            } else if (role.equals("ROLE_IDEATOR")) {
//                response.sendRedirect("/ideator");
//            } else if (role.equals("ROLE_INVESTOR")) {
//                response.sendRedirect("/investor");
//            } else {
//                response.sendRedirect("/profile");
//            }
//        };
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            User user = userRepository.findByEmail(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
//                    .password(user.getPassword())
//                    .roles(user.getRole())
//                    .build();
//        };
//    }
//}
//
//

package com.innoverasolutions.resource_management.config;

import com.innoverasolutions.resource_management.model.User;
import com.innoverasolutions.resource_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        try {
            // Create the admin user if it doesn't exist
            if (!userRepository.findByEmail("admin@example.com").isPresent()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("adminpassword"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                logger.info("Admin user created successfully.");
            } else {
                logger.info("Admin user already exists.");
            }
        } catch (Exception e) {
            logger.error("Error initializing admin user: ", e);
            throw e;
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/saveUser", "/static/**", "/css/**", "/error").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/ideator/**").hasRole("IDEATOR")
                        .requestMatchers("/investor/**").hasRole("INVESTOR")
                        .requestMatchers("/projects/view/**").hasAnyRole("ADMIN", "IDEATOR", "INVESTOR")  // Allow access to /projects/view/** for all roles
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/profile", true)
                        .successHandler(authenticationSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")   // Ensures Spring Security listens on this URL for logout
                        .logoutSuccessUrl("/login?logout") // Redirects to the login on successful logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf()   // Ensure CSRF is enabled
                .and()
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().stream()
                    .findFirst().get().getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin");
            } else if (role.equals("ROLE_IDEATOR")) {
                response.sendRedirect("/ideator");
            } else if (role.equals("ROLE_INVESTOR")) {
                response.sendRedirect("/investor");
            } else {
                response.sendRedirect("/profile");
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };
    }
}

