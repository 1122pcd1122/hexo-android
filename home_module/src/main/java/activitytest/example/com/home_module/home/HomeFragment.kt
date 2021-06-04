package activitytest.example.com.home_module.home

import activitytest.example.com.home_module.R
import activitytest.example.com.home_module.databinding.HomeFragmentBinding
import activitytest.example.com.home_module.home.adapter.BlogAdapter
import activitytest.example.com.home_module.home.viewmodel.BlogViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager


class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: HomeFragmentBinding

    private val blogViewModel by viewModels<BlogViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        homeFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container,false)


        setRecycleViewAdapter()
        setUserInfo()
        return homeFragmentBinding.root
    }

    /**
     * 设置recycleView的内容
     */
    private fun setRecycleViewAdapter(){
        blogViewModel.articleList().observe(this.viewLifecycleOwner, { value ->

            //获取ListArticleInfo
            val data = value?.data
            if (data != null){
                Log.d("home_module", "获取到${data::class.java.name}的内容:${data.message}")
            }

            //初始化适配器
            val blogAdapter = data?.let { BlogAdapter(requireContext(),activity, it.listArticle) }
            val linearLayoutManager = LinearLayoutManager(homeFragmentBinding.root.context)
            homeFragmentBinding.recyclerLastItem.apply {
                this.layoutManager = linearLayoutManager
                this.adapter = blogAdapter
            }
        })

    }

    /**
     * 配置用户信息
     */
    private fun setUserInfo() {
        blogViewModel.configuration().observe(this.viewLifecycleOwner, { value->


            //UserInfo
            val data = value?.data

            //列表名
            homeFragmentBinding.also {
                it.listTitle = "列表"
                it.userData = data?.userData
                Log.d("home_module","UserData观察中...")
                Log.d("home_module", data?.message.toString())
            }
        })
    }


}