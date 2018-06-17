package com.mologue.contentSale.controller;

import com.alibaba.fastjson.JSON;
import com.mologue.contentSale.service.serviceInterface.UserService;
import com.mologue.contentSale.util.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by wanru_h on 2018/6/16
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(){
//        System.out.println("start login");
        return "login";
    }

    @RequestMapping(value = "/api/login")
    @ResponseBody
    public String doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password,
                             HttpServletResponse response){
        HashMap modelMap = new HashMap();
        if(userService.userExists(userName,password)){
            modelMap.put("code",200);
            modelMap.put("message","login success");
            modelMap.put("result",true);
            Cookie userNameCookie = new Cookie("userName",userName);
            Cookie userTypeCookie = new Cookie("userType",""+userService.getUserType(userName));
            userNameCookie.setPath("/");
            userTypeCookie.setPath("/");
            userNameCookie.setMaxAge(60*60);
            userTypeCookie.setMaxAge(60*60);
            response.addCookie(userNameCookie);
            response.addCookie(userTypeCookie);
        }else{
            modelMap.put("code",401);
            modelMap.put("message","账户或密码错误");
            modelMap.put("result",false);
        }

        return JSON.toJSONString(modelMap);

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response,HttpServletRequest request){
        Cookie userNameCookie = UserSessionUtil.getUserCookie(request).get("userName");
        Cookie userTypeCookie = UserSessionUtil.getUserCookie(request).get("userType");
        userNameCookie.setMaxAge(0);
        userNameCookie.setPath("/");
        userTypeCookie.setMaxAge(0);
        userTypeCookie.setPath("/");
        response.addCookie(userNameCookie);
        response.addCookie(userTypeCookie);
        return "redirect:/";
    }
}
