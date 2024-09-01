package com.rishav.composeperformanceprofiler.profiler


data class NetworkReport(val requestCount: Int, val avgRequestTimeMs: Long)

object NetworkProfiler {
    private var requestCount = 0
    private var totalRequestTime = 0L

    fun start() {
        requestCount = 0
        totalRequestTime = 0L
    }

    fun logRequest(timeMs: Long) {
        requestCount++
        totalRequestTime += timeMs
    }

    fun stop(): NetworkReport {
        val avgRequestTime = if (requestCount > 0) totalRequestTime / requestCount else 0
        return NetworkReport(requestCount, avgRequestTime)
    }
}
