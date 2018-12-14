package com.finalproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.helper.NewspaperHelper;
import com.finalproject.model.Detail;
import com.finalproject.model.Image;
import com.finalproject.model.Newspaper;
import com.finalproject.response.DetailResponse;
import com.finalproject.ultils.Server;
import com.finalproject.ultils.Service;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class WebViewActivity extends AppCompatActivity {

//    private WebView wvNewspaper;
    private Detail rDetail;
    private LinearLayoutCompat linearContent;


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
        linearContent = (LinearLayoutCompat) findViewById(R.id.linearContent);
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
        List<Image> aImage = rDetail.getContents();
        for (int i = 0; i < aImage.size(); i++) {
            Image image =  aImage.get(i);
            getViewOfTypeContent(image);
        }
    }

//    add content to view
    public void getViewOfTypeContent(final Image image){
        switch (image.getType()){
            case Image.KEY_IMG:
                LayoutInflater inflater = LayoutInflater.from(this);
                View view = inflater.inflate(R.layout.item_viewpager_newspaper,null,false);
                final ImageView imageView = (ImageView) view.findViewById(R.id.imgDetail);
                TextView textView = (TextView) view.findViewById(R.id.txtDetail);
//              truyen du lieu
                String a = image.getUrl_img();
                textView.setText(image.getContent_img());
//                Picasso.with(this).load(image.getUrl_img()).fit().centerCrop().into(imageView);
                Picasso.with(this).load(image.getUrl_img()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                linearContent.addView(view);
                break;
            case Image.KEY_TEXT:
                TextView textViewAdd = new TextView(this);
                textViewAdd.setText(image.getText());
                linearContent.addView(textViewAdd);
                break;
            default:
                break;
        }

    }
}