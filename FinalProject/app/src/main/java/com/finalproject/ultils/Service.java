package com.finalproject.ultils;

import com.finalproject.response.CategoryResponse;
import com.finalproject.response.DetailResponse;
import com.finalproject.response.NewspaperResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Service {
//    menu category
    @GET("/menu")
    Call<CategoryResponse> getCategory();
    //    menu
    @GET("/home")
    Call<NewspaperResponse> getNewspaper();
    @GET("/menu")
    Call<NewspaperResponse> getNewspaper(@Query("id") int id);
    @FormUrlEncoded
    @POST("/detail")
    Call<DetailResponse> getDetail(@Field("url") String url);
    @GET("/search")
    Call<NewspaperResponse> getSearchNewspaper(@Query("text") String text);
}
