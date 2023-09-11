package com.easymeal.easymealgdc.authentication.filter;

import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.repository.PersonDetailsRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AppUserDetailsService implements UserDetailsService {
    private final PersonDetailsRepository userRepository;

    public AppUserDetailsService(PersonDetailsRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
        PersonDetails u = userRepository.findAllByEmailAddress( username );
        if(u == null) {
            throw new UsernameNotFoundException("User does not exist with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                u.getEmailAddress(),
                u.getPassword(),
                u.getRolesCollection().stream().map( Role::getName )
                        .map( SimpleGrantedAuthority::new )
                        .collect( Collectors.toSet())
        );
    }
}
