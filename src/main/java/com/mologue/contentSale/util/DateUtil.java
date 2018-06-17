package com.mologue.contentSale.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static String format = "yyyy-MM-dd HH:mm:ss";
    public static String date2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String strDate = sdf.format(date);
        return strDate;
    }
}
