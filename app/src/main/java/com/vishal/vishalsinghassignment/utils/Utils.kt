package com.vishal.vishalsinghassignment.utils

import java.text.SimpleDateFormat
import java.util.Date

object Utils {
    // getting date in a particular format for displaying on UI.
    fun getDate(_date: String): String {
        val inputFormat = SimpleDateFormat("E MMM dd HH:mm:ss 'GMT'Z yyyy")
        val outputFormat1 = SimpleDateFormat("MMM dd, yyyy hh:mm a")
        val outputFormat2 = SimpleDateFormat("MMM dd, yyyy")
        return try {
            val date = inputFormat.parse(_date)
            val todayTime = outputFormat1.format(date)
            val yesterdayTime = outputFormat1.format(Date(date.time - 2 * 24 * 60 * 60 * 1000))
            val twoDaysAgo = "2 days ago"
            val monthDateYear = outputFormat2.format(date)
            println("Today, time(hour:minute): $todayTime")
            println("Yesterday, hour:minute: $yesterdayTime")
            println("2 days ago: $twoDaysAgo")
            println("Month date, year: $monthDateYear")
            monthDateYear
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}