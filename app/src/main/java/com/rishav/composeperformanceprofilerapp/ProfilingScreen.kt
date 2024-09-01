package com.rishav.composeperformanceprofilerapp


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rishav.composeperformanceprofiler.profiler.CPUProfiler
import com.rishav.composeperformanceprofiler.profiler.MemoryProfiler
import com.rishav.composeperformanceprofiler.profiler.NetworkProfiler
import com.rishav.composeperformanceprofiler.profiler.Profiler
import com.rishav.composeperformanceprofiler.profiler.SessionProfiler

@Composable
fun ProfilingScreen() {
    var counter by remember { mutableStateOf(0) }
    val recompositionCount = Profiler.trackRecompositions {
        Profiler.measureRenderTime {
            Profiler.detectLayoutOverdraw {
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(onClick = { counter++ }) {
                        Text("Click me!")
                    }
                    Text("Button clicked $counter times")
                }
            }
        }
    }
    val renderTime = Profiler.measureRenderTime { }
    val overdrawReport = Profiler.detectLayoutOverdraw { }
    val frameRate = Profiler.monitorFrameRate()
    val viewHierarchyReport = Profiler.profileViewHierarchy { }
    Profiler.startNetworkProfiling()
    Profiler.logEvent("NetworkRequest", "Fetching data...")
    Profiler.stopNetworkProfiling()
    val networkReport = NetworkProfiler.stop()
    Profiler.startMemoryProfiling()
    Profiler.logEvent("MemoryUsage", "Checking memory...")
    Profiler.stopMemoryProfiling()
    val memoryReport = MemoryProfiler.stop()
    Profiler.startCPUProfiling()
    Profiler.logEvent("CPUUsage", "Checking CPU...")
    Profiler.stopCPUProfiling()
    val cpuReport = CPUProfiler.stop()
    Profiler.startSessionProfiling()
    Profiler.logEvent("Session", "Session started...")
    Profiler.stopSessionProfiling()
    val sessionReport = SessionProfiler.stop()

    Profiler.ShowDebugOverlay(
        recompositionCount = recompositionCount,
        renderTimeMs = renderTime,
        overdrawReport = overdrawReport,
        frameRate = frameRate,
        viewHierarchyReport = viewHierarchyReport,
        networkReport = networkReport,
        memoryReport = memoryReport,
        cpuReport = cpuReport,
        sessionReport = sessionReport
    )
}
