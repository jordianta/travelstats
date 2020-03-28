package com.trebol.travelstats.services;

import com.trebol.travelstats.domainobjects.User;

public interface UserService {

    User findByUserName(String userName);
}
