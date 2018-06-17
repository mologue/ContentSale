package com.mologue.contentSale.domain;

/**与数据库的`shoppingCar`对应的实体类*/
public class ShoppingCar {
    private long itemId;
    private String userName;
    private long contentId;
    private int amount;
    private String date;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
