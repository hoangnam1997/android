package com.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.finalproject.R;
import com.finalproject.helper.NewspaperHelper;
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
            Newspaper newspaper = (Newspaper)intent.getExtras().getSerializable(Newspaper.KEY_OBJECT);
            newspaper.setIsFollow(this);
            clickFollow(newspaper);
            String link = newspaper.getUrl();
            String title = newspaper.getTitle();
            wvNewspaper.loadUrl(link);
            getSupportActionBar().setTitle(title);
        }
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //    set event click
    public void clickFollow(final Newspaper itemCurrent){
        final Button btnFollow = (Button)findViewById(R.id.btnFollowDetail);
        if(itemCurrent.isFollow()){
            btnFollow.setBackgroundResource(R.drawable.heart);
        }else{
            btnFollow.setBackgroundResource(R.drawable.heart2);
        }
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewspaperHelper newspaperHelper = new NewspaperHelper(WebViewActivity.this);
                itemCurrent.setFollow(!itemCurrent.isFollow());
                if(itemCurrent.isFollow()){
                    btnFollow.setBackgroundResource(R.drawable.heart);
                }else{
                    btnFollow.setBackgroundResource(R.drawable.heart2);
                }
                if(itemCurrent.isFollow()){
                    newspaperHelper.insert(itemCurrent);
                    v.setBackgroundResource(R.drawable.heart);
                }else{
                    newspaperHelper.delete(itemCurrent);
                }

            }
        });
    }
}