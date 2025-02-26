package com.example.demo.Authentication;

import com.example.demo.Interfaces.IUsersDBRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsersDBRepository userRepository;

    public CustomUserDetailsService(IUsersDBRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<com.example.demo.Models.User> optionalUser = userRepository.findByUsername(username);

    com.example.demo.Models.User user = optionalUser
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
}

}

