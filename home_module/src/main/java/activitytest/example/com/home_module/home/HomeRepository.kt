package activitytest.example.com.home_module.home

import activitytest.example.com.base.IP
import activitytest.example.com.home_module.home.api.API
import activitytest.example.com.home_module.home.bean.ListArticle
import activitytest.example.com.home_module.home.bean.UserData

import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


class HomeRepository {

    companion object{

        //retrofit客户端
        private val retrofitClient by lazy {
           RetrofitClient().createRetrofitClient(IP.httpUrl)
        }
         val homeRepository by lazy {
            HomeRepository()
        }

         val homeService by lazy {
            retrofitClient?.create(API::class.java)
        }

        private const val PAGE_SIZE:Int = 2

    }

    /**
     * 所有文章
     */
    fun articleList(): Flow<PagingData<ListArticle>> {

        return Pager(
                config = PagingConfig(PAGE_SIZE),
                pagingSourceFactory = {
                    HomePagingSource()
                }
        ).flow


    }

    /**
     * 个人信息
     */
    suspend fun configuration(): ResponseResult<UserData> {

        return RequestAction.execute {
            homeService?.configuration()!!
        }

    }
}

