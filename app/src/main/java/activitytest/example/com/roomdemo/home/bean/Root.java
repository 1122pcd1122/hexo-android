package activitytest.example.com.roomdemo.home.bean;

public class Root {
    private String name;

    private String path;

    private String sha;

    private int size;

    private String url;

    private String html_url;

    private String git_url;

    private String download_url;

    private String type;

    private Link _links;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPath(String path){
        this.path = path;
    }
    public String getPath(){
        return this.path;
    }
    public void setSha(String sha){
        this.sha = sha;
    }
    public String getSha(){
        return this.sha;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setHtml_url(String html_url){
        this.html_url = html_url;
    }
    public String getHtml_url(){
        return this.html_url;
    }
    public void setGit_url(String git_url){
        this.git_url = git_url;
    }
    public String getGit_url(){
        return this.git_url;
    }
    public void setDownload_url(String download_url){
        this.download_url = download_url;
    }
    public String getDownload_url(){
        return this.download_url;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void set_links(Link _links){
        this._links = _links;
    }
    public Link get_links(){
        return this._links;
    }
}
