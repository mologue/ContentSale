package com.mologue.contentSale.controller;


import com.mologue.contentSale.dataDefine.TradeInfo;
import com.mologue.contentSale.domain.Content;
import com.mologue.contentSale.domain.Order;
import com.mologue.contentSale.domain.User;
import com.mologue.contentSale.service.serviceInterface.ContentService;
import com.mologue.contentSale.service.serviceInterface.OrderService;
import com.mologue.contentSale.service.serviceInterface.ShoppingCarService;
import com.mologue.contentSale.util.DateUtil;
import com.mologue.contentSale.util.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
@Controller
public class TradeController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ContentService contentService;
//    @Autowired
//    private ShoppingCarService shoppingCarService;

    @RequestMapping(value = "/account")
    public String showAccountInfo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        List<Order> orderList = orderService.getAllOrdersForUser(user.getUserName());
        modelMap.addAttribute("buyList",orderList);
        return "account";
    }

    @RequestMapping(value = "/settleAccount")
    public String settleAccount(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
        }
        return "settleAccount";
    }

    @RequestMapping(value = "/api/buy")
    @ResponseBody
    public ModelMap doSettle(@RequestBody List<TradeInfo> buyList, ModelMap modelMap,
                             HttpServletRequest request, HttpServletResponse response){
        User user = UserSessionUtil.getUser(request);
        String userName = user==null?"":user.getUserName();
        for(TradeInfo tradeInfo:buyList){
            Content content = contentService.getContentById(tradeInfo.getContentId());
            Order order = new Order();
            order.setUserName(userName);
            order.setContentId(content.getContentId());
            order.setTitle(content.getTitle());
            order.setPicture(content.getPicture());
            order.setPrice(content.getPrice());
            order.setAmount(tradeInfo.getNumber());
            System.out.println("tradeNumber:"+tradeInfo.getNumber());
            order.setDate(DateUtil.date2String(new Date()));
            orderService.makeNewOrder(order);
        }
        modelMap.addAttribute("code", 200);
        modelMap.addAttribute("message", "success");
        modelMap.addAttribute("result",true);
        return modelMap;
    }

}
