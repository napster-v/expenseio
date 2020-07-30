package com.expense.io.jwt;

import com.expense.io.project.auth.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    private Token token;
    private AppUser user;
}
