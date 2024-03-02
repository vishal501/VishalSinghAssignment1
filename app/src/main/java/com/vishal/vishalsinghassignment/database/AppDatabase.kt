package com.vishal.vishalsinghassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.vishal.vishalsinghassignment.database.dao.UserDao
import com.vishal.vishalsinghassignment.database.entity.User



@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private const val DATABASE_NAME = "AppDatabase"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        // getting database instance for db transactions
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE

        }
    }
}