package com.finalproject.ultils;

public class Server {
    public static final String BASE_URL = "http://tructt.laptrinhaz.com:65014";

    public static Service getService() {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
