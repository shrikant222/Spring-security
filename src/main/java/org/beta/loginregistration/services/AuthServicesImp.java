package org.beta.loginregistration.services;

import org.beta.loginregistration.dto.SignUpRequest;
import org.beta.loginregistration.model.User;
import org.beta.loginregistration.repo.RoleRepo;
import org.beta.loginregistration.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthServicesImp implements AuthServices {
    private static final Logger log = LoggerFactory.getLogger(AuthServicesImp.class);

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;  // Inject RoleRepo to fetch roles
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServicesImp(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;  // Initialize RoleRepo
    }

    @Override
    public boolean createUser(SignUpRequest signUpRequest) {
        log.info("Attempting to create user with email: {}", signUpRequest.getEmail());
        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            log.warn("User already exists with email: {}", signUpRequest.getEmail());
            return false;
        }

        // Create a new User object
        User user = new User();

        // Copy properties from SignUpRequest to User object
        BeanUtils.copyProperties(signUpRequest, user);

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        // Fetch the 'ROLE_USER' from the RoleRepo and assign it to the user
        roleRepo.findByName("ROLE_USER").ifPresent(user::setRole); // This will set the role if it exists

        // Save the user
        userRepo.save(user);

        log.info("User created successfully with email: {}", signUpRequest.getEmail());
        return true;
    }
}
