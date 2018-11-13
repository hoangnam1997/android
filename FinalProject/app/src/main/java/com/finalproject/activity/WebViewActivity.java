package com.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.finalproject.R;
import com.finalproject.model.Newspaper;

public class WebViewActivity extends AppCompatActivity {

    private WebView wvNewspaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setAttributes();
        setWebView();
    }

    public void setAttributes(){
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbarMenu);
        topToolBar.setTitleTextColor(getResources().getColor(R.color.toolBarColor));
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wvNewspaper = (WebView) findViewById(R.id.wvNewspaper);
        wvNewspaper.setWebViewClient(new WebViewClient());
    }

//    set web view
    public void setWebView(){
        Intent intent = getIntent();
        if(intent != null){
            String link = intent.getStringExtra(Newspaper.KEY_LINK);
            String title = intent.getStringExtra(Newspaper.KEY_TITLE);
            wvNewspaper.loadUrl(link);
            getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}