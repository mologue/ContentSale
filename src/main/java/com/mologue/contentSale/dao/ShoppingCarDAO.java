package com.mologue.contentSale.dao;

import com.mologue.contentSale.domain.ShoppingCar;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
public interface ShoppingCarDAO {
    @Select("select * from `shoppingCar` where userName=#{userName} order by date")
    @Results({
            @Result(property = "itemId",column = "itemId"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "amounnt",column = "amount"),
    })
    List<ShoppingCar> getShoppingCarListForUser(String userName);

    @Insert("insert into `shoppingCar` (userName,contentId,amount,date) VALUES(#{userName},#{contentId},#{amount},#{date});")
    @Options(useGeneratedKeys = true, keyProperty = "itemId")
    void addNewItemToShopingCar(ShoppingCar shoppingCar);

    @Update("update `shoppingCar` set amount=#{amount},date=#{date}")
    void updateItemInShoppingCar(ShoppingCar shoppingCar);

    @Delete("delete from `shoppingCar` where itemId=#{itemId}")
    void deleteContentById(long itemId);
}
