package com.BankMisr.shoppingapp.model

class ProductsResponse : ArrayList<ProductsResponseItem>()

data class ProductsResponseItem(
    val addedToCart: Boolean,
    var boughtItemsCount: Int,
    val category: String?,
    val color: String?,
    val description: String?,
    val id: Int,
    val image: String?,
    val name: String?,
    val price: Double?,
    val size: String?
)