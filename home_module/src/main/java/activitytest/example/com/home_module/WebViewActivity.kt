package activitytest.example.com.home_module


import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_web_item)
        val bundle = intent.extras
        val html = bundle?.getString("html")
        Log.d(TAG, html+"")
        val myWebView = findViewById<WebView>(R.id.webView)
        if (html != null) {
            myWebView.loadUrl(html)
        }
    }

    companion object {
        private val TAG = WebViewActivity::class.java.name
    }
}