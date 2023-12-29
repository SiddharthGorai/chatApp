package com.example.chatapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class authRepository(private val application: Application) {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser?>
    private val firebaseAuth: FirebaseAuth

    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun signUp(email: String?, pass: String?) {
        firebaseAuth.createUserWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(firebaseAuth.currentUser)
                Toast.makeText(application,"Done",Toast.LENGTH_LONG).show()
            } else {

                Toast.makeText(application, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signIn(email: String?, pass: String?) {
        firebaseAuth.signInWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(firebaseAuth.currentUser)
            } else {
                Toast.makeText(application, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}
