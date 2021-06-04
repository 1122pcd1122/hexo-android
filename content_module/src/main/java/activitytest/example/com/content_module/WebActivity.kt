package activitytest.example.com.content_module

import activitytest.example.com.content_module.databinding.ActivityWebBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.databinding.DataBindingUtil

class WebActivity : AppCompatActivity() {

    private lateinit var webBinding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webBinding = DataBindingUtil.setContentView(this,R.layout.activity_web)
        val bundle = intent.extras
        val html = bundle?.getString("html")
        if (html != null) {
            webBinding.webView.loadUrl(html)
        }
    }
}