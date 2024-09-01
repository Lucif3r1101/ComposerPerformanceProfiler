package com.rishav.composeperformanceprofiler.profiler


import android.util.Log

object PerformanceLogger {
    private const val TAG = "PerformanceLogger"

    fun log(eventName: String, details: String) {
        Log.d(TAG, "$eventName: $details")
    }
}
