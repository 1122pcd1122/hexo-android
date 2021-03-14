package activitytest.example.com.roomdemo.project.viewModel

import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.database.dao.BlogDao
import activitytest.example.com.roomdemo.main.database.entity.Blog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class LogViewModel: ViewModel(){

    private val blogDao:BlogDao

    init {
        val instance = MyDatabase.instance
        blogDao = instance?.blogDao!!
    }

    fun listUpdateTime(): LiveData<List<Blog>> {
        return blogDao.queryBlog()
    }
}


