package com.BankMisr.shoppingapp.api

import com.BankMisr.shoppingapp.model.LoginResponse
import com.BankMisr.shoppingapp.model.ProductDetailsResponse
import com.BankMisr.shoppingapp.model.ProductsResponse
import com.BankMisr.shoppingapp.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {

    @GET("/api/product/getProducts")
    fun getProducts(
        @Header("Authorization") token: String
    ): Call<ProductsResponse>

    @GET("/api/product/getProductByCategory/{cat}")
    fun getProductsByCategory(
        @Header("Authorization") token: String,
        @Path("cat") cat: String
    ): Call<ProductsResponse>


    @POST("user/login")
    fun login(
        @Body loginInfo: Map<String, String>,
    ): Call<LoginResponse>

    @POST("user/register")
    @JvmSuppressWildcards
    fun register(
        @Body registerInfo: Map<String, Any>,
    ): Call<RegisterResponse>

    @GET("api/product/getById/{id}")
    fun getProductDetails(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<ProductDetailsResponse>
}