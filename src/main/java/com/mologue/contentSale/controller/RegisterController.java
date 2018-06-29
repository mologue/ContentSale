package com.mologue.contentSale.controller;

import com.alibaba.fastjson.JSON;
import com.mologue.contentSale.domain.User;
import com.mologue.contentSale.service.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by wanru_h on 2018/6/17
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/api/register")
    @ResponseBody
    public String doRegister(@RequestParam("userName") String userName, @RequestParam("password") String password,
                             @RequestParam("userType") int type, HttpServletResponse response){   //0买家 1卖家
        HashMap modelMap = new HashMap();
        if(userService.userExists(userName,password)) {
            modelMap.put("code", 401);
            modelMap.put("message", "账户已经存在");
            modelMap.put("result", false);
        }else{
            User user =new User();
            user.setUserName(userName);
            user.setUserType(type);
            user.setPassword(password);
            userService.registerNewUser(user);
            modelMap.put("code",200);
            modelMap.put("message","login success");
            modelMap.put("result",true);
            Cookie userNameCookie = new Cookie("userName",userName);
            Cookie userTypeCookie = new Cookie("userType",""+type);
            userNameCookie.setPath("/");
            userTypeCookie.setPath("/");
            userNameCookie.setMaxAge(60*60);
            userTypeCookie.setMaxAge(60*60);
            response.addCookie(userNameCookie);
            response.addCookie(userTypeCookie);
        }
        return JSON.toJSONString(modelMap);
    }

}
