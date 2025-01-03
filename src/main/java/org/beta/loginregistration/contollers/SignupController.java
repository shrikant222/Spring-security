package org.beta.loginregistration.contollers;

import org.beta.loginregistration.dto.SignUpRequest;
import org.beta.loginregistration.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthServices authServices;

    @Autowired
    public SignupController(AuthServices authServices) {
        this.authServices = authServices;

    }
    @PostMapping
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        boolean isUserCreated = authServices.createUser(signUpRequest);

        if (isUserCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User created successfully").toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User already exists or failed to create user").toString());
        }


    }




}
