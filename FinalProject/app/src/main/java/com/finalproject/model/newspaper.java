package com.finalproject.model;

public class newspaper {
    private int id;
    private String url_image;
    private String title;
    private String introduce;

    public newspaper(int id, String url_image, String title, String introduce) {
        this.id = id;
        this.url_image = url_image;
        this.title = title;
        this.introduce = introduce;
    }

    public newspaper() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
