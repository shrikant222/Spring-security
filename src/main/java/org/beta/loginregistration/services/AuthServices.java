package org.beta.loginregistration.services;

import org.beta.loginregistration.dto.SignUpRequest;

public interface AuthServices {
    boolean createUser(SignUpRequest signUpRequest);
}
