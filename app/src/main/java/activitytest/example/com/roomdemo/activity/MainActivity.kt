package activitytest.example.com.roomdemo.activity


import activitytest.example.com.componentbase.*
import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.roomdemo.databinding.MainPopviewBinding
import activitytest.example.com.roomdemo.adapter.RecyclerviewAdapter
import activitytest.example.com.roomdemo.viewModel.APPViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import java.util.ArrayList




class MainActivity : AppCompatActivity() {


    //获取主app的viewModel
    private val appViewModel by viewModels<APPViewModel>()

    private var popViewBinding:MainPopviewBinding? = null


    private val popupWindow:PopupWindow by lazy {
        PopupWindow(popViewBinding?.root, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false).also {
            initPopWindow(it){
                val recycleView = popViewBinding?.recycleView
                recycleView?.layoutManager = LinearLayoutManager(this)
                recycleView?.adapter = RecyclerviewAdapter(this,menuList,menuClickListener())
            }
        }
    }


    private var mainActivityBinding: MainActivityBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        popViewBinding = DataBindingUtil.bind<MainPopviewBinding>(LayoutInflater.from(this).inflate(R.layout.main_popview, null))

        HomeServiceFactory.getFragmentService()?.newFragment(this,R.id.fragment_container,supportFragmentManager,null,"HomeFragment")

    }

    override fun onStart() {
        super.onStart()

        //弹出菜单
        mainActivityBinding?.menuIcon?.setOnClickListener {
            showPopWindow(popupWindow)
        }

        //配置博客的名字和button的图片
        appViewModel.configuration().observe(this, {
            val data = it?.data
            val userData = data?.userData
            setName(userData?.blogName)
            Glide.with(this).load("http://192.168.43.196:8080/btn_night/night").into(mainActivityBinding?.mainBtn!!)
        })

        mainActivityBinding?.mainBtn?.setOnClickListener{
            PersonServiceFactory.getFragmentService()?.replaceFragment(this@MainActivity,R.id.fragment_container, supportFragmentManager,null,"PersonFragment")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    /**
     * 弹出menu菜单
     */
    private fun initPopWindow(popupWindow: PopupWindow, showReView: () -> Unit) {
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.isOutsideTouchable = true
        popupWindow.isTouchable = true
        popupWindow.isFocusable = true
        showReView()
    }

    /**
     * 弹出 或者 关闭 PopWindow
     */
    private fun showPopWindow(popupWindow: PopupWindow) {

        if (!popupWindow.isShowing){
            popupWindow.showAsDropDown(mainActivityBinding?.appBarLayout)
        }else{
            popupWindow.dismiss()
        }

    }


    private fun menuClickListener(): RecyclerviewAdapter.TextViewClickListener {
        return object : RecyclerviewAdapter.TextViewClickListener {
            override fun clickListener(textView: View) {

                val fragment = textView as TextView
                val text = fragment.text
                when {

                    "Editor".contentEquals(text) -> {
                        EditorServiceFactory.getFragmentService()?.replaceFragment(this@MainActivity,R.id.fragment_container,supportFragmentManager,null,"EditorFragment")
                        Log.d("app_module", "导航->archivesFragment")
                    }
                    "Friends".contentEquals(text) -> {
                        FriendModuleFactory.getFragmentService()?.replaceFragment(this@MainActivity,R.id.fragment_container,supportFragmentManager,null,"FriendFragment")
                        Log.d("app_module", "导航->friendFragment")
                    }
                    "Log".contentEquals(text) -> {
                        LogServiceFactory.getFragmentService()?.replaceFragment(this@MainActivity,R.id.fragment_container,supportFragmentManager,null,"LogFragment")
                        Log.d("app_module", "导航->projectFragment")
                    }
                    "Person".contentEquals(text) -> {
                        PersonServiceFactory.getFragmentService()?.replaceFragment(this@MainActivity,R.id.fragment_container, supportFragmentManager,null,"PersonFragment")
                        Log.d("app_module", "导航->aboutFragment")
                    }
                    else -> {
                        HomeServiceFactory.getFragmentService()?.replaceFragment(this@MainActivity,R.id.fragment_container,supportFragmentManager,null,"HomeFragment")
                        Log.d("app_module", "导航->homeFragment")
                    }
                }
            }
        }
    }


    /**
     * 设置博客名
     */
    private fun setName(blogName: String?) {
        //设置博客标题
        mainActivityBinding?.blogName?.text = blogName
    }

    /**
     * @return 获取menu列表
     */
    private val menuList: List<String>
        get() {
            val menuList: MutableList<String> = ArrayList()
            menuList.add("Home")
            menuList.add("Editor")
            menuList.add("Friends")
            menuList.add("Log")
            menuList.add("Person")
            return menuList
        }



}