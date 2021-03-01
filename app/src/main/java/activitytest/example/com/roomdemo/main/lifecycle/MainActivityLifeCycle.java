

package activitytest.example.com.roomdemo.main.lifecycle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.roomdemo.R;
import activitytest.example.com.roomdemo.databinding.ActivityMainBinding;
import activitytest.example.com.roomdemo.databinding.PopviewBinding;
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum;
import activitytest.example.com.roomdemo.main.activity.MainActivity;
import activitytest.example.com.roomdemo.main.adapter.RecyclerviewAdapter;
import activitytest.example.com.roomdemo.main.database.Configuration;
import activitytest.example.com.roomdemo.main.viewModel.ConfigurationViewModel;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * MainActivity的lifecycle层
 */
public class MainActivityLifeCycle implements LifecycleObserver {
    private final String TAG = MainActivityLifeCycle.class.getName ();

    /**
     * 主页面绑定
     */
    private final ActivityMainBinding activityMainBinding;
    /**
     * 弹出页绑定
     */
    private final PopviewBinding popviewBinding;
    /**
     * MainActivity
     */
    private final MainActivity activity;
    /**
     * 弹出窗体
     */
    private PopupWindow popupWindow;
    /**
     * Configuration的ViewModel
     */
    private final ConfigurationViewModel configurationViewModel;

    /**
     * 构造器
     * @param mainActivity MainActivity
     * @param activityMainBinding 主页绑定类
     * @param popviewBinding 弹出页绑定类
     */
    public MainActivityLifeCycle(MainActivity mainActivity, ActivityMainBinding activityMainBinding, PopviewBinding popviewBinding) {
        this.activity = mainActivity;
        this.activityMainBinding = activityMainBinding;
        this.popviewBinding = popviewBinding;
        configurationViewModel = new ViewModelProvider ( mainActivity ).get ( ConfigurationViewModel.class );
        configurationViewModel.init ();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @OnLifecycleEvent ( Lifecycle.Event.ON_CREATE )
    public void initView(){

        //初始化控件
        AppBarLayout appBarLayout = activityMainBinding.appBarLayout;
        ImageButton imageButton = activityMainBinding.menuIcon;
        //初始化弹出窗体
        showPopupWindow();

        imageButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull ( popupWindow ).showAsDropDown(appBarLayout, 0,
                        0);
            }
        } );

        configurationViewModel.getCode ().observe ( activity, new Observer<Integer> () {
            @Override
            public void onChanged(Integer integer) {
                if (integer.equals ( ErrorCodeEnum.SUCCESS.getErrorCode () )){
                    Log.d ( TAG,"网络获取..." );
                    observerConfiguration ( configurationViewModel.getConfigurationNet () );
                }else {
                    Log.d ( TAG,"数据库获取..." );
                    observerConfiguration ( configurationViewModel.getConfigurationDb () );
                }
            }
        } );

    }

    /**
     * 观察configuration的变化
     * @param configurationLiveData Configuration
     */
    public void observerConfiguration(LiveData<Configuration> configurationLiveData){
        configurationLiveData.observe ( activity, new Observer<Configuration> () {
            @Override
            public void onChanged(Configuration configuration) {
                Log.d ( TAG,"观察中......" );
                if (isNight ()){
                    if (configuration.getIcon_white () == null){
                        setUserIcon ( R.drawable.git_white );
                    }else {
                        setUserIcon ( configuration.getIcon_white () );
                    }
                }else {
                    if (configuration.getIcon_night () == null){
                        setUserIcon ( R.drawable.git_night );
                    }else {
                        setUserIcon ( configuration.getIcon_night () );
                    }
                }

                setName ( configuration.getName () );
            }
        } );

    }


    /**
     * 设置博客名
     */
    private void setName(String blogName) {
        //设置博客标题
        activityMainBinding.setBlogName ( blogName );
    }

    private void setUserIcon(String imageUrl){
        activityMainBinding.setImageUrl ( imageUrl );
    }

    private void setUserIcon(int localImage){

        activityMainBinding.setLocalImage ( localImage );
    }

    /**
     * @return 返回true或false True为夜间模式 False为白天模式
     */
    private boolean isNight(){
        return AppCompatDelegate.getDefaultNightMode () == AppCompatDelegate.MODE_NIGHT_YES;
    }





    /**
     * @return menu的点击事件响应 - 进入到对应的fragment中
     */
    @OnLifecycleEvent ( Lifecycle.Event.ON_CREATE )
    public RecyclerviewAdapter.TextViewClickListener menuClickListener(){

         return new RecyclerviewAdapter.TextViewClickListener () {

             @Override
            public void clickListener(View textView) {
                Log.d ( TAG,"被点击了" );
                FragmentContainerView fragmentContainerView = activityMainBinding.fragmentContainer;
                NavController navController = Navigation.findNavController ( fragmentContainerView );
                TextView fragment = (TextView) textView;
                CharSequence text = fragment.getText ();

                if ("Archives".contentEquals ( text )) {
                    navController.navigate ( R.id.archivesFragment );
                } else if ("Friends".contentEquals ( text )) {
                    navController.navigate ( R.id.friendFragment );
                } else if ("Projects".contentEquals ( text )) {
                    navController.navigate ( R.id.projectFragment );
                } else if ("About".contentEquals ( text )) {
                    navController.navigate ( R.id.aboutFragment );
                } else {
                    navController.navigate ( R.id.homeFragment );
                }
            }
        };
    }


    /**
     * 弹出menu菜单
     */
    @OnLifecycleEvent ( Lifecycle.Event.ON_CREATE )
    private void showPopupWindow() {

        View contentView = popviewBinding.getRoot ();
        popupWindow = new PopupWindow (contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true );

        RecyclerView recyclerView = contentView.findViewById ( R.id.recycleView );

        ColorDrawable cd = new ColorDrawable(-0 );
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.update();
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setTouchable(true); // 设置popupWindow可点击
        popupWindow.setOutsideTouchable(true); // 设置popupWindow外部可点击
        popupWindow.setFocusable(true); // 获取焦点

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果点击了popupWindow的外部，popupWindow也会消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager (activity));
        RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter ( activity, getMenuList (), menuClickListener () );
        recyclerView.setAdapter ( recyclerviewAdapter );
    }

    /**
     * @return 获取menu列表
     */
    private List<String> getMenuList() {
        List<String> menuList = new ArrayList<> ();
        menuList.add ( "Home" );
        menuList.add ( "Archives" );
        menuList.add (  "Friends");
        menuList.add ( "Projects" );
        menuList.add ( "About" );

        return menuList;
    }

}
