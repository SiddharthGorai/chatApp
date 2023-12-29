package com.example.chatapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.data.userData
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.viewModel.authViewModel
import com.example.chatapp.viewModel.userViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val authViewModel: authViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val toolbar = binding.Toolbar
//        toolbar.setTitle("ChatApp")
//        toolbar.inflateMenu(R.menu.nav_menu)
//
//
//        toolbar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.signout -> {
//                    authViewModel.signOut()
//                    startActivity(Intent(this, loginSignupActivity::class.java))
//                    finish()
//                    true
//                }
//
//                else -> {
//                    false
//
//                }
//            }
//        }


    }

    override fun onStart() {
        super.onStart()

        val currentUser = authViewModel.currentUser
        if (currentUser == null) {
            val intent = Intent(this, loginSignupActivity::class.java)
            startActivity(intent)
        }
    }
}