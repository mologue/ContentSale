package com.mologue.contentSale.service;

import com.mologue.contentSale.dao.ContentDAO;
import com.mologue.contentSale.dao.OrderDAO;
import com.mologue.contentSale.domain.Order;
import com.mologue.contentSale.service.serviceInterface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/17
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ContentDAO contentDAO;

    @Override
    public List<Order> getOrderForUser(String userName, long contentId) {
        List<Order> orders = orderDAO.getOrderForUser(userName,contentId);
        return orders;
    }

    @Override
    public List<Order> getAllOrdersForUser(String userName) {
        List<Order> orders = orderDAO.getAllOrderForUser(userName);
        return orders;
    }

    @Override
    public void makeNewOrder(Order order) {
        orderDAO.insertNewOrder(order);
    }
}
