package com.muhmmad.shoppingapp.ui.activity.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhmmad.shoppingapp.api.RetrofitClient
import com.muhmmad.shoppingapp.model.LoginResponse
import retrofit2.Call
import retrofit2.Response

private const val TAG = "LoginViewModel"

class LoginViewModel : ViewModel() {

    val loginResponse: MutableLiveData<LoginResponse?> = MutableLiveData()

    fun login(context: Context, map: Map<String, String>) {
        RetrofitClient.retrofitServices.login(
            map
        ).enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    loginResponse.value = response.body()
                    Log.i(TAG, response.body().toString())
                } else {
                    loginResponse.postValue(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show()
                loginResponse.postValue(null)
            }
        })
    }
}