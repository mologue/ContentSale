package com.mologue.contentSale.dao;

import com.mologue.contentSale.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by wanru_h on 2018/6/16
 */

@Mapper
@Repository
public interface UserDAO {
    @Select("select count(*) from user where userName=#{userName} and password=#{password}")
    int getUserNum(@Param("userName") String userName,@Param("password") String password);

    @Select("select userType from user where userName=#{userName}")
    int getUserTypeByName(String userName);

    @Select("select * from user where userName=#{userName}")
    @Results({
//            @Result(id = true,property = "userId",column = "userId"),
            @Result( id=true, property = "userName",column = "userName"),
            @Result(property = "userType",column = "userType")
    })
    User getUserByName(String userName);

    @Insert("insert into `user` (userName,password,userType) VALUES (#{userName},#{password},#{userType});")
    void registerNewUser(User user);
}
