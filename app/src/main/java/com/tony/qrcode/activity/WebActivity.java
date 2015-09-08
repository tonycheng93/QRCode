package com.tony.qrcode.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tony.qrcode.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebActivity extends Activity {

    private WebView mWebView;

    private String mUrl;

    public static final String ARG_URL = "ARG_URL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        mWebView = (WebView) findViewById(R.id.web_view);
        mUrl = getIntent().getStringExtra(ARG_URL);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
