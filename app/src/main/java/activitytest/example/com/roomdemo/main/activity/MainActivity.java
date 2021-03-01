package activitytest.example.com.roomdemo.main.activity;

import activitytest.example.com.roomdemo.R;
import activitytest.example.com.roomdemo.databinding.ActivityMainBinding;
import activitytest.example.com.roomdemo.databinding.PopviewBinding;
import activitytest.example.com.roomdemo.main.ConfigurationService;
import activitytest.example.com.roomdemo.main.database.ConfigurationDao;
import activitytest.example.com.roomdemo.main.database.MyDatabase;
import activitytest.example.com.roomdemo.main.lifecycle.MainActivityLifeCycle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName ();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView ( this, R.layout.activity_main );

        PopviewBinding popviewBinding = DataBindingUtil.bind ( LayoutInflater.from (  this).inflate ( R.layout.popview,null ));
        //注册声明周期的观察者
        getLifecycle ().addObserver ( new MainActivityLifeCycle ( this,activityMainBinding ,popviewBinding) );


        Intent intent = new Intent (MainActivity.this, ConfigurationService.class );
        startService ( intent );
    }

}