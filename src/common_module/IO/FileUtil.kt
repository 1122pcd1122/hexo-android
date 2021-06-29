package common_module.IO


import java.io.BufferedReader
import java.io.FileReader

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/4/8-11:03
 */
class FileUtil {

    /*
        读取文件
     */
    fun readFile(path:String):String{
        val bufferedReader = BufferedReader(FileReader(path))

        var readLine = bufferedReader.readLine()
        val stringBuilder = StringBuilder()
        while (readLine != null){
            stringBuilder.append(readLine.toString())
            readLine = bufferedReader.readLine()
        }
        println(stringBuilder.toString())
        bufferedReader.close()

        return stringBuilder.toString()
    }


    /*
          获取markDown内容
       */
    private fun readMarkDownFile(path:String):String{
        val bufferedReader = BufferedReader(FileReader(path))
        val stringBuilder = StringBuilder()
        var stringLine = bufferedReader.readLine()
        while (stringLine != null){
            stringBuilder.append(stringLine)
            stringLine = bufferedReader.readLine()
        }
        bufferedReader.close()
        return stringBuilder.toString()
    }
}