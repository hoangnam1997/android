package com.finalproject.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.activity.WebViewActivity;
import com.finalproject.helper.NewspaperHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Newspaper implements Serializable {
    public static final String KEY_LINK = "Link";
    public static final String KEY_TITLE = "Title";
    public static final String KEY_OBJECT = "Newspaper";

    @SerializedName("thumb_art")
    @Expose
    private String thumb_art;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("url")
    @Expose
    private String url;

    private boolean isFollow = false;

    public Newspaper(String title, String url, String description,String thumb_art) {
        this.thumb_art = thumb_art;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Newspaper() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb_art() {
        return thumb_art;
    }

    public void setThumb_art(String thumb_art) {
        this.thumb_art = thumb_art;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

//    set is follow
    public void setIsFollow(Context context){
        NewspaperHelper newspaperHelper = new NewspaperHelper(context);
        Newspaper mNewspaper = newspaperHelper.get(this.getUrl());
        if(mNewspaper != null){
            this.setFollow(true);
        }else{
            this.setFollow(false);
        }
    }
}
