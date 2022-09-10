package com.muhmmad.shoppingapp.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.muhmmad.shoppingapp.R
import com.muhmmad.shoppingapp.adapter.ProductsAdapter
import com.muhmmad.shoppingapp.databinding.FragmentHomeBinding
import com.muhmmad.shoppingapp.ui.activity.login.LoginActivity.Companion.name
import com.muhmmad.shoppingapp.ui.activity.login.LoginActivity.Companion.token


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var categoryRadioGroup: RadioGroup? = null
    private var selectedCategory: RadioButton? = null
    private var text: TextView? = null
    private var firsTime = 0;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]


        return binding.root
    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryRadioGroup = getView()?.findViewById(R.id.rg_main)

        val selectedRadioButtonId: Int = categoryRadioGroup!!.checkedRadioButtonId

        allCategory()



        categoryRadioGroup!!.check(R.id.rb_all)


        val allbtn: RadioButton? = getView()?.findViewById(R.id.rb_all)
        if (allbtn != null) {
            allbtn.setOnClickListener() {

                allCategory()
            }
        }

        val menbtn: RadioButton? = getView()?.findViewById(R.id.rb_men)
        if (menbtn != null) {
            menbtn.setOnClickListener() {

                menCategory()
            }
        }
        val womenbtn: RadioButton? = getView()?.findViewById(R.id.rb_women)
        if (womenbtn != null) {
            womenbtn.setOnClickListener() {

                womenCategory()
            }
        }
        val kidsbtn: RadioButton? = getView()?.findViewById(R.id.rb_kids)
        if (kidsbtn != null) {
            kidsbtn.setOnClickListener() {
                kidsCategory()

            }
        }


    }

    @SuppressLint("SetTextI18n")
    fun allCategory() {
        val text = view?.findViewById<TextView>(R.id.tv_hi)
        text?.text = "Hi, "
        binding.apply {
            viewModel.getPosts(binding.root.context, token.toString())

            tvHi.text = "${tvHi.text} $name"

        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun menCategory() {
        binding.apply {
            viewModel.getPostsCategory(binding.root.context, token.toString(), "MEN")


        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun womenCategory() {
        binding.apply {
            viewModel.getPostsCategory(binding.root.context, token.toString(), "WOMEN")


        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

    @SuppressLint("SetTextI18n")

    fun kidsCategory() {
        binding.apply {
            viewModel.getPostsCategory(binding.root.context, token.toString(), "KIDS")


        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

}