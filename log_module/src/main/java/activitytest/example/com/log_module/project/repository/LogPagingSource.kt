package activitytest.example.com.log_module.project.repository

import activitytest.example.com.log_module.project.bean.ArticleByYears
import activitytest.example.com.network_module.RequestAction
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class LogPagingSource:PagingSource<Int,ArticleByYears>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleByYears>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleByYears> {
        return try {
            val page =  1
            params.loadSize
            val responseResult = RequestAction.execute {
                LogRepository.logService?.log()!!
            }

            val listArticle = responseResult.info
            val prevKey = if (page > 1) page - 1 else null
            if (listArticle?.isNotEmpty() == true) page + 1
            return LoadResult.Page(listArticle!!,prevKey,null)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}