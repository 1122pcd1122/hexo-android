package activitytest.example.com.log_moudle.project.viewModel


import activitytest.example.com.log_moudle.project.bean.ArticlesByYear
import activitytest.example.com.log_moudle.project.repository.LogRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class LogViewModel: ViewModel(){

    private val logRepository:LogRepository = LogRepository()

    /*
        根据年份获取文章列表
     */
    fun listArticlesByYear():LiveData<ArticlesByYear>{
        return logRepository.listBlogByYear()
    }

}


