package activitytest.example.com.home_module.home

import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.home_module.R
import activitytest.example.com.home_module.databinding.HomeFragmentBinding
import activitytest.example.com.home_module.ui.Articles
import activitytest.example.com.home_module.ui.ContentReFresh
import activitytest.example.com.home_module.ui.Signature
import activitytest.example.com.home_module.ui.theme.AppTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route



@Route(path = "/home_module/homefragment")
class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: HomeFragmentBinding

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        homeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)


        //如果token为空表示token过期而被删除掉
        if (TokenUtil.isEmptyByToken() == true) {
            TokenUtil.navToLogin()
        }


        setContent()


        return homeFragmentBinding.root
    }


    /**
     * 配置用户信息
     */
    @ExperimentalMaterialApi
    private fun setContent() {
        homeFragmentBinding.homeContent.setContent {
            AppTheme {

            ContentReFresh {


                Column(modifier = Modifier.background(Color(0xFFF8F8FF))) {

                    Signature()

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp))

                    Articles()
                }


            }

            }
        }
    }




}