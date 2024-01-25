package com.collins.backend.security;

import com.collins.backend.entities.User;
import com.collins.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with that email: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities());
    }

    private List<GrantedAuthority> getAuthorities(){
        return Collections.emptyList();
    }
}
