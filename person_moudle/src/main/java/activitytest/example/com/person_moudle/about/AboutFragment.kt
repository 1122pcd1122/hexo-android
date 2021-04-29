package activitytest.example.com.person_moudle.about

import activitytest.example.com.person_moudle.R
import activitytest.example.com.person_moudle.about.bean.ConfigurationBean
import activitytest.example.com.person_moudle.about.lifecycle.AboutLifeCycle
import activitytest.example.com.person_moudle.about.viewModel.PersonViewModel
import activitytest.example.com.person_moudle.databinding.AboutFragmentBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide



class AboutFragment : Fragment() {

    private val s: String = AboutFragment::class.java.name

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    @SuppressLint("LongLogTag")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentAboutBinding: AboutFragmentBinding? = DataBindingUtil.inflate(inflater, R.layout.about_fragment,container,false)

        val personViewModel:PersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        val configuration = personViewModel.configuration()
        configuration.observe(this.viewLifecycleOwner, {
                fragmentAboutBinding?.configuration = it.info

                Log.d("----------------------------555555555555-------------------------------",it.info.user_Icon)
                fragmentAboutBinding?.icon?.let { it1 ->
                Glide.with(this)
                        .load("http://49.232.6.89:8080/userIcon/icon")
                        .centerCrop()
                        .into(it1)
            }
        })

        lifecycle.addObserver(AboutLifeCycle(fragmentAboutBinding))

        personViewModel.articleNum().observe(viewLifecycleOwner,{
            fragmentAboutBinding?.articleNum = it.articleNum
        })

        personViewModel.tagNum().observe(viewLifecycleOwner,{
            fragmentAboutBinding?.tagsNum = it.num
        })

        return fragmentAboutBinding?.root
    }




}