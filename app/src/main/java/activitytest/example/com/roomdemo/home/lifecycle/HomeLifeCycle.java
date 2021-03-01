package activitytest.example.com.roomdemo.home.lifecycle;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import activitytest.example.com.roomdemo.R;
import activitytest.example.com.roomdemo.home.bean.Root;
import activitytest.example.com.roomdemo.home.utils.MonthUtil;
import activitytest.example.com.roomdemo.home.viewmodel.BlogViewModel;
import activitytest.example.com.roomdemo.home.HomeFragment;
import activitytest.example.com.roomdemo.home.adapter.BlogAdapter;
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce;
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum;
import activitytest.example.com.roomdemo.main.database.Configuration;
import activitytest.example.com.roomdemo.main.viewModel.ConfigurationViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class  HomeLifeCycle implements LifecycleObserver {

    private final View view;
    private final BlogViewModel blogViewModel;
    private final ConfigurationViewModel configurationViewModel;
    private final HomeFragment homeFragment;
    private RecyclerView recyclerView;
    private static final String TAG  = HomeLifeCycle.class.getName ();
    private TextView list_headlines;
    private TextView blog_userName;
    private TextView introduces;

    @SuppressLint("InflateParams")
    public HomeLifeCycle(HomeFragment homeFragment, BlogViewModel blogViewModel, ConfigurationViewModel configurationViewModel) {
        this.view = homeFragment.getView ();
        this.blogViewModel = blogViewModel;
        this.homeFragment = homeFragment;
        this.configurationViewModel =configurationViewModel;
        blogViewModel.init ();
        configurationViewModel.init ();
    }


    @OnLifecycleEvent ( Lifecycle.Event.ON_START )
    public void initHomeInfo(){

        list_headlines = view.findViewById ( R.id.list_headlines );
        blog_userName = view.findViewById ( R.id.blog_user_name );
        introduces = view.findViewById ( R.id.blog_introduce );

        configurationViewModel.getCode ().observe ( homeFragment, new Observer<Integer> () {
            @Override
            public void onChanged(Integer integer) {
                if (integer.equals (ErrorCodeEnum.SUCCESS.getErrorCode ())){
                    Log.d ( TAG,"网络获取..." );
                    Log.d ( TAG,integer.toString () );
                    observerConfiguration ( configurationViewModel.getConfigurationNet () );
                }else {
                    Log.d ( TAG,"数据库获取..." );
                    Log.d ( TAG,integer.toString () );
                    observerConfiguration ( configurationViewModel.getConfigurationDb () );
                }
            }
        } );


        recyclerView = view.findViewById ( R.id.recycler_last_item );
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext ()));
        getBlogList ();
    }


    public void observerConfiguration(LiveData<Configuration> configurationLiveData){
        configurationLiveData.observe ( homeFragment, new Observer<Configuration> () {
            @Override
            public void onChanged(Configuration configuration) {
                list_headlines.setText ( configuration.getListHeadlines () );
                blog_userName.setText ( configuration.getBlog_username () );
                introduces.setText ( configuration.getIntroduce () );
            }
        } );

    }

    /**
     * 获取博客的文章
     */
    public void getBlogList(){
        blogViewModel.init ();
        blogViewModel.getBlogInfoLiveData ().observe (homeFragment , new Observer<List<Root>> () {
            @Override
            public void onChanged(List<Root> roots) {
                listBlogIntroduce ( roots );
            }
        } );
    }


    public void listBlogIntroduce(List<Root> roots){
        List<String>  downloadUrlList = new ArrayList<> ();
        for (int i = 0; i < roots.size (); i++) {
            String download_url = roots.get ( i ).getDownload_url ();
            downloadUrlList.add ( download_url );
        }
        List<BlogIntroduce> blogIntroduceList = new ArrayList<> ();
        blogViewModel.setBlogContent ( downloadUrlList ).observe ( homeFragment, new Observer<Set<String>> () {
            @Override
            public void onChanged(Set<String> strings) {
                for (String html : strings) {
                    Document parse = Jsoup.parse ( html );
                    //解析标题
                    String title = parseTitle ( parse );
                    //解析时间
                    String time = parseUpDateTime ( parse );

                    String[] tags = parseCategories ( parse );

                    blogIntroduceList.add ( new BlogIntroduce ( title, MonthUtil.getNowDate (time),html  ,tags));
                }
                recyclerView.setAdapter ( new BlogAdapter ( view.getContext (),blogIntroduceList ) );
            }
        } );
    }

    private String[] parseCategories(Document parse) {
        Elements tags = parse.getElementsByTag ( "code" );
        Element element = tags.get ( 0 );
        String[] split = element.text ().split ( "\n" );
        Log.d ( TAG,"标签字符串:"+element.text () );
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split ( "-" );
            split[i] = split1[1];
        }
        Log.d ( TAG, "标签"+Arrays.toString ( split ) );
        return split;
    }

    /**
     * @param parse 文档
     * @return 时间
     */
    private String parseUpDateTime(Document parse) {
        Elements h2 = parse.getElementsByTag ( "h2" );
        Element timeElement = h2.get ( 0 );
        return timeElement.text ();
    }

    /**
     * @param parse 文档
     * @return 标题
     */
    private String parseTitle(Document parse) {
        Elements h1 = parse.getElementsByTag ( "p" );
        Element titleElement = h1.get ( 0 );
        String title = titleElement.text ();
        return title.substring ( 6 ,title.length () - 6);
    }
}
