package activitytest.example.com.roomdemo.main.http;


import android.util.Log;

import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 客户端
 */
public class RetrofitClient {

    private static final String TAG  = HomeLifeCycle.class.getName ();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        Log.d ( TAG,"API对象加载完毕" );
        return retrofit.create(serviceClass);
    }


}
