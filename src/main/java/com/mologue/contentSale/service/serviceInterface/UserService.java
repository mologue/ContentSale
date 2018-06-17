package com.mologue.contentSale.service.serviceInterface;

import com.mologue.contentSale.domain.User;

/**
 * Created by wanru_h on 2018/6/16
 */
public interface UserService {
    boolean userExists(String userName,String password);
    int getUserType(String userName);
    User getUserByName(String userName);
    void registerNewUser(User user);
}
