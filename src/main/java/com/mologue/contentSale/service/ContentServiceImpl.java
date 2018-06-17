package com.mologue.contentSale.service;

import com.mologue.contentSale.dao.ContentDAO;
import com.mologue.contentSale.dao.OrderDAO;
import com.mologue.contentSale.domain.Content;
import com.mologue.contentSale.domain.Order;
import com.mologue.contentSale.domain.User;
import com.mologue.contentSale.service.serviceInterface.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by wanru_h on 2018/6/17
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDAO contentDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Content getContent(String title, Double price, String picture, String summary, String detail) {
        return contentDAO.getContent(title,price,picture,summary,detail);
    }

    @Override
    public Content getContentById(long id) {
        return contentDAO.getContentById(id);
    }

    @Override
    public void inserContent(Content content) {
        contentDAO.addContent(content);
    }

    @Override
    public void updateContent(Content content) {
        contentDAO.updateContent(content);
    }

    @Override
    public void deleteContentById(long contentId) {
        contentDAO.deleteContentById(contentId);
    }

    @Override
    public List<Content> getAllContents() {
        return contentDAO.getAllContents();
    }

    @Override
    public List<Content> getAllItemsForBuyer(User user) {
        List<Content> buyContents = new ArrayList<Content>();
        List<Content> contents = contentDAO.getAllContents();
        if(contents==null || contents.size()<1){
            return buyContents;
        }
        List<Order> orders = orderDAO.getAllOrderForUser(user.getUserName());
        HashMap<Integer,Integer> contentIdOrderIdMap = new HashMap<Integer, Integer>();
//        HashSet<Integer> orderIdSet = new HashSet<Integer>();
        if(orders!=null && orders.size()>0){
            for(Order order:orders){
                contentIdOrderIdMap.put(order.getContentId(),order.getOrderId());
            }
        }
        for(Content content:contents){
            Content buyContent = new Content();
            buyContent.setContentId(content.getContentId());
            buyContent.setPrice(content.getPrice());
            buyContent.setDetail(content.getDetail());
            buyContent.setPicture(content.getPicture());
            buyContent.setTitle(content.getTitle());
            buyContent.setSummary(content.getSummary());
            if(contentIdOrderIdMap.containsKey(content.getContentId())){
                buyContent.setHasBought(true);
                Order order = orderDAO.getOrderByOrderId(contentIdOrderIdMap.get(content.getContentId()));
                buyContent.setBuyNum(order.getAmount());
                buyContent.setBuyPrice(order.getPrice());
            }else{
                buyContent.setHasBought(false);
            }
            buyContents.add(buyContent);
        }
        return buyContents;
    }

    @Override
    public List<Content> getAllUnboughtItemsForBuyer(User user) {
        List<Content> unBoughtContents = new ArrayList<Content>();
        List<Content> contents = contentDAO.getAllContents();
        if(contents==null || contents.size()<1){
            return unBoughtContents;
        }
        List<Order> orders = orderDAO.getAllOrderForUser(user.getUserName());
        HashSet<Integer> orderIdSet = new HashSet<Integer>();
        if(orders!=null && orders.size()>0){
            for(Order order:orders){
                orderIdSet.add(order.getContentId());
            }
        }
        for(Content content:contents){
            if(orderIdSet.contains(content.getContentId())){
                continue;
            }
            Content buyContent = new Content();
            buyContent.setContentId(content.getContentId());
            buyContent.setPrice(content.getPrice());
            buyContent.setDetail(content.getDetail());
            buyContent.setPicture(content.getPicture());
            buyContent.setTitle(content.getTitle());
            buyContent.setSummary(content.getSummary());
            buyContent.setHasBought(false);
            unBoughtContents.add(buyContent);
        }
        return unBoughtContents;
    }

    @Override
    public List<Content> getAllItemsForSeller(User user) {
        List<Content> contents = contentDAO.getAllContents();
        if(contents==null || contents.size()<1){
            return new ArrayList<Content>();
        }
        for(Content content: contents){
            if(contentHasSold(content)){
                content.setHasSold(true);
            }else{
                content.setHasSold(false);
            }
        }
        return contents;
    }

    private boolean contentHasSold(Content content){
        if(orderDAO.countOrderByContentId(content.getContentId())>0){
            return true;
        }return false;
    }
}
