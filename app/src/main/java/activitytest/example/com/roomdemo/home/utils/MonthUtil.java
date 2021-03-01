package activitytest.example.com.roomdemo.home.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthUtil {

    private static Date parse;

    public static String getMonthEN(int month){
        switch (month)
        {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March;";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }

        return "";
    }
    public static String getMonthCN(int month){
        switch (month)
        {
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
        }

        return "";
    }
    /**
     * 获取博客的发布日期
     * @return 以 月份 日 年 来返回博客发送的日期
     */
    public static String getNowDate(String date){
        String updateTime = date.substring ( 5);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        try {
            parse = simpleDateFormat.parse ( updateTime );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        Calendar calendar = Calendar.getInstance ();
        calendar.setTime ( parse );
        int dayOfMonth = calendar.get ( Calendar.DAY_OF_MONTH );
        int year = calendar.get ( Calendar.YEAR );
        return MonthUtil.getMonthCN ( calendar.get ( Calendar.MONTH ) ) + " " + dayOfMonth + " " +year+"年";
    }



}
