package activitytest.example.com.app.activity


import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.roomdemo.databinding.MainPopviewBinding
import activitytest.example.com.app.adapter.RecyclerviewAdapter
import activitytest.example.com.app.navigation.Nav
import activitytest.example.com.app.navigation.NavName
import activitytest.example.com.app.viewModel.APPViewModel
import activitytest.example.com.base.MyRouteTable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MainActivity : AppCompatActivity() {


    //获取主app的viewModel
    private val appViewModel by viewModels<APPViewModel>()

    private var popViewBinding:MainPopviewBinding? = null
    private val nav = Nav(this, null)
    private val recyclerView:RecyclerView by lazy {
        popViewBinding!!.recycleView.also {
            it.layoutManager = LinearLayoutManager(this)
        }
    }
    private var navNameList:List<NavName> = Nav.defaultNavNameList()

    //初始化窗口 并设置点击事件
    private val popupWindow:PopupWindow by lazy {
        PopupWindow(popViewBinding?.root, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false).also { window ->
            initPopWindow(window){
                recyclerView.adapter = RecyclerviewAdapter(this, navNameList){
                    val fragment = it as TextView
                    val text = fragment.text
                    Log.d("app_module",text.toString())


                    nav.nav(text as String)

                    val supportFragmentManager = supportFragmentManager
                    val beginTransaction = supportFragmentManager.beginTransaction()
                    beginTransaction.add(R.id.fragment_container,nav.nav(text as String)!!)
                }
            }
        }
    }


    private var mainActivityBinding: MainActivityBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        popViewBinding = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.main_popview, null))


//        //初始化HomeFragment
//        HomeServiceFactory.getFragmentService()?.newFragment(this,R.id.fragment_container,supportFragmentManager,null,"HomeFragment")

        val navigation:Fragment = ARouter.getInstance().build("/home_module/homefragment").navigation() as Fragment

        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fragment_container,navigation)
    }



    override fun onStart() {
        super.onStart()

        initViewContent()
    }

    /**
     * 设置视图中的内容
     */
    private fun initViewContent() {
        //弹出菜单
        mainActivityBinding?.menuIcon?.setOnClickListener {
            showPopWindow(popupWindow)
        }

        //配置博客的名字和button的图片
        appViewModel.configuration().observe(this, {
            val data = it
            mainActivityBinding?.blogName?.text = data?.blogName
            Glide.with(this).load(it?.userIcon).apply(RequestOptions.circleCropTransform()).into(mainActivityBinding?.mainBtn!!)

        })

        updateNavName()

        mainActivityBinding?.mainBtn?.setOnClickListener {
            ARouter.getInstance().build(MyRouteTable.manageModule_MainActivity).navigation()
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



    private fun updateNavName(){
        appViewModel.navNameList().observe(this,{
            nav.updateNavName(it)
            navNameList = it
        })
    }





}