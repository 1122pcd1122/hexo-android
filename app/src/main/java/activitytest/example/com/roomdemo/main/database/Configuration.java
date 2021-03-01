package activitytest.example.com.roomdemo.main.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 配置文件表
 */
@Entity(tableName = "Configuration")
public class Configuration {

    @PrimaryKey()
    private int id;

    /**
     * 博客名
     */
    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    private String name;
    /**
     * 暗色图标
     */
    @ColumnInfo(name = "icon_night",typeAffinity = ColumnInfo.TEXT)
    private String icon_night;
    /**
     * 亮色图标
     */
    @ColumnInfo(name = "icon_white",typeAffinity = ColumnInfo.TEXT)
    private String icon_white;
    /**
     * 博主名字
     */
    @ColumnInfo(name = "blog_username",typeAffinity = ColumnInfo.TEXT)
    private String blog_username;
    /**
     * 博主介绍
     */
    @ColumnInfo(name = "introduce",typeAffinity = ColumnInfo.TEXT)
    private String introduce;
    /**
     * 列表标题
     */
    @ColumnInfo(name = "listHeadlines",typeAffinity = ColumnInfo.TEXT)
    private String listHeadlines;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setIcon_night(String icon_night) {
        this.icon_night = icon_night;
    }
    public String getIcon_night() {
        return icon_night;
    }

    public void setIcon_white(String icon_white) {
        this.icon_white = icon_white;
    }
    public String getIcon_white() {
        return icon_white;
    }

    public String getBlog_username() {
        return blog_username;
    }

    public void setBlog_username(String blog_username) {
        this.blog_username = blog_username;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getIntroduce() {
        return introduce;
    }

    public void setListHeadlines(String listHeadlines) {
        this.listHeadlines = listHeadlines;
    }
    public String getListHeadlines() {
        return listHeadlines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon_night='" + icon_night + '\'' +
                ", icon_white='" + icon_white + '\'' +
                ", blog_username='" + blog_username + '\'' +
                ", introduce='" + introduce + '\'' +
                ", listHeadlines='" + listHeadlines + '\'' +
                '}';
    }
}