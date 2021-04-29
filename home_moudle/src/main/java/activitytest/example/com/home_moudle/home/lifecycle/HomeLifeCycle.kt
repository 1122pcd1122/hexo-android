package activitytest.example.com.home_moudle.home.lifecycle

import activitytest.example.com.home_moudle.R
import activitytest.example.com.home_moudle.home.HomeFragment
import activitytest.example.com.home_moudle.home.adapter.BlogAdapter
import activitytest.example.com.home_moudle.home.viewmodel.BlogViewModel
import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeLifeCycle
@SuppressLint("InflateParams")
constructor(private val homeFragment: HomeFragment, private val blogViewModel: BlogViewModel) : LifecycleObserver {
    private val view: View? = homeFragment.view
    private lateinit var recyclerView: RecyclerView
    private lateinit var listHeadlines: TextView
    private lateinit var name: TextView
    private lateinit var signature: TextView

    companion object {
        val TAG: String = HomeLifeCycle::class.java.name
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun initHomeInfo() {
        listHeadlines = view!!.findViewById(R.id.list_headlines)
        name = view.findViewById(R.id.blog_user_name)
        signature = view.findViewById(R.id.blog_introduce)
        recyclerView = view.findViewById(R.id.recycler_last_item)


        setConfiguration()

        setRecycleViewAdapter()
    }


    private fun setConfiguration() {

        blogViewModel.configuration().observe(homeFragment.viewLifecycleOwner, {
            //列表名
            listHeadlines.text = "列表"

            name.text = it.info.name

            signature.text = it.info.signature
        })
    }

    private fun setRecycleViewAdapter(){
        blogViewModel.articleList().observe(homeFragment.viewLifecycleOwner,{
            recyclerView.layoutManager = LinearLayoutManager(homeFragment.context)
            val let = homeFragment.context?.let { it1 -> BlogAdapter(it1, it) }
            recyclerView.adapter = let
        })

    }
}




