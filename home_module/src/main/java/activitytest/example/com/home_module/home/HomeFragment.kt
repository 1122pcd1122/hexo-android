package activitytest.example.com.home_module.home

import activitytest.example.com.componentbase.ContentServiceFactory
import activitytest.example.com.home_module.R
import activitytest.example.com.home_module.databinding.HomeFragmentBinding
import activitytest.example.com.home_module.home.adapter.BlogAdapter
import activitytest.example.com.home_module.home.viewmodel.BlogViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@Route(path = "/home_module/homefragment")
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

        val blogAdapter = BlogAdapter {
            val bundle = Bundle()
            bundle.putString("html", it)
            if (activity != null) {
                ContentServiceFactory.getFragmentService()?.startActivity(requireContext(), requireActivity(), bundle, "进入WebActivity")
            }
        }

        val recyclerView = homeFragmentBinding.reItem
        val progressBar = homeFragmentBinding.progressBar
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = blogAdapter
        lifecycleScope.launch {
           blogViewModel.articleList().collect {
               blogAdapter.submitData(it)
           }
       }

        blogAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    /**
     * 配置用户信息
     */
    private fun setUserInfo() {
        blogViewModel.configuration().observe(this.viewLifecycleOwner, { value->
            //UserInfo
            //列表名
            homeFragmentBinding.also {
                it.listTitle = "列表"
                it.userData = value
                Log.d("home_module","UserData观察中...")
            }
        })
    }


}