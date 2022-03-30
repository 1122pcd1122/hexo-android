package activitytest.example.com.label_module


import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.label_module.databinding.FriendFragmentBinding
import activitytest.example.com.label_module.ui.LabelScreen
import activitytest.example.com.label_module.ui.theme.AppTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route


@Route(path = "/label_module/labelfragment")
class FriendFragment : Fragment() {


    private lateinit var friendFragmentBinding: FriendFragmentBinding


    @ExperimentalMaterialApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        friendFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.friend_fragment, container, false)


        //如果token为空表示token过期而被删除掉
        if (TokenUtil.isEmptyByToken() == true){
            TokenUtil.navToLogin()
        }


        friendFragmentBinding.composeView.setContent {
            AppTheme {
                LabelScreen()
            }
        }


        return friendFragmentBinding.root
    }

}