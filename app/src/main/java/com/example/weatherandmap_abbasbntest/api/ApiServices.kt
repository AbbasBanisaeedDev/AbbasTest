package com.example.weatherandmap_abbasbntest.api

import com.example.weatherandmap_abbasbntest.model.ResponseWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiServices {

    @GET("data/2.5/weather?")
    suspend fun weatherList(@QueryMap queries: Map<String, String>): Response<ResponseWeather>


}