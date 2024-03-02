package com.vishal.vishalsinghassignment.repository

import com.vishal.vishalsinghassignment.database.AppDatabase
import com.vishal.vishalsinghassignment.utils.AppExecutors
import com.vishal.vishalsinghassignment.network.RetrofitClient.service
import com.vishal.vishalsinghassignment.models.UserList
import com.vishal.vishalsinghassignment.models.Result
import android.app.Application
import androidx.lifecycle.LiveData
import com.vishal.vishalsinghassignment.database.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainRepository(private val database: AppDatabase?) {


    private var getAllLocalUsers: LiveData<List<User>>? = null


    fun getGetAllLocalUsers(): LiveData<List<User>>? {
        getAllLocalUsers = database?.userDao()?.getAllUsers()
//        Log.d("data--",getAllLocalUsers.toString())
        return getAllLocalUsers
    }

    // getting all users data from api call
    fun saveUserData() {
        val userDataService = service
        userDataService.apiRequestsArray().enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                // all users fetched from Api call
                val userList: List<Result>? = response.body()!!.users
                createUserDataTable(userList)
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {}
        })
    }

    // storing all values needed for UI in User Table from API data
    private fun createUserDataTable(userList: List<Result>?) {
        val localUsers = ArrayList<User>()
        for (userData in userList!!) {
            val user = User(
                userData.name!!.first,
                userData.picture!!.large,
                userData.email,
                userData.location!!.country,
                userData.registered!!.date.toString(),
                userData.dob!!.date.toString(),
                userData.location!!.city,
                userData.location!!.state,
                userData.location!!.postcode,
                userData.dob!!.age.toString()
            )
            localUsers.add(user)
        }

        // Using Executor service for inserting user data in background
        AppExecutors().diskIO().execute {
            val userDao = database?.userDao()
            userDao?.insert(localUsers)
        }
    }



}


