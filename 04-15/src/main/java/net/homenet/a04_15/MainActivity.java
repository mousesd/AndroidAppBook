package net.homenet.a04_15;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Button button;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = this.findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebBrowserClient());
        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
        webView.loadUrl("file:///android_asset/www/sample.html");

        final EditText editText = this.findViewById(R.id.editText);
        button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = editText.getText().toString();
                if (!urlString.isEmpty())
                    webView.loadUrl(urlString);
            }
        });
    }

    final class JavaScriptMethods {
        JavaScriptMethods() { }

        @android.webkit.JavascriptInterface
        public void clickOnFace() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    button.setText(R.string.open_after_click);
                    webView.loadUrl("javascript:changeFace()");
                }
            });
        }
    }

    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }
    }
}
