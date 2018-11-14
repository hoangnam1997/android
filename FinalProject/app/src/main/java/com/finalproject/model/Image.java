package com.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {
    @SerializedName("url_img")
    @Expose
    private String url_img;

    @SerializedName("content_img")
    @Expose
    private String content_img;


    public Image(String url_img, String content_img) {
        this.url_img = url_img;
        this.content_img = content_img;
    }
    public Image() {
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getContent_img() {
        return content_img;
    }

    public void setContent_img(String content_img) {
        this.content_img = content_img;
    }
}
