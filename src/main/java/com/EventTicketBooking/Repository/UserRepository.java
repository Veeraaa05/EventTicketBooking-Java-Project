package com.EventTicketBooking.Repository;

import com.EventTicketBooking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    User findByEmail(String email);

    // Find user by email and password
    User findByEmailAndPassword(String email, String password);
}
