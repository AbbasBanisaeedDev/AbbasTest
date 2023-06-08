package com.example.weatherandmap_abbasbntest.model


import com.google.gson.annotations.SerializedName

data class ResponseWeather(
    @SerializedName("base")
    val base: String?, // stations
    @SerializedName("clouds")
    val clouds: Clouds?,
    @SerializedName("cod")
    val cod: Int?, // 200
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("dt")
    val dt: Int?, // 1686079368
    @SerializedName("id")
    val id: Int?, // 112931
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String, // تهران
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("timezone")
    val timezone: Int?, // 12600
    @SerializedName("visibility")
    val visibility: Int?, // 10000
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind?
) {
    data class Clouds(
        @SerializedName("all")
        val all: Int? // 75
    )

    data class Coord(
        @SerializedName("lat")
        val lat: Double, // 35.6944
        @SerializedName("lon")
        val lon: Double // 51.4215
    )

    data class Main(
        @SerializedName("feels_like")
        val feelsLike: Double, // 299.26
        @SerializedName("humidity")
        val humidity: Int, // 26
        @SerializedName("pressure")
        val pressure: Int, // 1014
        @SerializedName("temp")
        val temp: Double, // 299.88
        @SerializedName("temp_max")
        val tempMax: Double, // 300.94
        @SerializedName("temp_min")
        val tempMin: Double // 299.66
    )

    data class Sys(
        @SerializedName("country")
        val country: String?, // IR
        @SerializedName("id")
        val id: Int?, // 47737
        @SerializedName("sunrise")
        val sunrise: Int?, // 1686014326
        @SerializedName("sunset")
        val sunset: Int?, // 1686066439
        @SerializedName("type")
        val type: Int? // 2
    )

    data class Weather(
        @SerializedName("description")
        val description: String, // ابرهای پارچه پارچه شده
        @SerializedName("icon")
        val icon: String, // 04n
        @SerializedName("id")
        val id: Int, // 803
        @SerializedName("main")
        val main: String // Clouds
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Int?, // 290
        @SerializedName("speed")
        val speed: Double? // 7.2
    )
}