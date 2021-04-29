package activitytest.example.com.home_moudle.home.vo

data class BlogIntroduce(
        /**
         * 标题
         */
        val blogTitle: String,

        /**
         * 时间
         */
        val time: String,

        /**
         * html字符串
         */
        val html: String,

        /**
         * 标签
         */
        val tags: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BlogIntroduce

        if (blogTitle != other.blogTitle) return false
        if (time != other.time) return false
        if (html != other.html) return false
        if (!tags.contentEquals(other.tags)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = blogTitle.hashCode()
        result = 31 * result + (time.hashCode())
        result = 31 * result + html.hashCode()
        result = 31 * result + tags.contentHashCode()
        return result
    }
}
