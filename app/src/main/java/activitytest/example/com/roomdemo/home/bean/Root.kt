package activitytest.example.com.roomdemo.home.bean

class Root {
    var name: String? = null
    var path: String? = null
    var sha: String? = null
    var size = 0
    var url: String? = null
    var html_url: String? = null
    var git_url: String? = null
    var download_url: String? = null
    var type: String? = null
    private var _links: Link? = null
    fun set_links(_links: Link?) {
        this._links = _links
    }

    fun get_links(): Link? {
        return _links
    }
}