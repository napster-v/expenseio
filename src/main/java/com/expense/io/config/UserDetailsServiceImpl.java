package com.expense.io.config;

import com.expense.io.project.auth.AppUser;
import com.expense.io.project.auth.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AppUser> user = repository.findByUsername(username);
        if (user.isPresent()) return user.get();
        else throw new UsernameNotFoundException("No such user");
    }
}
