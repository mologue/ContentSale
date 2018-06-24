package com.mologue.contentSale.domain;

/**与数据库的`shoppingCar`对应的实体类*/
public class ShoppingCar {
    private long carItemId;
    private String userName;
    private long contentId;
    private int amount;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCarItemId() {
        return carItemId;
    }

    public void setCarItemId(long itemId) {
        this.carItemId = itemId;
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
