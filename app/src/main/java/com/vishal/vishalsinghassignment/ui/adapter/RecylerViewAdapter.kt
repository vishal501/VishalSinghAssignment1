package com.vishal.vishalsinghassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vishal.vishalsinghassignment.R
import com.vishal.vishalsinghassignment.database.entity.User
import com.vishal.vishalsinghassignment.databinding.SingleListItemBinding

class RecylerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecylerViewAdapter.DeveloperViewHolder>() {

    private var userList: ArrayList<User> = ArrayList() // Initialize userList

    fun setUserList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DeveloperViewHolder {
        val mDeveloperListItemBinding: SingleListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.single_list_item,
            viewGroup,
            false
        )
        return DeveloperViewHolder(mDeveloperListItemBinding)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class DeveloperViewHolder(private val binding: SingleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()

            // Set click listener
            binding.root.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }
}

//class RecylerViewAdapter(private val listener: OnItemClickListener) :
//    RecyclerView.Adapter<RecylerViewAdapter.DeveloperViewHolder>() {
//    private var userList: ArrayList<User>? = null
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DeveloperViewHolder {
//        val mDeveloperListItemBinding: SingleListItemBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(viewGroup.context),
//            R.layout.single_list_item,
//            viewGroup,
//            false
//        )
//        return DeveloperViewHolder(mDeveloperListItemBinding)
//    }
//
//    override fun onBindViewHolder(mDeveloperViewHolder: DeveloperViewHolder, i: Int) {
//        val user: User = userList!![i]
//        mDeveloperViewHolder.mDeveloperListItemBinding.user = user
//        mDeveloperViewHolder.bind(user)
//    }
//
//    override fun getItemCount(): Int {
//        return if (userList != null) {
//            userList!!.size
//        } else {
//            0
//        }
//    }
//
//    fun setUserList(userList: ArrayList<User>) {
//        this.userList = userList
//        notifyDataSetChanged()
//    }
//
//    inner class DeveloperViewHolder(mDeveloperListItemBinding: SingleListItemBinding) :
//        RecyclerView.ViewHolder(mDeveloperListItemBinding.getRoot()) {
//        var mDeveloperListItemBinding: SingleListItemBinding
//
//        init {
//            this.mDeveloperListItemBinding = mDeveloperListItemBinding
//        }
//
//        fun bind(user: User?) {
//            // click event for selected user details & passed to UI
//            mDeveloperListItemBinding.layout.setOnClickListener { view -> listener.onItemClick(user) }
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(user: User?)
//    }
//}
