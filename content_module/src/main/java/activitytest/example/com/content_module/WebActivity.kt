package activitytest.example.com.content_module

import activitytest.example.com.base.IP
import activitytest.example.com.content_module.databinding.ActivityWebBinding
import android.annotation.SuppressLint
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/content_module/content")
class WebActivity : AppCompatActivity() {

    private lateinit var webBinding: ActivityWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webBinding = DataBindingUtil.setContentView(this,R.layout.activity_web)
        // 设置允许加载混合内容
        webBinding.webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        webBinding.webView.settings.databaseEnabled = true
        webBinding.webView.settings.javaScriptEnabled = true
        webBinding.webView.webViewClient = object : WebViewClient(){
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError?) {
                handler.proceed()
            }
        }


        val url = intent.getStringExtra("url")
        webBinding.webView.loadUrl(IP.contentUrl+url)



    }
}