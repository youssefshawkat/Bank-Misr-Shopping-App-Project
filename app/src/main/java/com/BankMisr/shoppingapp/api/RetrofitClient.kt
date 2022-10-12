package com.BankMisr.shoppingapp.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }
    val httpClient by lazy {
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(true)
            .writeTimeout(60, TimeUnit.SECONDS)
    }
    val retrofitServices by lazy {
        Retrofit.Builder().baseUrl("https://clothes-online-shopping.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build().create(RetrofitServices::class.java)
    }

}