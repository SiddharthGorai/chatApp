package com.example.chatapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.repository.authRepository
import com.google.firebase.auth.FirebaseUser

class authViewModel(application: Application) : AndroidViewModel(application) {
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser?>
    val currentUser: FirebaseUser?
    private val repository: authRepository


    init {
        repository = authRepository(application)
        currentUser = repository.currentUser
        firebaseUserMutableLiveData = repository.firebaseUserMutableLiveData
    }

    fun signUp(email: String?, pass: String?) {
        repository.signUp(email, pass)
    }

    fun signIn(email: String?, pass: String?) {
        repository.signIn(email, pass)
    }

    fun signOut() {
        repository.signOut()
    }
}
