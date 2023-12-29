package com.example.chatapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.data.userData
import com.example.chatapp.databinding.FragmentUserBinding
import com.example.chatapp.userRecyclerViewAdapter
import com.example.chatapp.viewModel.authViewModel
import com.example.chatapp.viewModel.userViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class userFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: userViewModel by activityViewModels()
    private val authViewModel: authViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUserBinding.bind(view)

        val toolbar = binding.Toolbar
        toolbar.setTitle("ChatApp")
        toolbar.inflateMenu(R.menu.nav_menu)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.signout -> {
                    authViewModel.signOut()
                    startActivity(Intent(context, loginSignupActivity::class.java))
                    true
                }

                else -> {
                    false

                }
            }
        }

        val userList: MutableList<userData> = userViewModel.getAllUsers()
//        Toast.makeText(context,userList.toString(),Toast.LENGTH_LONG).show()

        val adapter = userRecyclerViewAdapter(userList)
        recyclerView = binding.userRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : userRecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val navHostFragment =
                    (context as AppCompatActivity).supportFragmentManager
                        .findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.chatFragment)

//                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}