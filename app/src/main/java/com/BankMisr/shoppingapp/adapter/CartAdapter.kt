package com.BankMisr.shoppingapp.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.BankMisr.shoppingapp.R
import com.BankMisr.shoppingapp.databinding.CartItemBinding
import com.BankMisr.shoppingapp.model.ProductsResponseItem


class CartAdapter(private val list: ArrayList<ProductsResponseItem>, context: Context?) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private var counter = 1
    private var tvPrice: TextView? = null
    private var price = 0.0
    private var context = context
    private lateinit var removeButton: Button


    inner class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            tvPrice = binding.root.findViewById(com.BankMisr.shoppingapp.R.id.tv_price)
            removeButton = itemView.findViewById(com.BankMisr.shoppingapp.R.id.remove_button)
            removeButton.setOnClickListener {
                list.removeAt(adapterPosition)
                refreshFragment(context)


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    @SuppressLint("CutPasteId")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(root).load(list[position].image).into(ivItemImage)
            tvItemName.text = list[position].name
            tvItemPrice.text = (list[position].price?.times(list[position].boughtItemsCount)).toString()
            tvCounter.text = list[position].boughtItemsCount.toString()

            price += counter * (list[position].price!!)




            tvPlus.setOnClickListener {
                counter = list[position].boughtItemsCount
                tvCounter.text = (++counter).toString()
                tvItemPrice.text = (counter * (list[position].price!!)).toString()
                list[position].boughtItemsCount = counter
                price += list[position].price!!
                refreshFragment(context)

            }

            tvMinus.setOnClickListener {
                counter = list[position].boughtItemsCount
                if (counter != 1) {

                    Log.d("not equal","Minus")
                    counter = list[position].boughtItemsCount
                    tvPrice = root.findViewById(com.BankMisr.shoppingapp.R.id.tv_price)
                    tvCounter.text = (--counter).toString()
                    tvItemPrice.text = (counter * (list[position].price!!)).toString()
                    list[position].boughtItemsCount = counter
                    price -= list[position].price!!
                    refreshFragment(context)


                }
            }
        }
    }


    override fun getItemCount(): Int = list.size


    fun refreshFragment(context: Context?) {
        context?.let {
            var fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            var fragment = fragmentManager?.findFragmentById(R.id.nav_host_fragment)
            var cartFragment = fragment?.childFragmentManager?.fragments?.get(0)

            if (fragment != null) {

                if (cartFragment != null) {

                    fragment.childFragmentManager.beginTransaction().detach(cartFragment).commit()
                    fragment.childFragmentManager.beginTransaction().attach(cartFragment).commit()

                }
            }


        }


    }
}