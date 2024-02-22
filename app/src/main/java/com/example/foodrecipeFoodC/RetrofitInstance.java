package com.example.foodrecipeFoodC;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit retrofit = null;
    private static String Base_Url = "https://www.themealdb.com/api/json/v1/1/";
    public static MealApiService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(MealApiService.class);
    }

}
