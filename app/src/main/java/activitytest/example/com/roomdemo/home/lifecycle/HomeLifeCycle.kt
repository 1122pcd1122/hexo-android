package activitytest.example.com.roomdemo.home.lifecycle

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.home.HomeFragment
import activitytest.example.com.roomdemo.home.adapter.BlogAdapter
import activitytest.example.com.roomdemo.home.utils.DownAndParseBlog
import activitytest.example.com.roomdemo.home.viewmodel.BlogViewModel
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum
import activitytest.example.com.roomdemo.main.viewModel.ConfigurationViewModel
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeLifeCycle @SuppressLint("InflateParams") constructor(private val homeFragment: HomeFragment, private val blogViewModel: BlogViewModel, private val configurationViewModel: ConfigurationViewModel) : LifecycleObserver {
    private val view: View? = homeFragment.view
    private var recyclerView: RecyclerView? = null
    private var listHeadlines: TextView? = null
    private var blogUsername: TextView? = null
    private var introduces: TextView? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun initHomeInfo() {
        listHeadlines = view!!.findViewById(R.id.list_headlines)
        blogUsername = view.findViewById(R.id.blog_user_name)
        introduces = view.findViewById(R.id.blog_introduce)

        configurationViewModel.code!!.observe(homeFragment, { integer ->
            if (integer == ErrorCodeEnum.SUCCESS.errorCode) {
                Log.d(TAG, "网络获取...")
                Log.d(TAG, integer.toString())
                observerConfiguration(configurationViewModel.configurationNet)
            } else {
                Log.d(TAG, "数据库获取...")
                Log.d(TAG, integer.toString())
                observerConfiguration(configurationViewModel.configurationDb)
            }
        })
        recyclerView = view.findViewById(R.id.recycler_last_item)

        setRecycleViewAdapter()
    }

    private fun setRecycleViewAdapter(){
        val down= DownAndParseBlog(blogViewModel,homeFragment)
        down.listDownLoadUrls()
        down.listBlog().observe(homeFragment,  {
            recyclerView?.layoutManager = LinearLayoutManager(view?.context)
            recyclerView?.adapter = view?.context?.let { it1 -> BlogAdapter(it1,it) }

            GlobalScope.launch {
                down.insertInoDB(it)
            }

        })

    }

    private fun observerConfiguration(configurationLiveData: LiveData<Configuration?>?) {
        configurationLiveData!!.observe(homeFragment,  { configuration ->
            if (configuration != null) {
                listHeadlines?.text = configuration.listHeadlines
                blogUsername?.text = configuration.blog_username
                introduces?.text = configuration.introduce
            }
        })
    }


    companion object {
        val TAG = HomeLifeCycle::class.java.name
    }

    init {
        blogViewModel.init()
        configurationViewModel.init()
    }
}




