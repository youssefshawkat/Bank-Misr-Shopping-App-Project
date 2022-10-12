package com.BankMisr.shoppingapp.ui.activity.register

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BankMisr.shoppingapp.api.RetrofitClient
import com.BankMisr.shoppingapp.model.RegisterResponse
import retrofit2.Call
import retrofit2.Response

private val TAG = "RegisterViewModel"
class RegisterViewModel : ViewModel() {

    val registerResponse: MutableLiveData<RegisterResponse?> = MutableLiveData()

    fun register(context: Context, map: HashMap<String, Any>) {
        RetrofitClient.retrofitServices.register(
            map
        ).enqueue(object : retrofit2.Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    registerResponse.value = response.body()
                    Log.i(TAG, response.body().toString())
                } else {
                    registerResponse.postValue(null)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show()
                registerResponse.postValue(null)
            }
        })
    }
}