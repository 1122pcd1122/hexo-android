package activitytest.example.com.roomdemo.project.lifecycle

import activitytest.example.com.roomdemo.databinding.ProjectFragmentBinding
import activitytest.example.com.roomdemo.home.utils.MonthUtil
import activitytest.example.com.roomdemo.main.database.entity.Blog
import activitytest.example.com.roomdemo.project.ProjectFragment
import activitytest.example.com.roomdemo.project.adapter.LogAdapter
import activitytest.example.com.roomdemo.project.viewModel.LogViewModel
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager

class ProjectLifeCycle(private val blogViewModel: LogViewModel, private val projectFragment: ProjectFragment,val projectFragmentBinding: ProjectFragmentBinding) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun listUpdateTime(){
        val blogInfoLiveData = blogViewModel.listUpdateTime()
        val mutableMapOf = mutableMapOf<Int, MutableList<Blog>>()
        blogInfoLiveData.observe(projectFragment, { list ->
            list.forEach {
                val updateTime:Int = MonthUtil.getYear(it.updateTime)
                //创建根据年份的的Map集合
                if (!mutableMapOf.containsKey(updateTime)){
                    mutableMapOf[updateTime] = mutableListOf()
                    Log.d("Project",updateTime.toString())
                }
            }

            list.forEach {
                val year = MonthUtil.getYear(it.updateTime)
                if (mutableMapOf.containsKey(year)){
                    val get = mutableMapOf[year]
                    get?.add(it)
                    Log.d("Project",it.blogTitle)
                }
            }

            val mutableListOf = mutableListOf<Int>()
            val keys = mutableMapOf.keys.asIterable()
            keys.forEach{
                mutableListOf.add(it)
            }

            projectFragmentBinding.recycleView.layoutManager = LinearLayoutManager(projectFragment.context)
            projectFragmentBinding.recycleView.adapter = projectFragment.context?.let { LogAdapter(mutableMap = mutableMapOf, it,mutableListOf) }


        })


    }

}
