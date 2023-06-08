package com.example.weatherandmap_abbasbntest.viewmodel

import androidx.lifecycle.*
import com.example.weatherandmap_abbasbntest.model.ResponseWeather
import com.example.weatherandmap_abbasbntest.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val topWeatherList = MutableLiveData<ResponseWeather>()


    fun loadTopWeatherList(queries: Map<String, String>) = viewModelScope.launch {
        val response = repository.topWeatherList(queries)
        if (response.isSuccessful) {
            topWeatherList.postValue(response.body())
        }
    }


}