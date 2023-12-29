package com.example.chatapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.data.userData
import com.example.chatapp.repository.userRepository
import com.example.chatapp.ui.userFragment
import com.example.chatapp.userRecyclerViewAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

open class userViewModel : ViewModel() {
    private val repository: userRepository

    init{
        repository = userRepository()
    }

     fun getAllUsers():MutableList<userData>{
        return repository.getAllUsers()
    }


}