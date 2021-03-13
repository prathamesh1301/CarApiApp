package com.example.carsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarServiceApi {
    @GET("https://private-anon-97c1ba0fb0-carsapi1.apiary-mock.com/manufacturers")
    Call<List<CarAttributes>> getAllCars();
}
