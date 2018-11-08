package com.finalproject.response;

import com.finalproject.model.Category;
import com.finalproject.model.Newspaper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewspaperResponse {
    @SerializedName("record")
    @Expose
    private List<Newspaper> listNewspaper = null;

    public List<Newspaper> getListNewspaper() {
        return listNewspaper;
    }

    public void setListNewspaper(List<Newspaper> listNewspaper) {
        this.listNewspaper = listNewspaper;
    }
}
