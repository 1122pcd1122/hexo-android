package activitytest.example.com.roomdemo.home.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object MonthUtil {
    private var parse: Date? = null

    fun getMonthEN(month: Int): String {
        when (month) {
            1 -> return "January"
            2 -> return "February"
            3 -> return "March;"
            4 -> return "April"
            5 -> return "May"
            6 -> return "June"
            7 -> return "July"
            8 -> return "August"
            9 -> return "September"
            10 -> return "October"
            11 -> return "November"
            12 -> return "December"
        }
        return ""
    }

    private fun getMonthCN(month: Int): String {
        when (month) {
            1 -> return "一月"
            2 -> return "二月"
            3 -> return "三月"
            4 -> return "四月"
            5 -> return "五月"
            6 -> return "六月"
            7 -> return "七月"
            8 -> return "八月"
            9 -> return "九月"
            10 -> return "十月"
            11 -> return "十一月"
            12 -> return "十二月"
        }
        return ""
    }

    /**
     * 获取博客的发布日期
     * @return 以 月份 日 年 来返回博客发送的日期
     */
    fun getNowDate(date: String): String {
        val updateTime = date.substring(5)
        @SuppressLint("SimpleDateFormat")
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            parse = simpleDateFormat.parse(updateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val calendar = Calendar.getInstance()
        calendar.time = parse!!
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        val year = calendar[Calendar.YEAR]
        return getMonthCN(calendar[Calendar.MONTH]) + " " + dayOfMonth + " " + year + "年"
    }

    fun getYear(date:String): Int {
        val updateTime = date.substring(5)
        @SuppressLint("SimpleDateFormat")
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            parse = simpleDateFormat.parse(updateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val calendar = Calendar.getInstance()
        calendar.time = parse!!
        return calendar[Calendar.YEAR]
    }
}