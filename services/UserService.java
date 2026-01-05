////package com.example.demo.service;
////
////import com.example.demo.model.User;
////import com.example.demo.repository.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////@Service
////public class UserService {
////
////    private final UserRepository userRepository;
////
////    @Autowired
////    public UserService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
////
////    // Save user to the database
////    public User saveUser(User user) {
////        return userRepository.save(user);
////    }
////
////    // Find user by email (login)
////    public User findByUsername(String email) {
////        return userRepository.findByEmail(email);
////    }
////
////    // Method to get the logged-in student's ID
////    public Long getLoggedInStudentId() {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
////            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////            String username = userDetails.getUsername();  // In your case, username is the email
////
////            // Fetch the user by email and return the student ID
////            User user = userRepository.findByEmail(username);
////            if (user != null) {
////                return user.getId();  // Return the student's ID
////            }
////        }
////
////        return null;  // Return null if the user is not logged in or doesn't exist
////    }
////    // Fetch user by ID (e.g., student ID)
////    public User getUserById(Long userId) {
////        return userRepository.findById(userId)
////                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));  // Return user if found, otherwise throw exception
////    }
////}
//package com.example.demo.service;
//
//import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User getUserById(Long id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        return optionalUser.orElse(null);
//    }
//
//    // Example: get logged-in user email (replace with your Security logic)
//    public User getLoggedInUser() {
//        // For now, we fetch a test user (replace with Spring Security authentication)
//        return userRepository.findById(1L).orElse(null);
//    }
//}
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // ---------------- Get All Supervisors ----------------
    public List<User> getAllSupervisors() {
        return userRepository.findByRole("SUPERVISOR"); // role ka name jo aap DB me use karte ho
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔴 THIS METHOD MUST EXIST EXACTLY LIKE THIS
    public Long getLoggedInStudentId() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        Object principal = auth.getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User user = userRepository.findByEmail(email);

            if (user != null) {
                return user.getId();
            }
        }

        return null;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}
