package com.muhmmad.shoppingapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhmmad.shoppingapp.R
import com.muhmmad.shoppingapp.databinding.ProductItemBinding
import com.muhmmad.shoppingapp.model.ProductsResponse

class ProductsAdapter(private val list: ProductsResponse) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(root).load(list[position].image).into(ivProductImage)
            tvProductName.text = list[position].name
            tvProductPrice.text = list[position].price.toString()

            root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", list[position].id)
                Navigation.findNavController(it).navigate(
                    R.id.productDetailsFragment,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int = list.size
}