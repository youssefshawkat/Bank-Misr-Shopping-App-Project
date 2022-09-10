package com.muhmmad.shoppingapp.model

data class ProductDetailsResponse(
    val addedToCart: String,
    val boughtItemsCount: String,
    val category: String,
    val color: String,
    val description: String,
    val image: String,
    val name: String,
    val price: String,
    val size: String
)