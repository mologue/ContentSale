package com.mologue.contentSale.service;

import com.mologue.contentSale.dao.ContentDAO;
import com.mologue.contentSale.dao.ShoppingCarDAO;
import com.mologue.contentSale.dataDefine.ShoppingCarItem;
import com.mologue.contentSale.domain.Content;
import com.mologue.contentSale.domain.ShoppingCar;
import com.mologue.contentSale.service.serviceInterface.ShoppingCarService;
import com.mologue.contentSale.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanru_h on 2018/6/17
 */
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService{
    @Autowired
    private ShoppingCarDAO shoppingCarDAO;

    @Autowired
    private ContentDAO contentDAO;

    @Override
    public void addNewShoppingItem(ShoppingCar shoppingCar) {
        String userName=shoppingCar.getUserName();
        long contentId = shoppingCar.getContentId();
        ShoppingCar shoppingItem = shoppingCarDAO.getShoppingItem(userName,contentId);
        if(shoppingItem==null){
            shoppingCarDAO.addNewItemToShopingCar(shoppingCar);
        }else{
            int totalNum = shoppingItem.getAmount()+shoppingCar.getAmount();
            shoppingItem.setDate(DateUtil.date2String(new Date()));
            shoppingItem.setAmount(totalNum);
            shoppingCarDAO.updateItemInShoppingCar(shoppingItem);
        }
    }

    @Override
    public void deleteFromShoppingCar(long itemId) {
        shoppingCarDAO.deleteContentById(itemId);
    }

    @Override
    public void updateShoppingCarList(ShoppingCar shoppingCar) {
        shoppingCarDAO.updateItemInShoppingCar(shoppingCar);
    }

    @Override
    public List<ShoppingCarItem> getAllShoppingListForUser(String userName) {
        List<ShoppingCarItem> itemList = new ArrayList<>();
        List<ShoppingCar> shoppingCarList = shoppingCarDAO.getShoppingCarListForUser(userName);
        for(ShoppingCar shoppingCar:shoppingCarList){
            Content content = contentDAO.getContentById(shoppingCar.getContentId());
            ShoppingCarItem item = new ShoppingCarItem();
            item.setAmount(shoppingCar.getAmount());
            item.setContentId(shoppingCar.getContentId());
            item.setUserName(shoppingCar.getUserName());
            item.setCarItemId(shoppingCar.getCarItemId());
            item.setPrice(content.getPrice());
            item.setTitle(content.getTitle());
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public ShoppingCar getShoppingCarItem(long itemId) {
        return shoppingCarDAO.getShoppingItemById(itemId);
    }

}
