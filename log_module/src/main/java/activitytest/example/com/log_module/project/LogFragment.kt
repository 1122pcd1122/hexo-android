package activitytest.example.com.log_module.project

import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.log_module.R
import activitytest.example.com.log_module.databinding.LogFragmentBinding
import activitytest.example.com.log_module.project.ui.Logs
import activitytest.example.com.log_module.project.ui.theme.AppTheme
import activitytest.example.com.log_module.project.viewModel.LogViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.coroutines.launch


@Route(path = "/log_module/logfragment")
class LogFragment : Fragment() {

    private lateinit var logFragmentBinding: LogFragmentBinding



    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        logFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.log_fragment, container, false)



        //如果token为空表示token过期而被删除掉
        if (TokenUtil.isEmptyByToken() == true){
            TokenUtil.navToLogin()
        }

        logFragmentBinding.composeView.setContent {
            AppTheme {
                Logs()
            }
        }

        return logFragmentBinding.root
    }
}