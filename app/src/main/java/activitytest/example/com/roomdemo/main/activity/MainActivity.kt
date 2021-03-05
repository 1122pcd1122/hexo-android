package activitytest.example.com.roomdemo.main.activity

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.ActivityMainBinding
import activitytest.example.com.roomdemo.databinding.PopviewBinding
import activitytest.example.com.roomdemo.main.ConfigurationService
import activitytest.example.com.roomdemo.main.lifecycle.MainActivityLifeCycle
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val popViewBinding: PopviewBinding? = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.popview, null))
        //注册声明周期的观察者
        lifecycle.addObserver(MainActivityLifeCycle(this, activityMainBinding, popViewBinding))
        val intent = Intent(this@MainActivity, ConfigurationService::class.java)
        startService(intent)
    }
}