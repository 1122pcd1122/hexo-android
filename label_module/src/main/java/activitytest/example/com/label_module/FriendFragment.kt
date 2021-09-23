package activitytest.example.com.label_module


import activitytest.example.com.friend_module.R
import activitytest.example.com.label_module.adapter.ContentAdapter
import activitytest.example.com.label_module.adapter.LabelsAdapter
import activitytest.example.com.friend_module.databinding.FriendFragmentBinding
import activitytest.example.com.label_module.viewModel.LabelsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route


@Route(path = "/label_module/labelfragment")
class FriendFragment : Fragment() {


    private lateinit var friendFragmentBinding: FriendFragmentBinding
    private val labelsViewModel by viewModels<LabelsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        friendFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.friend_fragment, container, false)



        return friendFragmentBinding.root
    }


    override fun onStart() {
        super.onStart()


        val reTags = friendFragmentBinding.reTags
        reTags.layoutManager = LinearLayoutManager(context).apply {
            this.orientation = LinearLayoutManager.HORIZONTAL
        }

        labelsViewModel.listLabels().observe(this.viewLifecycleOwner) { LabelsBean ->
            val labelsAdapter = LabelsAdapter(LabelsBean){ tag ->
                val reTagsContent = friendFragmentBinding.reTagsContent
                reTagsContent.layoutManager = LinearLayoutManager(context)
                labelsViewModel.articles(tag).observe(this.viewLifecycleOwner){
                    reTagsContent.adapter = ContentAdapter(it ?: emptyList())
                }
            }
            reTags.adapter = labelsAdapter
        }


    }

}