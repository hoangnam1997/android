package com.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.adapter.ViewPagerAdapter;
import com.finalproject.helper.NewspaperHelper;
import com.finalproject.model.Detail;
import com.finalproject.model.Newspaper;
import com.finalproject.response.CategoryResponse;
import com.finalproject.response.DetailResponse;
import com.finalproject.ultils.Server;
import com.finalproject.ultils.Service;
import com.google.gson.Gson;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class WebViewActivity extends AppCompatActivity {

//    private WebView wvNewspaper;
    private Detail rDetail;
    private TextView txtContent;
    private ViewPager vbNewspaper;

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
        txtContent = (TextView)findViewById(R.id.txtContent);
        txtContent.setMovementMethod(new ScrollingMovementMethod());
        vbNewspaper = (ViewPager) findViewById(R.id.vbNewspaper);
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
//            set content
    //        get data
            Service mService = Server.getService();
            mService.getDetail(link).enqueue(new Callback<DetailResponse>(){
                @Override
                public void onResponse(Response<DetailResponse> response, Retrofit retrofit) {
                    rDetail = response.body().getDetail();
                    if(rDetail!=null){
                        setViewDetail(rDetail);
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(WebViewActivity.this, R.string.app_error,Toast.LENGTH_LONG).show();
                }
            });
//            set title
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

//    set view detail
    public void setViewDetail(Detail rDetail){
//        set content
        txtContent.setText(rDetail.getContentView());
//        set ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,rDetail.getImgs());
        vbNewspaper.setAdapter(viewPagerAdapter);
    }
}