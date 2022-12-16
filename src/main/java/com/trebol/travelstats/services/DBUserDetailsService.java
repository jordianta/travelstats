package com.trebol.travelstats.services;

import com.trebol.travelstats.domainobjects.Role;
import com.trebol.travelstats.domainobjects.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class DBUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final var user = userService.findByUserName(userName);
        final var authorities = getUserAuthority(Set.of(user.getRole()));
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(final Set<Role> userRoles) {
        return userRoles.stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).distinct()
                        .collect(toList());
    }

    private UserDetails buildUserForAuthentication(final User user, final List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}