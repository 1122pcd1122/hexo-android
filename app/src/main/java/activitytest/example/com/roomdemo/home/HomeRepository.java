package activitytest.example.com.roomdemo.home;

import android.util.ArraySet;
import android.util.Log;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import activitytest.example.com.roomdemo.home.bean.Root;
import activitytest.example.com.roomdemo.main.http.API;
import activitytest.example.com.roomdemo.main.http.RetrofitClient;
import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private static final String TAG  = HomeRepository.class.getName ();

    public static HomeRepository homeRepository;
    private final API blogIntroduce;

    public static HomeRepository getInstance(){
        if (homeRepository == null){
            homeRepository = new HomeRepository ();
        }
        return homeRepository;
    }

    public HomeRepository() {
        blogIntroduce = RetrofitClient.createService ( API.class );
    }


    public MutableLiveData<List<Root>> listBlogInfo(){

        MutableLiveData<List<Root>> mutableLiveData = new MutableLiveData<> ();
        Call<List<Root>> call = blogIntroduce.getCall ();
        call.enqueue ( new Callback<List<Root>> () {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                Log.d ( TAG,"成功" );
                Log.d ( TAG,response.body ().size ()+"" );
                if (response.isSuccessful ()){
                    Log.d ( TAG,"正在添加......" );
                    mutableLiveData.postValue ( response.body () );
                    Log.d ( TAG,"添加成功......" );
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                Log.d ( TAG,"失败" );
                Log.d ( TAG, Objects.requireNonNull ( t.getMessage () ) );
            }
        } );

        return mutableLiveData;
    }


    public MutableLiveData<Set<String>> downLoadBlogContent(List<String> blogContent) {
        MutableLiveData<Set<String>> contentMutableList = new MutableLiveData<> ();
        Set<String> contentList = new ArraySet<> ();

        for (int i = 0; i < blogContent.size (); i++) {
            String url = blogContent.get ( i );
            Call<ResponseBody> responseBodyCall = blogIntroduce.downLoadMarkDown ( url );
            int finalI = i;
            responseBodyCall.enqueue ( new Callback<ResponseBody> () {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.d ( TAG,"成功"+response.body ().toString () );
                        String string = response.body ().string ();
                        String html = new Markdown4jProcessor ().process ( string );
                        Log.d ( TAG,html );
                        contentList.add ( html );
                        if (blogContent.size ()-1 == finalI){
                            contentMutableList.postValue ( contentList );
                        }
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d ( TAG,"下载失败" );
                }
            } );

        }

        return contentMutableList;
    }
}
