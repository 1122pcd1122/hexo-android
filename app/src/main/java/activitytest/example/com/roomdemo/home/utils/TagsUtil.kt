package activitytest.example.com.roomdemo.home.utils

import java.lang.StringBuilder

object TagsUtil {

     fun getTag(tag:Array<String>):String{
        val stringBuilder = StringBuilder()
        tag.forEach {
            stringBuilder.append(it).append(" ")
        }

        return stringBuilder.toString()
    }
}