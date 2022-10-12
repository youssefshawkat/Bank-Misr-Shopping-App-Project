package com.BankMisr.shoppingapp.ui.fragment.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BankMisr.shoppingapp.api.RetrofitClient
import com.BankMisr.shoppingapp.model.ProductsResponse
import retrofit2.Call
import retrofit2.Response

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    val posts: MutableLiveData<ProductsResponse?> = MutableLiveData()

    fun getPosts(context: Context, token: String) {
        RetrofitClient.retrofitServices.getProducts(
            "Bearer $token"
        ).enqueue(object : retrofit2.Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    posts.value = response.body()
                    Log.i(TAG, response.body().toString())
                } else {
                    posts.postValue(null)
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show()
                posts.postValue(null)
            }
        })
    }

    fun getPostsCategory(context: Context, token: String,category: String) {
        RetrofitClient.retrofitServices.getProductsByCategory(
            "Bearer $token",
            category

        ).enqueue(object : retrofit2.Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    posts.value = response.body()
                    Log.i(TAG, response.body().toString())
                } else {
                    posts.postValue(null)
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show()
                posts.postValue(null)
            }
        })
    }








}