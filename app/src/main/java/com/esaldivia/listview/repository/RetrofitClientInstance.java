package com.esaldivia.listview.repository;

import com.esaldivia.listview.model.ItemInterface;
import com.esaldivia.listview.model.LaptopItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    // todo pasarlo a gradle como en sima
    private static final String BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface GetDataService {

        @GET("/list")
        Call<List<LaptopItem>> getAllItems();
    }
}