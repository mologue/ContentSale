package com.mologue.contentSale.service.serviceInterface;

import com.mologue.contentSale.domain.Order;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
public interface OrderService {
    List<Order> getOrderForUser(String userName,long contentId);
    List<Order> getAllOrdersForUser(String userName);
    void makeNewOrder(Order order);
}
