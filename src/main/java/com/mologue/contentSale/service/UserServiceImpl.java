package com.mologue.contentSale.service;

import com.mologue.contentSale.dao.UserDAO;
import com.mologue.contentSale.domain.User;
import com.mologue.contentSale.service.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanru_h on 2018/6/17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean userExists(String userName, String password) {
        int userNum = userDAO.getUserNum(userName,password);
        return userNum==0?false:true;
    }

    @Override
    public int getUserType(String userName) {
        return userDAO.getUserTypeByName(userName);
    }

    @Override
    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    @Override
    public void registerNewUser(User user) {
        userDAO.registerNewUser(user);
    }
}
