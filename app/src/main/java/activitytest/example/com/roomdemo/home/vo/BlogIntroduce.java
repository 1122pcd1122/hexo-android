package activitytest.example.com.roomdemo.home.vo;



public class BlogIntroduce {


    /**
     * 标题
     */
    private String blogTitle;
    /**
     * 时间
     */
    private String time;
    /**
     * html字符串
     */
    private String html;

    /**
     * 标签
     */
    private String[] tags;

    public BlogIntroduce() {
    }

    public BlogIntroduce(String blogTitle, String time, String html, String[] tags) {
        this.blogTitle = blogTitle;
        this.time = time;
        this.html = html;
        this.tags = tags;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public StringBuffer getTag(){
        StringBuffer stringBuffer = new StringBuffer ();
        for (String tag : tags) {
            stringBuffer.append ( tag ).append ( " " );
        }

        return stringBuffer;
    }
}
