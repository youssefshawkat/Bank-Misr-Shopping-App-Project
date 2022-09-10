package com.muhmmad.shoppingapp.ui.fragment.product_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.muhmmad.shoppingapp.databinding.FragmentProductDetailsBinding
import com.muhmmad.shoppingapp.ui.activity.login.LoginActivity.Companion.token

class ProductDetailsFragment : Fragment() {


    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]

        binding.btnAdd.setOnClickListener{



        }



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
                binding.tvProductTitle.text = it.name
                binding.tvProductDetails.text = it.description
                Glide.with(binding.root).load(it.image).into(binding.ivProduct)
            }
        }

        binding.ivBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

    }
}