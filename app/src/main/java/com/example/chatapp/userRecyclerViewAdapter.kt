package com.example.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.data.userData
import com.example.chatapp.databinding.FragmentUserBinding
import com.example.chatapp.databinding.UserItemBinding

class userRecyclerViewAdapter(private val userList: MutableList<userData>) :
    RecyclerView.Adapter<userRecyclerViewAdapter.myViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return myViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)

    }

    class myViewHolder(private val binding: UserItemBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: userData) {
            binding.user.text = currentItem.userID
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

    }
}