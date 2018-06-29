package com.mologue.contentSale.dao;

import com.mologue.contentSale.domain.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
@Mapper
@Repository
public interface OrderDAO {
    @Select("select * from `order` where userName=#{userName}")
//    @ResultMap("com.sangmo.dao.OrderMapper.order")
    @Results({
            @Result(id = true,property = "orderId",column = "orderId"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "date",column = "date"),
            @Result(property = "price",column = "price"),
            @Result(property = "amount",column = "amount")
    })
    List<Order> getAllOrderForUser(@Param("userName") String userName);

    @Select("select * from `order` where orderId=#{orderId}")
//    @ResultMap("com.sangmo.dao.OrderMapper.order")
    @Results({
            @Result(id = true,property = "orderId",column = "orderId"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "date",column = "date"),
            @Result(property = "price",column = "price"),
            @Result(property = "amount",column = "amount")
    })
    Order getOrderByOrderId(long orderId);

    @Select("select * from `order` where userName=#{userName} and contentId=#{contentId}")
//    @ResultMap("com.sangmo.dao.OrderMapper.order")
    @Results({
            @Result(id = true,property = "orderId",column = "orderId"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "date",column = "date"),
            @Result(property = "price",column = "price"),
            @Result(property = "amount",column = "amount")
    })
    List<Order> getOrderForUser(@Param("userName") String userName,@Param("contentId") long contentId);

    @Select("select count(*) from `order` where contentId=#{contentId}")
    int countOrderByContentId(int contentId);

    @Insert("insert into `order` (userName,contentId,title,picture,date,price,amount) VALUES (#{userName},#{contentId},#{title},#{picture},#{date},#{price},#{amount});")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void insertNewOrder(Order order);
}
