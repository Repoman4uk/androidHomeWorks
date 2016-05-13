package com.itis.androidlab.retrofit.network;

import com.itis.androidlab.retrofit.models.FullWeatherInfo;

import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherRest {

    @GET("/data/2.5/forecast/daily")
    FullWeatherInfo getTemperatureByCity(@Query("q") String cityName);




}
