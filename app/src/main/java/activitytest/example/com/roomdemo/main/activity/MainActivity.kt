package activitytest.example.com.roomdemo.main.activity

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.roomdemo.databinding.MainPopviewBinding
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
        val activityMainBinding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        val popViewBinding: MainPopviewBinding? = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.main_popview, null))
        //注册声明周期的观察者
        lifecycle.addObserver(MainActivityLifeCycle(this, activityMainBinding, popViewBinding))
        val intent = Intent(this@MainActivity, ConfigurationService::class.java)
        startService(intent)
    }
}