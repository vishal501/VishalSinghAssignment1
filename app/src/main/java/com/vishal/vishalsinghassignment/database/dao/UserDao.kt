package com.vishal.vishalsinghassignment.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vishal.vishalsinghassignment.database.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<User>)


    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>
}

