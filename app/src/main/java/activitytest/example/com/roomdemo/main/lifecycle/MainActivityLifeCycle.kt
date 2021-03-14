package activitytest.example.com.roomdemo.main.lifecycle

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.roomdemo.databinding.MainPopviewBinding
import activitytest.example.com.roomdemo.main.activity.MainActivity
import activitytest.example.com.roomdemo.main.adapter.RecyclerviewAdapter
import activitytest.example.com.roomdemo.main.adapter.RecyclerviewAdapter.TextViewClickListener
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum
import activitytest.example.com.roomdemo.main.viewModel.ConfigurationViewModel
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
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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



    private val navController by lazy {
        val fragmentContainerView = activityMainBinding.fragmentContainer
        Navigation.findNavController(fragmentContainerView)
    }
    /**
     * Configuration的ViewModel
     */
    private val configurationViewModel: ConfigurationViewModel = ViewModelProvider(activity).get(ConfigurationViewModel::class.java)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initView() {

        //初始化控件
        val appBarLayout = activityMainBinding.appBarLayout
        val imageButton = activityMainBinding.menuIcon
        //初始化弹出窗体
        showPopupWindow()
        imageButton.setOnClickListener {
            Objects.requireNonNull(popupWindow)?.showAsDropDown(appBarLayout, 0,
                    0)
        }
        configurationViewModel.getCode()!!.observe(activity, { integer ->
            if (integer == ErrorCodeEnum.SUCCESS.errorCode) {
                Log.d(tag, "网络获取...")
                observerConfiguration(configurationViewModel.configurationNet)
            } else {
                Log.d(tag, "数据库获取...")
                observerConfiguration(configurationViewModel.configurationDb)
            }
        })

        activityMainBinding.mainSelfInfo.setOnClickListener {
            navController.navigate(R.id.aboutFragment)
        }
    }

    /**
     * 观察configuration的变化
     * @param configurationLiveData Configuration
     */
    private fun observerConfiguration(configurationLiveData: LiveData<Configuration?>?) {
        configurationLiveData!!.observe(activity, Observer { configuration ->
            Log.d(tag, "观察中......")
            if (isNight) {
                if (configuration != null) {
                    if (configuration.icon_white == null) {
                        setUserIcon(R.drawable.main_icon_im_night)
                    } else {
                        setUserIcon(configuration.icon_white)
                    }
                }
            } else {
                if (configuration != null) {
                    if (configuration.icon_night == null) {
                        setUserIcon(R.drawable.main_icon_im_day)
                    } else {
                        setUserIcon(configuration.icon_night)
                    }
                }
            }
            setName(configuration?.name)
        })
    }

    /**
     * 设置博客名
     */
    private fun setName(blogName: String?) {
        //设置博客标题
        activityMainBinding.name = blogName
    }

    private fun setUserIcon(imageUrl: String?) {
        activityMainBinding.imageUrl = imageUrl
    }

    private fun setUserIcon(localImage: Int) {
        activityMainBinding.localImage = localImage
    }

    /**
     * @return 返回true或false True为夜间模式 False为白天模式
     */
    private val isNight: Boolean
        get() = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    /**
     * @return menu的点击事件响应 - 进入到对应的fragment中
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun menuClickListener(): TextViewClickListener {
        return object : TextViewClickListener {
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


    init {
        configurationViewModel.init()
    }
}