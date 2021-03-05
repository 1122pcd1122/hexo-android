package activitytest.example.com.roomdemo.home.viewmodel

import activitytest.example.com.roomdemo.home.HomeRepository
import activitytest.example.com.roomdemo.home.bean.Root
import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlogViewModel : ViewModel() {
    private var rootLiveData: MutableLiveData<List<Root?>?>? = null
    private var homeRepository: HomeRepository? = null
    fun init() {
        if (rootLiveData != null) {
            return
        }
        homeRepository = HomeRepository.instance
        this.rootLiveData = homeRepository?.listBlogInfo()
    }

    /**
     * 返回博客信息
     * @return 博客信息
     */
    val blogInfoLiveData: LiveData<List<Root?>?>?
        get() = rootLiveData

    /**
     * 返回博文的链接
     * @param blogContent 博文的链接
     * @return 博文内容
     */
    fun listBlogContent(blogContent: List<String?>): MutableLiveData<Set<String>> {
        return homeRepository!!.downLoadBlogContent(blogContent)
    }

    companion object {
        private val TAG = HomeLifeCycle::class.java.name
    }
}