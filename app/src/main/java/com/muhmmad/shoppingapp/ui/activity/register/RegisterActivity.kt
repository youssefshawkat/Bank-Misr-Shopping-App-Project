package com.muhmmad.shoppingapp.ui.activity.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns

import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.muhmmad.shoppingapp.R

import com.muhmmad.shoppingapp.databinding.ActivityRegisterBinding

import com.muhmmad.shoppingapp.ui.activity.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private var genderRadioGroup: RadioGroup? = null
    private var selectedGender: RadioButton? = null
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.apply {
            saveBtn.setOnClickListener {
                if (!validateForm()) {
                    return@setOnClickListener
                }

                val map = HashMap<String, Any>()
                map["name"] = etName.text.toString()
                map["userName"] = etUsername.text.toString()
                map["email"] = etMail.text.toString()
                map["password"] = etPassword.text.toString()
                genderRadioGroup = findViewById(R.id.radio_group)
                val selectedRadioButtonId: Int = genderRadioGroup!!.checkedRadioButtonId

                if (selectedRadioButtonId != -1) {
                    selectedGender = findViewById(selectedRadioButtonId)
                    map["gender"] = selectedGender?.text.toString()

                }

                map["phoneNum"] = etPhoneNumber.text.toString()
                map["city"] = etCity.text.toString()
                map["address"] = etAddress.text.toString()
                map["isActive"] = true
                viewModel.register(binding.root.context, map)
            }
        }



        viewModel.registerResponse.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        )
    }


    private fun validateForm(): Boolean {
        var valid = true
        val name = binding.etName.text.toString().trim { it <= ' ' }
        val userName = binding.etUsername.text.toString().trim { it <= ' ' }
        val email = binding.etMail.text.toString().trim { it <= ' ' }
        val password = binding.etPassword.text.toString().trim { it <= ' ' }
        val phoneNumber =binding.etPhoneNumber.text.toString().trim { it <= ' ' }
        val city =binding.etCity.text.toString().trim { it <= ' ' }
        val address = binding.etAddress.text.toString().trim { it <= ' ' }
        val gender  = binding.radioGroup
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
        if (TextUtils.isEmpty(userName)) {
            binding.inputUserName.error = getString(R.string.username_alert)
            valid = false
        } else {
            binding.inputUserName.error = null
        }
        if (TextUtils.isEmpty(name)) {
            binding.inputName.error = getString(R.string.name_alert)
            valid = false
        } else {
            binding.inputName.error = null
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.inputPhoneNumber.error = getString(R.string.phone_alert)
            valid = false
        } else {
            binding.inputPhoneNumber.error = null
        }
        if (TextUtils.isEmpty(city)) {
            binding.inputCity.error = getString(R.string.city_alert)
            valid = false
        } else {
            binding.inputCity.error = null
        }
        if (TextUtils.isEmpty(address)) {
            binding.inputaddress.error = getString(R.string.address_alert)
            valid = false
        } else {
            binding.inputaddress.error = null
        }
        if (gender.checkedRadioButtonId == -1) {
            Toast.makeText(applicationContext,"Please Select Your Gender",Toast.LENGTH_SHORT).show();
            valid = false
        }


        return valid
    }





}