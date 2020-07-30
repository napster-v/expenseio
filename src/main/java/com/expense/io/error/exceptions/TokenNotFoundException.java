package com.expense.io.error.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFoundException extends AuthenticationException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
