package activitytest.example.com.roomdemo.main.lifecycle

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.roomdemo.databinding.MainPopviewBinding
import activitytest.example.com.roomdemo.main.activity.MainActivity
import activitytest.example.com.roomdemo.main.adapter.RecyclerviewAdapter
import activitytest.example.com.roomdemo.main.viewModel.MainViewModel
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

/**
 * MainActivity的lifecycle层
 */
@Suppress("COMPATIBILITY_WARNING")
class MainActivityLifeCycle(
        /**
         * MainActivity
         */
        private val activity: MainActivity,
        /**
         * 主页面绑定
         */
        private val activityMainBinding: MainActivityBinding,
        /**
         * 弹出页绑定
         */
        private val popViewBinding: MainPopviewBinding?) : LifecycleObserver {


        private val tag = MainActivityLifeCycle::class.java.name

        /**
        * 弹出窗体
         */
         private var popupWindow: PopupWindow? = null

        /**
        * Configuration的ViewModel
         */
        private val mainViewModel: MainViewModel by lazy {
            ViewModelProvider(activity).get(MainViewModel::class.java)
        }

        /**
        * @return 返回true或false True为夜间模式 False为白天模式
        */
        private val isNight: Boolean
            get() = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        private val navController by lazy {
            val fragmentContainerView = activityMainBinding.fragmentContainer
            Navigation.findNavController(fragmentContainerView)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun initView() {

            //初始化控件
            val appBarLayout = activityMainBinding.appBarLayout
            //初始化弹出窗体
            showPopupWindow()
            activityMainBinding.menuIcon.setOnClickListener {
                Objects.requireNonNull(popupWindow)?.showAsDropDown(appBarLayout, 0,
                    0)
            }

            activityMainBinding.mainBtn.setOnClickListener {
                navController.navigate(R.id.aboutFragment)
            }

            mainViewModel.configuration().observe(activity,{
                setName(it.info.blog_name)
                if (isNight){
                    Glide.with(activity).load("http://49.232.6.89:8080/btn_White/night").into(activityMainBinding.mainBtn)
                }else{
                    Glide.with(activity).load("http://49.232.6.89:8080/btn_Night/white").into(activityMainBinding.mainBtn)
                }
            })

        }


    /**
     * 设置博客名
     */
    private fun setName(blogName: String?) {
        //设置博客标题
        activityMainBinding.blogName.text = blogName
    }


    /**
     * @return menu的点击事件响应 - 进入到对应的fragment中
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun menuClickListener(): RecyclerviewAdapter.TextViewClickListener {
        return object : RecyclerviewAdapter.TextViewClickListener {
            override fun clickListener(textView: View) {
                Log.d(tag, "被点击了")

                val fragment = textView as TextView
                val text = fragment.text
                when {
                    "Archives".contentEquals(text) -> {
                        navController.navigate(R.id.archivesFragment)
                    }
                    "Friends".contentEquals(text) -> {
                        navController.navigate(R.id.friendFragment)
                    }
                    "Projects".contentEquals(text) -> {
                        navController.navigate(R.id.projectFragment)
                    }
                    "About".contentEquals(text) -> {
                        navController.navigate(R.id.aboutFragment)
                    }
                    else -> {
                        navController.navigate(R.id.homeFragment)
                    }
                }
            }
        }
    }

    /**
     * 弹出menu菜单
     */
    @SuppressLint("ClickableViewAccessibility")
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun showPopupWindow() {
        val contentView = popViewBinding!!.root
        popupWindow = PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)
        val recyclerView: RecyclerView = contentView.findViewById(R.id.recycleView)
        val cd = ColorDrawable(-0)
        popupWindow!!.setBackgroundDrawable(cd)
        popupWindow!!.update()
        popupWindow!!.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
        popupWindow!!.isTouchable = true // 设置popupWindow可点击
        popupWindow!!.isOutsideTouchable = true // 设置popupWindow外部可点击
        popupWindow!!.isFocusable = true // 获取焦点
        popupWindow!!.setTouchInterceptor(OnTouchListener { _, event -> // 如果点击了popupWindow的外部，popupWindow也会消失
            if (event.action == MotionEvent.ACTION_OUTSIDE) {
                popupWindow!!.dismiss()
                return@OnTouchListener true
            }
            false
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val recyclerviewAdapter = RecyclerviewAdapter(activity, menuList, menuClickListener())
        recyclerView.adapter = recyclerviewAdapter
    }

    /**
     * @return 获取menu列表
     */
    private val menuList: List<String>
        get() {
            val menuList: MutableList<String> = ArrayList()
            menuList.add("Home")
            menuList.add("Archives")
            menuList.add("Friends")
            menuList.add("Projects")
            menuList.add("About")
            return menuList
        }


}