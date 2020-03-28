package com.trebol.travelstats.services;

import com.trebol.travelstats.domainobjects.User;
import com.trebol.travelstats.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUserName(final String userName) {
        return userRepository.findByUserName(userName);
    }
}
