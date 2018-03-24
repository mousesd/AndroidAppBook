package net.homenet.challenge08;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout urlLayout;
    private Button menuButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlidingAnimationListener slidingAnimationListener = new SlidingAnimationListener();
        final Animation upsideAnim = AnimationUtils.loadAnimation(this, R.anim.translate_upside);
        final Animation downsideAnim = AnimationUtils.loadAnimation(this, R.anim.translate_downside);
        upsideAnim.setAnimationListener(slidingAnimationListener);
        downsideAnim.setAnimationListener(slidingAnimationListener);

        urlLayout = this.findViewById(R.id.urlLayout);
        menuButton = this.findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (urlLayout.getVisibility() == View.VISIBLE) {
                    urlLayout.startAnimation(upsideAnim);
                } else {
                    urlLayout.startAnimation(downsideAnim);
                }
            }
        });

        final WebView webView = this.findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setSaveFormData(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        final EditText urlEditText = this.findViewById(R.id.urlEditText);
        final Button goButton = this.findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlString = urlEditText.getText().toString();
                if (urlString.isEmpty())
                    return;

                if (!urlString.startsWith("http://")) {
                    urlString = "http://" + urlString;
                    urlEditText.setText(urlString);
                }

                webView.loadUrl(urlString);
                urlLayout.startAnimation(upsideAnim);
            }
        });
    }

    private class SlidingAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (urlLayout.getVisibility() == View.VISIBLE) {
                urlLayout.setVisibility(View.GONE);
                menuButton.setText(R.string.expand);
            } else {
                urlLayout.setVisibility(View.VISIBLE);
                menuButton.setText(R.string.collapse);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
