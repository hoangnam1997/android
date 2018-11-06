package com.finalproject;

import retrofit.http.GET;
import retrofit.Call;
public interface Service {
//    menu category
    @GET("/menu")
    Call<CategoryResponse> getCategory();
}
