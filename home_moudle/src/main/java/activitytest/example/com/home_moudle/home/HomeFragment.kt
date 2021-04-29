package activitytest.example.com.home_moudle.home

import activitytest.example.com.home_moudle.R
import activitytest.example.com.home_moudle.home.lifecycle.HomeLifeCycle
import activitytest.example.com.home_moudle.home.viewmodel.BlogViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class HomeFragment : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val blogViewModel = ViewModelProvider(this).get(BlogViewModel::class.java)
        lifecycle.addObserver(HomeLifeCycle(this, blogViewModel))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }



}