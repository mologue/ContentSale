package com.mologue.contentSale.controller;

import com.mologue.contentSale.domain.Content;
import com.mologue.contentSale.domain.User;
import com.mologue.contentSale.service.serviceInterface.ContentService;
import com.mologue.contentSale.util.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */

@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = {"/","/index",""})
    public String index(ModelMap modelMap, HttpServletRequest request,
                        @RequestParam(value = "type",required = false)Integer type){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        if(type==null){
            type = 0;
        }
        HashMap<String,Integer> typeMap = new HashMap<String, Integer>();
        typeMap.put("listType",type);
        modelMap.addAttribute("RequestParameters",typeMap);

        List<Content> contentList = null;
        if(user==null){
            contentList = contentService.getAllContents();
        }else if(type==1){
            contentList = contentService.getAllUnboughtItemsForBuyer(user);
        }else if(user.getUserType()==0){
            contentList = contentService.getAllItemsForBuyer(user);
        }else{
            contentList = contentService.getAllItemsForSeller(user);
        }
        modelMap.addAttribute("contentList",contentList);

        return "index";
    }
}
