package activitytest.example.com.categories_module

import activitytest.example.com.categories_module.databinding.FragmentCategoriesBinding
import activitytest.example.com.categories_module.nav.NavigationScreen
import activitytest.example.com.categories_module.ui.ArticleScreen
import activitytest.example.com.categories_module.ui.item.CategoriesArea
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import activitytest.example.com.categories_module.ui.theme.AppTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alibaba.android.arouter.facade.annotation.Route


@Route(path = "/categories_module/categoriesFragment")
class CategoriesFragment : Fragment() {

    private lateinit var categoriesBinding: FragmentCategoriesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

       categoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container,false)



        return categoriesBinding.root
    }

    @ExperimentalMaterialApi
    override fun onStart() {
        super.onStart()

        categoriesBinding.categoriesContainer.setContent {
            AppTheme {
                val navController = rememberNavController()
                val categoriesViewModel:CategoriesViewModel = viewModel()
                NavHost(navController = navController, startDestination = NavigationScreen.CategoriesScreen().route){
                    composable(NavigationScreen.CategoriesScreen().route) {
                        CategoriesArea(navController,categoriesViewModel)
                    }
                    composable(NavigationScreen.ArticleScreen().route) {
                        ArticleScreen(categoriesViewModel)
                    }
                }
            }
        }
    }
}