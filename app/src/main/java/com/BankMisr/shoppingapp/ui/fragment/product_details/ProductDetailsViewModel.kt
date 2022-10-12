package com.BankMisr.shoppingapp.ui.fragment.product_details

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BankMisr.shoppingapp.api.RetrofitClient
import com.BankMisr.shoppingapp.model.ProductDetailsResponse
import retrofit2.Call
import retrofit2.Response

private const val TAG = "ProductDetailsViewModel"
class ProductDetailsViewModel : ViewModel() {

    val productDetails: MutableLiveData<ProductDetailsResponse?> = MutableLiveData()


    fun getProductDetails(context: Context, token: String, id: Int) {
        RetrofitClient.retrofitServices.getProductDetails(
            "Bearer $token",id
        ).enqueue(object : retrofit2.Callback<ProductDetailsResponse> {
            override fun onResponse(
                call: Call<ProductDetailsResponse>,
                response: Response<ProductDetailsResponse>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    productDetails.value = response.body()
                    Log.i(TAG, response.body().toString())
                } else {
                    productDetails.postValue(null)
                }
            }

            override fun onFailure(call: Call<ProductDetailsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show()
                productDetails.postValue(null)
            }
        })
    }
}