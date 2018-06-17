package com.mologue.contentSale.service.serviceInterface;

import com.mologue.contentSale.domain.Content;
import com.mologue.contentSale.domain.User;

import java.util.List;

/**
 * Created by wanru_h on 2018/6/16
 */
public interface ContentService {
    Content getContent(String title,Double price,String picture,String summary,String detail);

    Content getContentById(long id);

    void inserContent(Content content);

    void updateContent(Content content);

    void deleteContentById(long contentId);

    List<Content> getAllContents();

    List<Content> getAllItemsForBuyer(User user);

    List<Content> getAllUnboughtItemsForBuyer(User user);

    List<Content> getAllItemsForSeller(User user);
}
