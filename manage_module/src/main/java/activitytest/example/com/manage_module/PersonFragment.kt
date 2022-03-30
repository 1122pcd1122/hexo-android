package activitytest.example.com.manage_module


import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.manage_module.navigation.NavigationScreen
import activitytest.example.com.manage_module.screen.ManageScreen
import activitytest.example.com.manage_module.screen.SetScreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


@Route(path = "/manage_module/personfragment")
@ExperimentalCoilApi
class PersonFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return ComposeView(requireContext()).apply{
            setContent {
                val navHostController = rememberNavController()
                NavHost(navController = navHostController, startDestination = NavigationScreen.MANAGE().title){
                    composable(NavigationScreen.MANAGE().title) {
                        activity?.let { at -> ManageScreen(at,navHostController) }
                    }
                    composable(NavigationScreen.SET().title) {
                        SetScreen()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (TokenUtil.isEmptyByToken() == true){
            ARouter.getInstance().build(MyRouteTable.loginModule_MainActivity).navigation()
        }
    }





}