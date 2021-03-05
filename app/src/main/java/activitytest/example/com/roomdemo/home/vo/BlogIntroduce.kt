package activitytest.example.com.roomdemo.home.vo

import java.io.Serializable

class BlogIntroduce {
    /**
     * 标题
     */
    var blogTitle: String? = null

    /**
     * 时间
     */
    var time: String? = null

    /**
     * html字符串
     */
    var html: String? = null

    /**
     * 标签
     */
    var tags: Array<String>? = null

    constructor() {}
    constructor(blogTitle: String?, time: String?, html: String?, tags: Array<String>) {
        this.blogTitle = blogTitle
        this.time = time
        this.html = html
        this.tags = tags
    }

    val tag: StringBuffer
        get() {
            val stringBuffer = StringBuffer()
            for (tag in tags!!) {
                stringBuffer.append(tag).append(" ")
            }
            return stringBuffer
        }
}