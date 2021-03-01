package activitytest.example.com.roomdemo.main.activity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;

import java.util.Objects;

import activitytest.example.com.roomdemo.R;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG  = WebViewActivity.class.getName ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_web_view );
        Bundle bundle = getIntent ().getExtras ();
        String html = Objects.requireNonNull ( bundle ).getString ( "html" );
        assert html != null;
        Log.d ( TAG,html );
        WebView myWebView = findViewById ( R.id.webView );
        String encodedHtml = Base64.encodeToString(html.getBytes(),
                Base64.NO_PADDING);
        myWebView.loadData(encodedHtml, "text/html", "base64");

    }
}