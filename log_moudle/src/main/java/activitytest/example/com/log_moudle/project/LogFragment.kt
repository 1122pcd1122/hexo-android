package activitytest.example.com.log_moudle.project

import activitytest.example.com.log_moudle.R
import activitytest.example.com.log_moudle.databinding.LogFragmentBinding
import activitytest.example.com.log_moudle.project.lifecycle.ProjectLifeCycle
import activitytest.example.com.log_moudle.project.viewModel.LogViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlin.math.log


class LogFragment : Fragment() {

    private lateinit var logFragmentBinding: LogFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        logFragmentBinding = DataBindingUtil.inflate<LogFragmentBinding>(inflater, R.layout.log_fragment, container, false)

        return logFragmentBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val logViewModel = ViewModelProvider(this).get(LogViewModel::class.java)

        lifecycle.addObserver(ProjectLifeCycle(logViewModel,this,logFragmentBinding))


        super.onActivityCreated(savedInstanceState)
    }



}