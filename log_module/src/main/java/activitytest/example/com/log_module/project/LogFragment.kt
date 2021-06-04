package activitytest.example.com.log_module.project

import activitytest.example.com.log_moudle.R
import activitytest.example.com.log_moudle.databinding.LogFragmentBinding
import activitytest.example.com.log_module.project.viewModel.LogViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels


class LogFragment : Fragment() {

    private lateinit var logFragmentBinding: LogFragmentBinding
    private val logViewModel: LogViewModel by viewModels<LogViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        logFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.log_fragment, container, false)



        return logFragmentBinding.root
    }






}