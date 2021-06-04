package activitytest.example.com.person_module.about

import activitytest.example.com.person_module.R
import activitytest.example.com.person_module.about.viewModel.PersonViewModel
import activitytest.example.com.person_module.databinding.AboutFragmentBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide


class PersonFragment : Fragment() {



    private val personViewModel:PersonViewModel by viewModels()
    private lateinit var personFragmentBinding: AboutFragmentBinding

    @SuppressLint("LongLogTag")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        personFragmentBinding = DataBindingUtil.inflate<AboutFragmentBinding>(inflater, R.layout.about_fragment, container, false)



        return personFragmentBinding.root
    }

    override fun onStart() {
        super.onStart()

        personViewModel.tagNum().observe(this){
            val data = it?.data
            personFragmentBinding.tagsNum = data?.num
        }

        personViewModel.articleNum().observe(this){
            val data = it?.data
            personFragmentBinding.articleNum = data?.articleNum
        }

        personViewModel.userInfo().observe(this){
            val data = it?.data
            val userData = data?.userData
            personFragmentBinding.configuration = userData

            Glide.with(this).load(userData?.userIcon).into(personFragmentBinding?.icon!!)
        }

    }



}