package com.rishav.composeperformanceprofilerapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rishav.composeperformanceprofiler.profiler.CPUReport
import com.rishav.composeperformanceprofiler.profiler.MemoryReport
import com.rishav.composeperformanceprofiler.profiler.NetworkProfiler
import com.rishav.composeperformanceprofiler.profiler.NetworkReport
import com.rishav.composeperformanceprofiler.profiler.OverdrawReport
import com.rishav.composeperformanceprofiler.profiler.Profiler
import com.rishav.composeperformanceprofiler.profiler.SessionReport
import com.rishav.composeperformanceprofiler.profiler.ViewHierarchyReport

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun ProfilingScreen() {
    var counter by remember { mutableStateOf(0) }
    var networkTime by remember { mutableStateOf<Long?>(null) }
    val scope = rememberCoroutineScope()

    var recompositionCount by remember { mutableStateOf(0) }
    var renderTime by remember { mutableStateOf(0L) }
    var overdrawReport by remember { mutableStateOf(OverdrawReport(0)) }
    var frameRate by remember { mutableStateOf(0) }
    var viewHierarchyReport by remember { mutableStateOf(ViewHierarchyReport(0)) }
    var networkReport by remember { mutableStateOf(NetworkReport(0, 0L)) }
    var memoryReport by remember { mutableStateOf(MemoryReport(0L)) }
    var cpuReport by remember { mutableStateOf(CPUReport(0f)) }
    var sessionReport by remember { mutableStateOf(SessionReport(0L)) }

    Profiler.startSessionProfiling()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Compose Performance Profiler",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "This screen demonstrates how the profiler monitors various aspects of your Jetpack Compose app in real-time. Below are buttons and text that trigger different profiling events.",
                fontSize = 16.sp
            )

            Text(
                text = "1. Button Clicks and Recomposition Tracking",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Text(
                text = "Click the button below to trigger recompositions and increase the click count.",
                fontSize = 14.sp
            )
            Button(onClick = { counter++ }) {
                Text("Click me!")
            }
            Text("Button clicked $counter times")

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "2. Simulate Network Request",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Text(
                text = "This simulates a network request and measures its performance.",
                fontSize = 14.sp
            )
            Button(onClick = {
                scope.launch {
                    Profiler.startNetworkProfiling()
                    networkTime = simulateNetworkRequest()
                    networkReport = NetworkProfiler.stop()
                }
            }) {
                Text("Simulate Network Request")
            }
            networkTime?.let { Text("Network request took $it ms") }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "3. View and Layout Profiling",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Text(
                text = "The profiler automatically tracks view hierarchy, frame rate, overdraw, and memory/CPU usage.",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            recompositionCount = Profiler.trackRecompositions {
                renderTime = Profiler.measureRenderTime {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Interactive Elements",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Button(onClick = { counter++ }) {
                            Text("Click me again!")
                        }
                        Text("Button clicked $counter times")

                        Button(onClick = {
                            scope.launch {
                                Profiler.startNetworkProfiling()
                                networkTime = simulateNetworkRequest()
                                networkReport = NetworkProfiler.stop()
                            }
                        }) {
                            Text("Simulate Another Network Request")
                        }
                        networkTime?.let { Text("Network request took $it ms") }
                    }
                }
                overdrawReport = Profiler.detectLayoutOverdraw { }
                frameRate = Profiler.monitorFrameRate()
                viewHierarchyReport = Profiler.profileViewHierarchy { }

                memoryReport = MemoryReport(totalMemoryUsageKb = 1024L)
                cpuReport = CPUReport(cpuUsagePercentage = 15f)
                sessionReport = SessionReport(sessionDurationMs = 2000L)
            }

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
    }
}

suspend fun simulateNetworkRequest(): Long {
    val startTime = System.currentTimeMillis()
    delay(1000L)
    return System.currentTimeMillis() - startTime
}

