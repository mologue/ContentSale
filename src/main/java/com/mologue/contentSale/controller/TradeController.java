package com.mologue.contentSale.controller;


import com.alibaba.fastjson.JSON;
import com.mologue.contentSale.dataDefine.ShoppingCarItem;
import com.mologue.contentSale.dataDefine.TradeInfo;
import com.mologue.contentSale.domain.Content;
import com.mologue.contentSale.domain.Order;
import com.mologue.contentSale.domain.ShoppingCar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private ShoppingCarService shoppingCarService;

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

    @RequestMapping(value = "/api/addShopping")
    @ResponseBody
    public void addNewToShoppingCar(HttpServletRequest request,@RequestParam("id") long contentId, @RequestParam("num") int num){
        User user = UserSessionUtil.getUser(request);
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setUserName(user==null?"":user.getUserName());
        shoppingCar.setAmount(num);
        shoppingCar.setContentId(contentId);
        shoppingCar.setDate(DateUtil.date2String(new Date()));
        shoppingCarService.addNewShoppingItem(shoppingCar);
    }

    @RequestMapping(value = "/settleAccount")
    public String settleAccount(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        User user = UserSessionUtil.getUser(request);
        if(user!=null){
            modelMap.addAttribute("user",user);
            List<ShoppingCarItem> shoppingIttemList = shoppingCarService.getAllShoppingListForUser(user.getUserName());
            modelMap.addAttribute("shoppingItemList",shoppingIttemList);
        }
        return "settleAccount";
    }

    @RequestMapping(value = "/api/deleteFromShoppingCar")
    @ResponseBody
    public ModelMap doDeleteShoppingItem(@RequestParam("shoppingCarItem") ShoppingCarItem shoppingCarItem,ModelMap modelMap,HttpServletRequest request){
        shoppingCarService.deleteFromShoppingCar(shoppingCarItem.getCarItemId());
        modelMap.addAttribute("code", 200);
        modelMap.addAttribute("message", "success");
        return modelMap;
    }

    @RequestMapping(value = "/api/buy")
    @ResponseBody
    public String doSettle(@RequestBody List<TradeInfo> buyList, ModelMap modelMap,
                             HttpServletRequest request){
        HashMap<String,String> map = new HashMap<>();
        User user = UserSessionUtil.getUser(request);
        String userName = user==null?"":user.getUserName();
        for(TradeInfo tradeInfo:buyList){
            long contentId = shoppingCarService.getShoppingCarItem(tradeInfo.getCarItemId()).getContentId();
            Content content = contentService.getContentById(contentId);
            if(content == null){
                continue;
            }
            Order order = new Order();
            order.setUserName(userName);
            order.setContentId(content.getContentId());
            order.setTitle(content.getTitle());
            order.setPicture(content.getPicture());
            order.setPrice(content.getPrice());
            order.setAmount(tradeInfo.getNumber());
            order.setDate(DateUtil.date2String(new Date()));
            orderService.makeNewOrder(order);
            shoppingCarService.deleteFromShoppingCar(tradeInfo.getCarItemId());
        }
        map.put("code", "200");
        map.put("message", "success");
        map.put("result","true");
        return JSON.toJSONString(map);
    }

}
