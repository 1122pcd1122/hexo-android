package activitytest.example.com.roomdemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import activitytest.example.com.roomdemo.main.database.MyDatabase;
import activitytest.example.com.roomdemo.main.http.API;
import activitytest.example.com.roomdemo.main.http.RetrofitClient;


public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate ();
        context = getApplicationContext ();
        myDatabase = MyDatabase.getInstance ();
        api = RetrofitClient.createService ( API.class );
    }

    private static API api;
    private static MyDatabase myDatabase;

    public static MyDatabase getMyDatabase() {
        return myDatabase;
    }

    public static API getApi() {
        return api;
    }

    public static Context getContext() {
        return context;
    }


}
