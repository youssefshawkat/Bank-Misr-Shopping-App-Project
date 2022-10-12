package com.BankMisr.shoppingapp.ui.fragment.product_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.BankMisr.shoppingapp.R
import com.BankMisr.shoppingapp.databinding.FragmentProductDetailsBinding
import com.BankMisr.shoppingapp.model.ProductDetailsResponse
import com.BankMisr.shoppingapp.model.ProductsResponseItem
import com.BankMisr.shoppingapp.ui.activity.login.LoginActivity.Companion.token
import com.BankMisr.shoppingapp.ui.fragment.home.HomeFragment.Companion.productsList

class ProductDetailsFragment : Fragment() {


    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel
    private var color: String = "BLACK"
    private var item: ProductDetailsResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductDetails(
            requireContext(),
            token.toString(),
            requireArguments().getInt("id")
        )

        viewModel.productDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                item = it
                binding.tvProductTitle.text = it.name
                binding.tvProductDetails.text = it.description
                Glide.with(binding.root).load(it.image).into(binding.ivProduct)
                binding.tvPrice.text = it.price
            }
        }

        binding.rgColors.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_black -> {
                    color = "BLACK"
                }
                R.id.rb_yellow -> {
                    color = "YELLOW"
                }
                R.id.rb_gray -> {
                    color = "GREEN"
                }
            }
        }

        binding.btnAdd.setOnClickListener {

            for (i in productsList) {
                if (i.image == item?.image) {
                    Toast.makeText(
                        binding.root.context,
                        "Sorry, but this item is already in the cart",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }

            productsList.add(
                ProductsResponseItem(
                    true,
                    1,
                    "",
                    color,
                    item?.description,
                    requireArguments().getInt("id"),
                    item?.image,
                    item?.name,
                    item?.price?.toDouble(),
                    item?.size
                )
            )

            Toast.makeText(binding.root.context, "Done", Toast.LENGTH_SHORT).show()
        }

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }
}