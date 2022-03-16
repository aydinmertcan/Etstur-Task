package com.etstur.config.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetail implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUsername(String username){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if(username.equals("admin")) {
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
                return new User(username, "", true,
                        true, true, true, grantedAuthorities);
            }
            return null;
    }
}