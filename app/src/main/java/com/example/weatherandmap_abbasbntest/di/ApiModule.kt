package com.example.weatherandmap_abbasbntest.di

import com.example.weatherandmap_abbasbntest.api.ApiServices
import com.example.weatherandmap_abbasbntest.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    //Client
    @Provides
    @Singleton
    fun provideConnectionTime()=Constants.CONNECTION_TIME


    @Provides
    @Singleton
    fun provideGson():Gson=GsonBuilder().setLenient().create()


    //Client
    @Provides
    @Singleton
    fun provideInterceptor()=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }


    //Client
    @Provides
    @Singleton
    fun provideClient(time:Long,interceptor: HttpLoggingInterceptor)=OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .writeTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .connectTimeout(time, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String,gson: Gson,client: OkHttpClient) :ApiServices=
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)

}