package com.goit.java_dev_module15_springSecurity.feature.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomAuthProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsPasswordService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsPasswordService.loadUserByUsername(username);
        return checkPassword(user, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(UserDetails user, String rawPassword) {
        if (Objects.equals(rawPassword, user.getPassword())) {
            User innerUser = new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );

            return new UsernamePasswordAuthenticationToken(innerUser, user.getPassword(), user.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }
}