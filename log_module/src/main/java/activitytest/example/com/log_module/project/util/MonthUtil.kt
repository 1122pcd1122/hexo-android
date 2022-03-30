package activitytest.example.com.log_module.project.util

import java.lang.NumberFormatException
import java.util.*

var MONTH_LIST: List<String> = listOf("JAN", "FEB", "MAR",
    "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")


fun changeNumberToChar(releaseVersion: String?): String? {
    requireNotNull(releaseVersion) { "ReleaseVersion cannot be null" }
    val strNumber = releaseVersion.substring(0, 2)
    var strMonth: String? = null
    try {
        strMonth = MONTH_LIST[strNumber.toInt()]
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        println("Parse month error........")
    }
    return strMonth + releaseVersion.substring(2)
}

fun changeStringToNumber(releaseVersion: String?): String? {
    requireNotNull(releaseVersion) { "ReleaseVersion cannot be null" }
    for (i in 1 .. MONTH_LIST.size) {
        if (releaseVersion.uppercase(Locale.getDefault()).startsWith(MONTH_LIST[i-1])) {
            return if (i < 10) {
                "0$i"
            } else i.toString() + ""
        }
    }
    return (-1).toString() + ""
}

fun changeNumToDigits(num: String?): String {
    requireNotNull(num) { "ReleaseVersion cannot be null" }

    return if (num.toInt() < 10){
        "0$num"
    }else{
        num.toString()
    }
}