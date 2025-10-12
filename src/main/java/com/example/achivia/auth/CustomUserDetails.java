package com.example.achivia.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails, Principal, Serializable {

    private UUID id;
    private String username;
    private String email;
    private String password;
    private Set< String > roles = new HashSet<>();


    @Override
    public String getName() {
        return username;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        return roles
                .stream()
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toSet() );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
