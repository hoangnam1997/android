package com.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {
    public static final String KEY_IMG  = "img";
    public static final String KEY_TEXT = "text";

    @SerializedName("url_img")
    @Expose
    private String url_img;

    @SerializedName("content_img")
    @Expose
    private String content_img;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("text")
    @Expose
    private String text;

    public Image(String url_img, String content_img, String type, String text) {
        this.url_img = url_img;
        this.content_img = content_img;
        this.type = type;
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent_img() {
        return content_img;
    }

    public void setContent_img(String content_img) {
        this.content_img = content_img;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
