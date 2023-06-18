package com.project.springbootwebstore.service.validation;

import com.project.springbootwebstore.security.CustomUserDetails;

public class SignupValidator implements Validator<CustomUserDetails> {
    @Override
    public boolean validate(CustomUserDetails customUserDetails) {
        return false;
    }
}
