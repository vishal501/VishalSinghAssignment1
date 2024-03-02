package com.vishal.vishalsinghassignment

import android.app.Application
import android.content.Context
import com.vishal.vishalsinghassignment.database.AppDatabase
import com.vishal.vishalsinghassignment.network.RetrofitClient
import com.vishal.vishalsinghassignment.repository.MainRepository

class MainApplication : Application() {
    lateinit var repo : MainRepository
    companion object {
        lateinit var appContext : Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initialize()
    }

    private fun initialize() {
        val database = AppDatabase.getInstance(applicationContext)
        repo = MainRepository(database)
    }
}