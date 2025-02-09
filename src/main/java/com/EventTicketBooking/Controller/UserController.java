package com.EventTicketBooking.Controller;

import com.EventTicketBooking.Model.User;
import com.EventTicketBooking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Show signup form
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Thymeleaf template for signup
    }

    // Handle signup submission
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Check if email is already registered
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email is already registered.");
            return "signup";
        }
        userService.registerUser(user);
        model.addAttribute("message", "Registration successful! Please login.");
        return "redirect:/auth/login";
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Thymeleaf template for login
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        if (userService.authenticate(email, password)) {
            return "redirect:/"; // Redirect to events page after successful login
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // Return to login page with error
        }
    }
}
