package com.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Detail implements Serializable {
    @SerializedName("imgs")
    @Expose
    private List<Image> imgs;
    @SerializedName("contents")
    @Expose
    private List<String> contents;

    public Detail(List<Image> imgs, List<String> contents) {
        this.imgs = imgs;
        this.contents = contents;
    }
    public Detail() {
    }

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public String getContentView(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contents.size(); i++) {
            result.append(contents.get(i));
            if (i != contents.size() - 1) {
                result.append("\n\n");
            }
        }
        return result.toString();
    }
}
