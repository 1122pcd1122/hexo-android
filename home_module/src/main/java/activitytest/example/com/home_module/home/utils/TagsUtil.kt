package activitytest.example.com.home_module.home.utils

import java.lang.StringBuilder

object TagsUtil {

     fun getTag(tag: String?):String{
        val stringBuilder = StringBuilder()
         tag?.split(",")?.forEach {
             stringBuilder.append(it).append(" ")
         }

        return stringBuilder.substring(1,stringBuilder.length-2)
    }
}