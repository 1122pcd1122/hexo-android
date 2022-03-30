package activitytest.example.com.manage_module


import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import coil.annotation.ExperimentalCoilApi
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/manage_module/manage")
class MainActivity : AppCompatActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.main_frame,PersonFragment(),"")
    }

}