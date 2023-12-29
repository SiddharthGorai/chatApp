package com.example.chatapp.repository

import android.service.autofill.UserData
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.userData
import com.example.chatapp.ui.MainActivity
import com.example.chatapp.viewModel.userViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class userRepository {
//    private val userID = FirebaseAuth.getInstance().currentUser!!.uid
    private lateinit var databaseRef: DatabaseReference
    var userList: MutableList<userData> = mutableListOf()


    fun getAllUsers(): MutableList<userData> {
        databaseRef = FirebaseDatabase.getInstance().getReference("users")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val userName: String = userSnapshot.value.toString()
                        if (!userList.contains(userData(userName))) {
                            userList.add(
                                userData(
                                    userName
                                )
                            )
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return userList

    }
}


