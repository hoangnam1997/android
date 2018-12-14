package com.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Detail implements Serializable {
    @SerializedName("contents")
    @Expose
    private List<Image> contents;

    public Detail(List<Image> contents) {
        this.contents = contents;
    }
    public Detail() {
    }

    public List<Image> getContents() {
        return contents;
    }

    public void setContents(List<Image> contents) {
        this.contents = contents;
    }

//    public String getContentView(){
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < contents.size(); i++) {
//            result.append(contents.get(i));
//            if (i != contents.size() - 1) {
//                result.append("\n\n");
//            }
//        }
//        return result.toString();
//    }
}
