package com.mologue.contentSale.util;

import com.mologue.contentSale.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UserSessionUtil {

    public static User getUser(HttpServletRequest request){
        Map<String,Cookie> userCookie = getUserCookie(request);
        if(userCookie!=null){
            User user = new User();
            user.setUserName(userCookie.get("userName").getValue());
            user.setUserType(Integer.parseInt(userCookie.get("userType").getValue()));
            return user;
        }
        return null;
    }

    public static Map<String,Cookie> getUserCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> userCookie = new HashMap<String, Cookie>();
        if(cookies!=null){
            boolean hasUserName = false;
            boolean hasUserType = false;
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("userName")){
                    userCookie.put("userName",cookie);
                    hasUserName = true;
                }else if(cookie.getName().equals("userType")){
                    userCookie.put("userType",cookie);
                    hasUserType = true;
                }
            }
            if(hasUserName && hasUserType){
                return userCookie;
            }
        }
        return null;
    }
}
