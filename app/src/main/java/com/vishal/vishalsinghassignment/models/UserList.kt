package com.vishal.vishalsinghassignment.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vishal.vishalsinghassignment.models.Result

class UserList {
    @SerializedName("results")
    @Expose
    var users: ArrayList<Result>? = null
}