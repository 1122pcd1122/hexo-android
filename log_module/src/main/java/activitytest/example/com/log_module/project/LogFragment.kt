package activitytest.example.com.log_module.project

import activitytest.example.com.log_module.R
import activitytest.example.com.log_module.databinding.LogFragmentBinding
import activitytest.example.com.log_module.project.adapter.OneAdapter
import activitytest.example.com.log_module.project.viewModel.LogViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.coroutines.launch


@Route(path = "/log_module/logfragment")
class LogFragment : Fragment() {

    private lateinit var logFragmentBinding: LogFragmentBinding
    private val logViewModel: LogViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        logFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.log_fragment, container, false)


        logFragmentBinding.recycleView.layoutManager = LinearLayoutManager(context)
        val oneAdapter = OneAdapter()
        logFragmentBinding.recycleView.adapter = oneAdapter


        logViewModel.log().observe(this@LogFragment.viewLifecycleOwner) {
            lifecycleScope.launch {
                oneAdapter.submitData(it)
            }
        }

        return logFragmentBinding.root
    }
}