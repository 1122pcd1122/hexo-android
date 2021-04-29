package activitytest.example.com.person_moudle.about.lifecycle

import activitytest.example.com.person_moudle.databinding.AboutFragmentBinding
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AboutLifeCycle(private val fragmentAboutBinding: AboutFragmentBinding?) : LifecycleObserver {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun switchTheme(){
        val switch = fragmentAboutBinding?.switch1
        switch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


}
