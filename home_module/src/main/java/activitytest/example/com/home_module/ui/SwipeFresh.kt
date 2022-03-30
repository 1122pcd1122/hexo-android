package activitytest.example.com.home_module.ui

import activitytest.example.com.home_module.home.viewmodel.BlogViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun ContentReFresh(content:@Composable () -> Unit){
    val blogViewModel:BlogViewModel = viewModel()
    val isRefreshing = blogViewModel.isRefreshIng.observeAsState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value!!),
        onRefresh = { blogViewModel.refresh() },
    ) {
        content()
    }

}