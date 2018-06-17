package com.mologue.contentSale.domain;

/**与数据库的`content`对应的实体类*/
public class Content {
    private int contentId;
    private String title;
    private Double price;
    private String picture;
    private String summary;
    private String detail;
    private String sellerName;

//    private String sellerName;
    private boolean hasBought;
    private boolean hasSold;
    private int buyNum;
    private double buyPrice;

    public Content(){}

    public Content(Double price, String title, String picture, String summary, String detail){
        this.price = price;
        this.title = title;
        this.picture = picture;
        this.summary = summary;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public boolean isHasBought() {
        return hasBought;
    }

    public void setHasBought(boolean hasBought) {
        this.hasBought = hasBought;
    }

    public boolean isHasSold() {
        return hasSold;
    }

    public void setHasSold(boolean hasSold) {
        this.hasSold = hasSold;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
