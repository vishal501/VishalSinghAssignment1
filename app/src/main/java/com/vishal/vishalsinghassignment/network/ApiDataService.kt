package com.vishal.vishalsinghassignment.network

import com.vishal.vishalsinghassignment.models.UserList
import retrofit2.Call
import retrofit2.http.GET

interface ApiDataService {
    @GET("?results=100")
    fun apiRequestsArray() : Call<UserList>
}
