package com.expense.io.project.auth;

import com.expense.io.jwt.TokenResponse;
import com.expense.io.utils.JWT;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager
                         ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("register")
    public AppUser createUser(@RequestBody @Valid AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                                                       loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Username or Password is incorrect");
        }

        final Optional<AppUser> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent()) {
            final TokenResponse response = new JWT().generateAuthTokens(user.get());
            return ResponseEntity.ok(response);
        } else {
            throw new BadCredentialsException("Failed!");
        }
    }
}
