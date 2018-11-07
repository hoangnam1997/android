package com.finalproject.ultils;

import com.finalproject.response.CategoryResponse;
import com.finalproject.response.NewspaperResponse;

import retrofit.http.GET;
import retrofit.Call;
public interface Service {
//    menu category
    @GET("/menu")
    Call<CategoryResponse> getCategory();
    //    menu
    @GET("/home")
    Call<NewspaperResponse> getHome();
}
