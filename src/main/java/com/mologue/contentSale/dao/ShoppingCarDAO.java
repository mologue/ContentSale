package com.mologue.contentSale.dao;

import com.mologue.contentSale.domain.ShoppingCar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
@Mapper
@Repository
public interface ShoppingCarDAO {
    @Select("select * from `shoppingCar` where userName=#{userName} and contentId=#{contentId}")
    @Results({
            @Result(property = "carItemId",column = "carItemId"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "amount",column = "amount"),
            @Result(property = "userName",column = "userName"),
    })
    ShoppingCar getShoppingItem(@Param("userName") String userName,@Param("contentId") long contentId);

    @Select("select * from `shoppingCar` where carItemId=#{itemId}")
    @Results({
            @Result(property = "carItemId",column = "carItemId"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "amount",column = "amount"),
            @Result(property = "userName",column = "userName"),
    })
    ShoppingCar getShoppingItemById(long itemId);

    @Select("select * from `shoppingCar` where userName=#{userName} order by date")
    @Results({
            @Result(property = "carItemId",column = "carItemId"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "amount",column = "amount"),
            @Result(property = "userName",column = "userName"),
    })
    List<ShoppingCar> getShoppingCarListForUser(String userName);

    @Insert("insert into `shoppingCar` (userName,contentId,amount,date) VALUES(#{userName},#{contentId},#{amount},#{date});")
    @Options(useGeneratedKeys = true, keyProperty = "carItemId")
    int addNewItemToShopingCar(ShoppingCar shoppingCar);

    @Update("update `shoppingCar` set amount=#{amount},date=#{date} where contentId=#{contentId}")
    void updateItemInShoppingCar(ShoppingCar shoppingCar);

    @Delete("delete from `shoppingCar` where carItemId=#{carItemId}")
    void deleteContentById(long carItemId);
}
