package activitytest.example.com.roomdemo.main.http;

import java.util.List;

import activitytest.example.com.roomdemo.home.bean.Root;
import activitytest.example.com.roomdemo.main.database.Configuration;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 *
 */
public interface API {

    @GET("repos/1122pcd1122/MyNotes/contents/blog_post?ref=master")
    Call<List<Root>> getCall();

    @GET
    Call<ResponseBody> downLoadMarkDown(@Url String url);

    @GET()
    Call<Configuration> getConfiguration(@Url String url);
}
