package activitytest.example.com.home_moudle.home.utils

import java.lang.StringBuilder

object TagsUtil {

     fun getTag(tag:List<String>):String{
        val stringBuilder = StringBuilder()
        tag.forEach {
            stringBuilder.append(it).append(" ")
        }

        return stringBuilder.toString()
    }
}