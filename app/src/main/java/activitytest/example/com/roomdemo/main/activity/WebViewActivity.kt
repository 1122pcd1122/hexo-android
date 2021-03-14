package activitytest.example.com.roomdemo.main.activity

import activitytest.example.com.roomdemo.R
import android.os.Bundle
import android.util.Base64
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
        val encodedHtml = Base64.encodeToString(html?.toByteArray(),
                Base64.NO_PADDING)
        myWebView.loadData(encodedHtml, "text/html", "base64")
    }

    companion object {
        private val TAG = WebViewActivity::class.java.name
    }
}