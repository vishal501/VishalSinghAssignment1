package com.vishal.vishalsinghassignment.viewmodel

import com.vishal.vishalsinghassignment.repository.MainRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vishal.vishalsinghassignment.database.entity.User


class UserViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var getAllUsers: LiveData<List<User>>? = null

    init {
        fetchAndSaveUserData()
    }

    // Method to fetch data from API and save it to the database
    private fun fetchAndSaveUserData() {
        mainRepository.saveUserData()
    }

    // Method to get all users from the database
    fun getAllUsers(): LiveData<List<User>>? {
        if (getAllUsers == null) {
            getAllUsers = mainRepository.getGetAllLocalUsers()
        }
        return getAllUsers
    }
}
