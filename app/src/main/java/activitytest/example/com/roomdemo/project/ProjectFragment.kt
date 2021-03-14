package activitytest.example.com.roomdemo.project

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.ProjectFragmentBinding
import activitytest.example.com.roomdemo.project.lifecycle.ProjectLifeCycle
import activitytest.example.com.roomdemo.project.viewModel.LogViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class ProjectFragment : Fragment() {

    private lateinit var projectFragmentBinding:ProjectFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        projectFragmentBinding = DataBindingUtil.inflate<ProjectFragmentBinding>(inflater, R.layout.project_fragment, container, false)

        return projectFragmentBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val logViewModel = ViewModelProvider(this).get(LogViewModel::class.java)

        lifecycle.addObserver(ProjectLifeCycle(logViewModel,this,projectFragmentBinding))


        super.onActivityCreated(savedInstanceState)
    }



}