package com.example.foodrecipeFoodC;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ArrayList<Meal> meals = new ArrayList<>();
    private MutableLiveData<List<Meal>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public Repository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Meal>> getMutableLiveData() {
        MealApiService mealApiService = RetrofitInstance.getService();
        Call<Result> call = mealApiService.getMeals();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result != null && result.getMeals() != null) {
                    meals = (ArrayList<Meal>) result.getMeals();
                    mutableLiveData.setValue(meals);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
