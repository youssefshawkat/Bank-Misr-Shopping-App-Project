package com.BankMisr.shoppingapp.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.BankMisr.shoppingapp.R
import com.BankMisr.shoppingapp.databinding.ActivityLoginBinding
import com.BankMisr.shoppingapp.ui.activity.home.HomeActivity
import com.BankMisr.shoppingapp.ui.activity.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewModel.loginResponse.observe(this) {
            if (it != null) {
                Log.i(TAG, "Login Response: $it")
                token = it.token
                name = it.name
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        binding.btnLogin.setOnClickListener {
            if (!validateForm()) {
                return@setOnClickListener
            }

            val map = HashMap<String, String>()
            map["email"] = binding.edtEmail.text.toString()
            map["password"] = binding.edtPass.text.toString()

            viewModel.login(this, map)
        }

    }

    fun signUp(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun validateForm(): Boolean {
        var valid = true
        val email = binding.edtEmail.text.toString().trim { it <= ' ' }
        val password = binding.edtPass.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            binding.inputEmail.error = getString(R.string.email_alert_1)
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false
            binding.inputEmail.error = getString(R.string.email_alert_2)
        } else {
            binding.inputEmail.error = null
        }
        if (TextUtils.isEmpty(password)) {
            binding.inputPass.error = getString(R.string.pass_alert)
            valid = false
        } else {
            binding.inputPass.error = null
        }
        return valid
    }

    companion object {
        var token: String? = null
        var name: String? = null
    }
}