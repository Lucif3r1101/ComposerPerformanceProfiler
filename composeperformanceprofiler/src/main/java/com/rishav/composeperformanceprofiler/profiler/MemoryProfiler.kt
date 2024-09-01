package com.rishav.composeperformanceprofiler.profiler

import android.os.Debug

data class MemoryReport(val totalMemoryUsageKb: Long)

object MemoryProfiler {
    private var startMemoryUsage = 0L

    fun start() {
        startMemoryUsage = Debug.getNativeHeapAllocatedSize() / 1024
    }

    fun stop(): MemoryReport {
        val totalMemoryUsage = (Debug.getNativeHeapAllocatedSize() / 1024) - startMemoryUsage
        return MemoryReport(totalMemoryUsage)
    }
}
