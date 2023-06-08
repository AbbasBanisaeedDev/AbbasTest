package com.example.weatherandmap_abbasbntest.repository

import com.example.weatherandmap_abbasbntest.api.ApiServices

import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:ApiServices) {
    suspend fun topWeatherList(queries: Map<String, String>)=api.weatherList(queries)

}