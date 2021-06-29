package common_module.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author peichendong
 * @Description TODO
 * @date 2021/4/12-20:35
 */
object TimeUtil {

    private fun stringToDate(dateStr: Date): Date {

        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStr)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.parse(format)
    }

    /*
        获取年份
     */
    fun getYear(dateStr: Date): Int {
        val date = stringToDate(dateStr)
        val instance = Calendar.getInstance()
        instance.time = date
        return instance[Calendar.YEAR]
    }

    /*
        获取月份
     */
    fun getMonth(dateStr: Date): Int {
        val date = stringToDate(dateStr)
        val instance = Calendar.getInstance()
        instance.time = date
        return instance[Calendar.MONTH] + 1
    }

    /*
        获取一个月中的第几天
     */
    fun getDay(dateStr: Date): Int {
        val date = stringToDate(dateStr)
        val instance = Calendar.getInstance()
        instance.time = date
        return instance[Calendar.DAY_OF_MONTH]
    }

    /**
     * 将 Date 解析为 yyyy/MM/DD
     */
    fun parseDate(date: Date):String?{
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        return simpleDateFormat.format(date)
    }

}