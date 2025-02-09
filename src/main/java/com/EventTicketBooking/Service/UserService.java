package com.EventTicketBooking.Service;

import com.EventTicketBooking.Model.User;
import com.EventTicketBooking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public void registerUser(User user) {
        // Hash the password before saving
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Authenticate user by email and password
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && BCrypt.checkpw(password, user.getPassword());
    }
}
