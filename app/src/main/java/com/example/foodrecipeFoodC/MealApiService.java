package com.example.foodrecipeFoodC;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealApiService {

    @GET("search.php?f=c")
    Call<Result> getMeals();
}
