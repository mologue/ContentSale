package com.mologue.contentSale.dao;

import com.mologue.contentSale.domain.Content;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wanru_h on 2018/6/16
 */
@Mapper
@Repository
public interface ContentDAO {

    @Select("select * from content where title=#{arg0} and price=#{arg1} and picture=#{arg2} and summary=#{arg3} and detail=#{arg4}")
    @Results({
            @Result(id = true,property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "detail",column = "detail"),
            @Result(property = "price",column = "price"),
            @Result(property = "sellerName",column = "sellerName"),
    })
    Content getContent(String title, Double price, String picture, String summary, String detail);

    @Select("select * from content where contentId=#{contentId}")
//    @ResultMap("com.sangmo.dao.ContentMapper.content")
    @Results({
            @Result(id = true,property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "detail",column = "detail"),
            @Result(property = "price",column = "price"),
            @Result(property = "sellerName",column = "sellerName"),
    })
    Content getContentById(long contentId);


    @Select("select * from content")
//    @ResultMap("com.sangmo.dao.ContentMapper.content")
    @Results({
            @Result(id = true,property = "contentId",column = "contentId"),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "detail",column = "detail"),
            @Result(property = "price",column = "price"),
            @Result(property = "sellerName",column = "sellerName"),
    })
    List<Content> getAllContents();


    @Insert("insert into content (price,title,picture,summary,detail,sellerName) VALUES(#{price},#{title},#{picture},#{summary},#{detail},#{sellerName});")
    @Options(useGeneratedKeys = true, keyProperty = "contentId")
    void addContent(Content content);

    @Update("update content set price=#{price},title=#{title},picture=#{picture},summary=#{summary},detail=#{detail} where contentId=#{contentId}")
    void updateContent(Content content);

    @Delete("delete from content where contentId=#{contentId}")
    void deleteContentById(long id);

}
