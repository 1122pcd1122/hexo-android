package activitytest.example.com.base

class IP {
    companion object{
        var httpUrl:String = ""
            get() {
                field = if (isHttps){
                    "$httpsHeader$host:$port"
                }else{
                    "$httpHeader$host:$port"
                }
                return field
            }
        private const val host:String = "pcd11.top"

        private var port:String = ""
            get() {
                field = if (isHttps){
                    "443"
                }else{
                    "80"
                }
                return field
            }

        private const val httpHeader:String = "http://"
        private const val httpsHeader:String = "https://"

        private const val isHttps:Boolean = false
        val contentUrl = "$httpHeader$host:$port/blog/pcd/hexo"
    }
}