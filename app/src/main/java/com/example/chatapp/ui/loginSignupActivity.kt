package com.example.chatapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.example.chatapp.databinding.ActivityLoginSignupBinding
import com.example.chatapp.viewModel.authViewModel



class loginSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginSignupBinding
    private val authViewModel: authViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val singupBtn: Button = binding.signupButton
        val loginBtn: Button = binding.loginButton

        singupBtn.setOnClickListener {
            val email: String = binding.lEmail.text.toString().trim()
            val password: String = binding.lPass.text.toString().trim()

            if (!email.isEmpty() && !password.isEmpty()) {
                authViewModel.signUp(email, password)
                authViewModel.firebaseUserMutableLiveData.observe(this) { fireBaseUser ->
                    if (fireBaseUser != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_LONG).show()
                    }

                }
            } else {
                Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_LONG).show()
            }
        }

        loginBtn.setOnClickListener {
            val email: String = binding.lEmail.text.toString().trim()
            val password: String = binding.lPass.text.toString().trim()

            if (!email.isEmpty() && !password.isEmpty()) {
                authViewModel.signIn(email, password)
                authViewModel.firebaseUserMutableLiveData.observe(this) { fireBaseUser ->
                    if (fireBaseUser != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_LONG).show()

                    }
                }
            } else {
                Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_LONG).show()
            }
        }


    }
}