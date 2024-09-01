package com.rishav.composeperformanceprofiler.profiler

import android.os.SystemClock

data class SessionReport(val sessionDurationMs: Long)

object SessionProfiler {
    private var sessionStartTime = 0L

    fun start() {
        sessionStartTime = SystemClock.elapsedRealtime()
    }

    fun stop(): SessionReport {
        val sessionDuration = SystemClock.elapsedRealtime() - sessionStartTime
        return SessionReport(sessionDuration)
    }
}
