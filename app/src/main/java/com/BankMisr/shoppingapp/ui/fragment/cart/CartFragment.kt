package com.BankMisr.shoppingapp.ui.fragment.cart
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.BankMisr.shoppingapp.adapter.CartAdapter
import com.BankMisr.shoppingapp.databinding.FragmentCartBinding
import com.BankMisr.shoppingapp.ui.fragment.home.HomeFragment.Companion.productsList


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private var price: Double = 0.0
    private lateinit var itemPrice : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            price = 0.0
            for (i in productsList) {

                price += i.price!! * i.boughtItemsCount
            }
            tvPrice.text = price.toString()
            rvCard.adapter = CartAdapter(productsList,context)





        }
    }



}