package com.expense.io.filters;

import com.expense.io.config.UserDetailsServiceImpl;
import com.expense.io.error.Codes;
import com.expense.io.error.Error;
import com.expense.io.error.exceptions.TokenNotFoundException;
import com.expense.io.renderers.ErrorResponse;
import com.expense.io.utils.JWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTRequestFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JWT jwt = new JWT();

    public JWTRequestFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

//        logger.info("Filter hit!");

        Optional<String> header = Optional.ofNullable(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));

        try {
//            logger.info("checking for header " + header);
            if (header.isPresent()) {
                /* set authenticated user in Spring */
//                logger.info("inside header is present");

                /* Remove the word "Bearer" from the token */
                final String token = header.get()
                                           .substring(7);


                /* will throw exception if username is null */
                var username = jwt.extractUsername(token);

                /* Below code is pretty self explanatory */
                var user = userDetailsService.loadUserByUsername(username);

                var authenticationToken = new UsernamePasswordAuthenticationToken(user,
                                                                                  null,
                                                                                  user.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext()
                                     .setAuthentication(authenticationToken);

            }

            /* continue with the filter regardless if the header is present or not */
            filterChain.doFilter(httpServletRequest,
                                 httpServletResponse);

        } catch (MalformedJwtException | ExpiredJwtException | SignatureException | UsernameNotFoundException | TokenNotFoundException e) {
            logger.error(e);
            handleError(httpServletResponse, e);
        }
    }

    protected void handleError(HttpServletResponse response,
                               Exception exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        if (exception instanceof SignatureException) {
            response.getWriter()
                    .write(invalidToken((SignatureException) exception));
        } else if (exception instanceof UsernameNotFoundException) {
            response.getWriter()
                    .write(userNotFound((UsernameNotFoundException) exception));
        } else if (exception instanceof ExpiredJwtException) {
            response.getWriter()
                    .write(expiredToken((ExpiredJwtException) exception));
        } else if (exception instanceof TokenNotFoundException) {
            response.getWriter()
                    .write(tokenNotFound((TokenNotFoundException) exception));
        }
    }

//    @Override
//    protected boolean shouldNotFilter(@NotNull HttpServletRequest request) {
//        return Settings.getSkipPaths()
//                       .stream()
//                       .anyMatch(s -> pathMatcher.match(s,
//                                                        request.getServletPath()));
//    }


    private String tokenNotFound(TokenNotFoundException e) throws JsonProcessingException {
        var error = new Error(e.getLocalizedMessage(),
                              HttpStatus.UNAUTHORIZED,
                              Codes.AUTH_TOKEN_NOT_FOUND);
        var errorResponse = new ErrorResponse<>(error);
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }

    private String invalidToken(SignatureException e) throws JsonProcessingException {
        var error = new Error(e.getLocalizedMessage(),
                              HttpStatus.UNAUTHORIZED,
                              Codes.INVALID_AUTH_TOKEN);
        var errorResponse = new ErrorResponse<>(error);
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }

    private String expiredToken(ExpiredJwtException e) throws JsonProcessingException {
        var error = new Error(e.getLocalizedMessage(),
                              HttpStatus.UNAUTHORIZED,
                              Codes.AUTH_TOKEN_EXPIRED);
        var errorResponse = new ErrorResponse<>(error);
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }

    private String userNotFound(UsernameNotFoundException e) throws JsonProcessingException {
        var error = new Error(e.getLocalizedMessage(),
                              HttpStatus.UNAUTHORIZED,
                              Codes.USER_NOT_FOUND);
        var errorResponse = new ErrorResponse<>(error);
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }
}
