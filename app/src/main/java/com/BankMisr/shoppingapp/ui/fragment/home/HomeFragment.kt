package com.BankMisr.shoppingapp.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.BankMisr.shoppingapp.R
import com.BankMisr.shoppingapp.adapter.ProductsAdapter
import com.BankMisr.shoppingapp.databinding.FragmentHomeBinding
import com.BankMisr.shoppingapp.model.ProductsResponseItem
import com.BankMisr.shoppingapp.ui.activity.login.LoginActivity.Companion.name
import com.BankMisr.shoppingapp.ui.activity.login.LoginActivity.Companion.token


class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var categoryRadioGroup: RadioGroup? = null
    lateinit var searchView: SearchView


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

        searchView = view.findViewById(R.id.iv_search) as SearchView
        searchView.setOnQueryTextListener(this)

        categoryRadioGroup = getView()?.findViewById(R.id.rg_main)

        categoryRadioGroup!!.checkedRadioButtonId

        allCategory()

        categoryRadioGroup!!.check(R.id.rb_all)


        val allbtn: RadioButton? = getView()?.findViewById(R.id.rb_all)
        if (allbtn != null) {


            allbtn.setOnClickListener() {
                searchView.setQuery("", false)
                searchView.isIconified = true
                searchView.clearFocus()
                allCategory()
            }
        }

        val menbtn: RadioButton? = getView()?.findViewById(R.id.rb_men)
        if (menbtn != null) {
            menbtn.setOnClickListener() {
                searchView.setQuery("", false)
                searchView.isIconified = true
                searchView.clearFocus()

                menCategory()
            }
        }
        val womenbtn: RadioButton? = getView()?.findViewById(R.id.rb_women)
        if (womenbtn != null) {
            womenbtn.setOnClickListener() {
                searchView.setQuery("", false)
                searchView.isIconified = true
                searchView.clearFocus()
                womenCategory()
            }
        }
        val kidsbtn: RadioButton? = getView()?.findViewById(R.id.rb_kids)
        if (kidsbtn != null) {
            kidsbtn.setOnClickListener() {
                searchView.setQuery("", false)
                searchView.isIconified = true
                searchView.clearFocus()
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

            categoryRadioGroup?.check(R.id.rb_all)
            searchView.setQuery("", false)
            searchView.isIconified = true
            searchView.clearFocus()

            tvHi.text = "${tvHi.text} $name"

        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvProducts.adapter = ProductsAdapter(it)
                categoryRadioGroup?.check(R.id.rb_all)
                searchView.setQuery("", false)
                searchView.isIconified = true
                searchView.clearFocus()

            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun menCategory() {
        binding.apply {
            viewModel.getPostsCategory(binding.root.context, token.toString(), "MEN")
            categoryRadioGroup?.check(R.id.rb_men)

        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                categoryRadioGroup?.check(R.id.rb_men)
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun womenCategory() {
        binding.apply {
            categoryRadioGroup?.check(R.id.rb_women)
            viewModel.getPostsCategory(binding.root.context, token.toString(), "WOMEN")
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            if (it != null) {
                categoryRadioGroup?.check(R.id.rb_women)
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

    @SuppressLint("SetTextI18n")

    fun kidsCategory() {
        binding.apply {
            categoryRadioGroup?.check(R.id.rb_kids)
            viewModel.getPostsCategory(binding.root.context, token.toString(), "KIDS")


        }

        viewModel.posts.observe(viewLifecycleOwner) {
            categoryRadioGroup?.check(R.id.rb_kids)
            if (it != null) {
                binding.rvProducts.adapter = ProductsAdapter(it)
            }
        }

    }

    companion object {
        var productsList = ArrayList<ProductsResponseItem>()
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        var adapter = viewModel.posts.value?.let { ProductsAdapter(it) }
        adapter?.filter?.filter(query)
        binding.rvProducts.adapter = adapter

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        var adapter = viewModel.posts.value?.let { ProductsAdapter(it) }
        adapter?.filter?.filter(newText)
        binding.rvProducts.adapter = adapter
        return false
    }


}