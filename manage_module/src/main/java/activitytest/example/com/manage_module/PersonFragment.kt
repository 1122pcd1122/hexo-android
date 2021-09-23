package activitytest.example.com.manage_module


import activitytest.example.com.manage_module.databinding.AboutFragmentBinding
import activitytest.example.com.manage_module.viewModel.PersonViewModel


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide


@Route(path = "/manage_module/personfragment")
class PersonFragment : Fragment() {



    private val personViewModel: PersonViewModel by viewModels()
    private lateinit var personFragmentBinding: AboutFragmentBinding

    @SuppressLint("LongLogTag")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        personFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.about_fragment, container, false)



        return personFragmentBinding.root
    }

    override fun onStart() {
        super.onStart()

        personViewModel.tagNum().observe(this){
            personFragmentBinding.tagsNum = it
        }

        personViewModel.articleNum().observe(this){
            val data = it
            personFragmentBinding.articleNum = data
            personFragmentBinding.logNum = data
        }

        personViewModel.userInfo().observe(this){
            personFragmentBinding.configuration = it

            Glide.with(this).load(it?.userIcon).into(personFragmentBinding.icon)
        }

    }



}