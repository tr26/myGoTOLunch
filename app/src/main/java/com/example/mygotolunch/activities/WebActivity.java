package com.example.mygotolunch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.mygotolunch.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(url);
    }
}
