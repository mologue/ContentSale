package com.mologue.contentSale.dataDefine;

/**对应 业务层结算购物车时的交易信息*/
public class TradeInfo {
    private long contentId;
    private int number;

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int num) {
        this.number = num;
    }
}
