package com.example.test.services;

import com.example.test.model.user.User;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserSvc {

    private UserRepo userDao;

    public UserSvc(UserRepo userDao) {
        this.userDao = userDao;
    }

    public boolean checkIfCurrentUserMatchesParam(
            User currentUser, User paramUser
    ){
        if(currentUser.getId() == paramUser.getId()) return true;
        else return false;
    }

}
