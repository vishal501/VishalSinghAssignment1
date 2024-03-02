package com.vishal.vishalsinghassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vishal.vishalsinghassignment.R
import com.vishal.vishalsinghassignment.database.entity.User
import com.vishal.vishalsinghassignment.databinding.SingleListItemBinding

class RecyclerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.SampleViewHolder>() {

    private var userList: ArrayList<User> = ArrayList()
    private var filteredList: ArrayList<User> = ArrayList()

    init {
        filteredList.addAll(userList)
    }

    fun setUserList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        filter("")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_list_item, parent, false)
        return SampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val user = filteredList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    //filter method for users
    fun filter(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(userList)
        } else {
            for (user in userList) {
                if (user.name!!.contains(query, true)) {
                    filteredList.add(user)
                }
            }
        }
        notifyDataSetChanged()
    }

    inner class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: SingleListItemBinding = SingleListItemBinding.bind(itemView)

        fun bind(user: User) {
            binding.user = user
            binding.root.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }
}

