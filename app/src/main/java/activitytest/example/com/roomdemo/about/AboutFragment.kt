package activitytest.example.com.roomdemo.about

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.about.lifecycle.AboutLifeCycle
import activitytest.example.com.roomdemo.databinding.AboutFragmentBinding
import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum
import activitytest.example.com.roomdemo.main.viewModel.ConfigurationViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class AboutFragment : Fragment() {

    private val s: String = AboutFragment::class.java.name

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentAboutBinding:AboutFragmentBinding? = DataBindingUtil.inflate(inflater,R.layout.about_fragment,container,false)

        val configurationViewModel:ConfigurationViewModel = ViewModelProvider(this).get(ConfigurationViewModel::class.java)

        Log.d(s,"开始观察")

        configurationViewModel.init()
        configurationViewModel.getCode()?.observe(this.viewLifecycleOwner,  { it ->
            if (it == ErrorCodeEnum.SUCCESS.errorCode){
                configurationViewModel.configurationNet?.observe(this.viewLifecycleOwner, {
                    Log.d(s,"网络获取")

                    observerSelfInfo(fragmentAboutBinding,it)

                })
            }else if (it == ErrorCodeEnum.ERROR_API.errorCode || it == ErrorCodeEnum.NO_NETWORK.errorCode){
                configurationViewModel.configurationDb?.observe(this.viewLifecycleOwner,{
                    Log.d(s,"数据库获取")

                    observerSelfInfo(fragmentAboutBinding, it)

                })
            }
        })

        lifecycle.addObserver(AboutLifeCycle(fragmentAboutBinding,activity))

        return fragmentAboutBinding?.root
    }

    private fun observerSelfInfo(fragmentAboutBinding: AboutFragmentBinding?, it: Configuration?) {


        val blogNum:Int? = MyDatabase.instance?.blogDao?.queryBlogNum()
        val tags:Int? = MyDatabase.instance?.tagsDao?.queryTagNum()

        fragmentAboutBinding?.articleNum = blogNum
        fragmentAboutBinding?.tagsNum = tags
        fragmentAboutBinding?.configuration = it
    }


}