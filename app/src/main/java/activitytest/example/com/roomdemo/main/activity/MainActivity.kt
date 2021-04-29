package activitytest.example.com.roomdemo.main.activity


import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.roomdemo.databinding.MainPopviewBinding
import activitytest.example.com.roomdemo.main.lifecycle.MainActivityLifeCycle
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityBinding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        val popviewBinding = DataBindingUtil.bind<MainPopviewBinding>(LayoutInflater.from(this).inflate(R.layout.main_popview, null))
        lifecycle.addObserver(MainActivityLifeCycle(this,mainActivityBinding,popviewBinding))

    }
}