package com.expense.io.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    private String access;
    private String refresh;

    public Token(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }
}
