package com.rishav.composeperformanceprofiler.profiler


import android.os.Process
import android.os.SystemClock

data class CPUReport(val cpuUsagePercentage: Float)

object CPUProfiler {
    private var startCpuTime = 0L

    fun start() {
        startCpuTime = Process.getElapsedCpuTime()
    }

    fun stop(): CPUReport {
        val endCpuTime = Process.getElapsedCpuTime()
        val cpuUsageTime = endCpuTime - startCpuTime
        val elapsedTime = SystemClock.elapsedRealtime()
        val cpuUsagePercentage = (cpuUsageTime / elapsedTime.toFloat()) * 100
        return CPUReport(cpuUsagePercentage)
    }
}
