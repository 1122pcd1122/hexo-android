package activitytest.example.com.log_moudle.project.lifecycle



import activitytest.example.com.log_moudle.databinding.LogFragmentBinding
import activitytest.example.com.log_moudle.project.LogFragment
import activitytest.example.com.log_moudle.project.adapter.LogAdapter
import activitytest.example.com.log_moudle.project.bean.ArticlesByYear
import activitytest.example.com.log_moudle.project.viewModel.LogViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager

class ProjectLifeCycle(private val logViewModel: LogViewModel, private val logFragment: LogFragment, val logFragmentBinding: LogFragmentBinding) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun listUpdateTime(){
        val listArticlesByYear = logViewModel.listArticlesByYear()

        listArticlesByYear.observe(logFragment.viewLifecycleOwner, {
            val recycleView = logFragmentBinding.recycleView
            val logAdapter = LogAdapter(logFragment.requireContext(), articlesByYear = it)
            recycleView.layoutManager = LinearLayoutManager(logFragment.context)
            recycleView.adapter = logAdapter
        })

    }

}
