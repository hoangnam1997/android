package com.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newspaper {
    public static final String KEY_LINK = "Link";
    public static final String KEY_TITLE = "Title";
    @SerializedName("id")
    @Expose
    private int id;

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

    public Newspaper(int id, String thumb_art, String title, String description) {
        this.id = id;
        this.thumb_art = thumb_art;
        this.title = title;
        this.description = description;
    }

    public Newspaper() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
