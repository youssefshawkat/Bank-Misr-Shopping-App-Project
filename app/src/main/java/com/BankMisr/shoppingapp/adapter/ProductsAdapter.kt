package com.BankMisr.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.BankMisr.shoppingapp.R
import com.BankMisr.shoppingapp.databinding.ProductItemBinding
import com.BankMisr.shoppingapp.model.ProductsResponse
import java.util.*

class ProductsAdapter(private var list: ProductsResponse) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() , Filterable {

    lateinit var productsListFilter: ProductsResponse


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
                val bundle = android.os.Bundle()
                bundle.putInt("id", list[position].id)
                Navigation.findNavController(it).navigate(
                    R.id.productDetailsFragment,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                productsListFilter = if (charString.isEmpty()) list else {
                    val filteredList = ProductsResponse()
                    list
                        .filter {

                            (it.name!!.contains(constraint,true))

                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                list = productsListFilter
                return FilterResults().apply { values = productsListFilter }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                productsListFilter = if (results?.values == null)
                    ProductsResponse()
                else
                    results.values as ProductsResponse
                notifyDataSetChanged()
            }
        }
    }
}