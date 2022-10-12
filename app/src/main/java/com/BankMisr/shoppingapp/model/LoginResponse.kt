package com.BankMisr.shoppingapp.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("token")
    val token: String
)
