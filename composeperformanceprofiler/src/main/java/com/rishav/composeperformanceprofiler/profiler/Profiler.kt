package com.rishav.composeperformanceprofiler.profiler

import androidx.compose.runtime.Composable

object Profiler {

    @Composable
    fun trackRecompositions(content: @Composable () -> Unit): Int {
        return recompositionCounter(content)
    }

    @Composable
    fun measureRenderTime(content: @Composable () -> Unit): Long {
        return renderTimeMeasurer(content)
    }

    @Composable
    fun detectLayoutOverdraw(content: @Composable () -> Unit): OverdrawReport {
        return layoutOverdrawDetector(content)
    }

    @Composable
    fun monitorFrameRate(): Int {
        return frameRateMonitor()
    }

    @Composable
    fun profileViewHierarchy(content: @Composable () -> Unit): ViewHierarchyReport {
        return viewHierarchyProfiler(content)
    }

    fun logEvent(eventName: String, details: String) {
        PerformanceLogger.log(eventName, details)
    }

    fun startNetworkProfiling() {
        NetworkProfiler.start()
    }

    fun stopNetworkProfiling() {
        NetworkProfiler.stop()
    }

    fun startMemoryProfiling() {
        MemoryProfiler.start()
    }

    fun stopMemoryProfiling() {
        MemoryProfiler.stop()
    }

    fun startCPUProfiling() {
        CPUProfiler.start()
    }

    fun stopCPUProfiling() {
        CPUProfiler.stop()
    }

    fun startSessionProfiling() {
        SessionProfiler.start()
    }

    fun stopSessionProfiling() {
        SessionProfiler.stop()
    }

    @Composable
    fun ShowDebugOverlay(
        recompositionCount: Int,
        renderTimeMs: Long,
        overdrawReport: OverdrawReport,
        frameRate: Int,
        viewHierarchyReport: ViewHierarchyReport,
        networkReport: NetworkReport,
        memoryReport: MemoryReport,
        cpuReport: CPUReport,
        sessionReport: SessionReport
    ) {
        DebugOverlay(
            recompositionCount = recompositionCount,
            renderTimeMs = renderTimeMs,
            overdrawReport = overdrawReport,
            frameRate = frameRate,
            viewHierarchyReport = viewHierarchyReport,
            networkReport = networkReport,
            memoryReport = memoryReport,
            cpuReport = cpuReport,
            sessionReport = sessionReport
        )
    }
}
