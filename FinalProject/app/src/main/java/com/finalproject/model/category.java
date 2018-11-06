package com.finalproject.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class category {
    private int id;
    private String title;
    private String slug;
    private String url;

    public category(int id, String title, String slug, String url) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.url = url;
    }

    public category() {
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//get list category by api
    public List<category> getCategory(){
        List<category> listCategory = new ArrayList<category>();
        listCategory.add(new category(1,"Kinh Doanh","kinh-doanh","https://kinhdoanh.vnexpress.net/"));
        listCategory.add(new category(2,"Giải trí","giai-tri","https://giaitri.vnexpress.net/"));
        listCategory.add(new category(3,"Video","video","https://video.vnexpress.net"));
        return listCategory;
    }
}
