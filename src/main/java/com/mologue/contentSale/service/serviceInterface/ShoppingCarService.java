package com.mologue.contentSale.service.serviceInterface;

import com.mologue.contentSale.dataDefine.ShoppingCarItem;
import com.mologue.contentSale.domain.ShoppingCar;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
public interface ShoppingCarService {
    void addNewShoppingItem(ShoppingCar shoppingCar);
    void deleteFromShoppingCar(long itemId);
    void updateShoppingCarList(ShoppingCar shoppingCar);
    List<ShoppingCarItem> getAllShoppingListForUser(String userName);
}
