package activitytest.example.com.roomdemo.home.viewmodel;

import android.util.Log;

import java.util.List;
import java.util.Set;
import activitytest.example.com.roomdemo.home.HomeRepository;
import activitytest.example.com.roomdemo.home.bean.Root;
import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle;
import activitytest.example.com.roomdemo.main.MainRepository;
import activitytest.example.com.roomdemo.main.activity.MainActivity;
import activitytest.example.com.roomdemo.main.database.Configuration;
import activitytest.example.com.roomdemo.main.database.MyDatabase;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BlogViewModel extends ViewModel {
    private static final String TAG  = HomeLifeCycle.class.getName ();

    private MutableLiveData<List<Root>> mutableLiveData;
    private HomeRepository homeRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        homeRepository = HomeRepository.getInstance ();
        mutableLiveData = homeRepository.listBlogInfo ();
    }

    /**
     * 返回博客信息
     * @return 博客信息
     */
    public LiveData<List<Root>> getBlogInfoLiveData() {
        return mutableLiveData;
    }


    /**
     * 返回博文的链接
     * @param blogContent 博文的链接
     * @return 博文内容
     */
    public MutableLiveData<Set<String>> setBlogContent(List<String> blogContent){
        return homeRepository.downLoadBlogContent ( blogContent );
    }

}
