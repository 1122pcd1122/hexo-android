package activitytest.example.com.home_module.home

import activitytest.example.com.home_module.home.HomeRepository.Companion.homeService
import activitytest.example.com.home_module.home.bean.ListArticle
import activitytest.example.com.network_module.RequestAction

import androidx.paging.PagingSource
import androidx.paging.PagingState

import kotlin.Exception


class HomePagingSource :PagingSource<Int,ListArticle>() {
    override fun getRefreshKey(state: PagingState<Int, ListArticle>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListArticle> {
        return try {

            homeService
            val responseResult = RequestAction.execute {
                homeService?.articleList()!!
            }

            val page = params.key ?: 1
            params.loadSize
            val listArticle = responseResult.info
            val prevKey = if (page > 1) page - 1 else null
            if (listArticle!!.isNotEmpty()) page + 1
            return LoadResult.Page(listArticle,prevKey,null)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }


}