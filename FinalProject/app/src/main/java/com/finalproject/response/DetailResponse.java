package com.finalproject.response;

import com.finalproject.model.Category;
import com.finalproject.model.Detail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResponse {
    @SerializedName("record")
    @Expose
    private Detail detail = null;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
