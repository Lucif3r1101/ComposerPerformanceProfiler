package com.rishav.composeperformanceprofiler.profiler


import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun frameRateMonitor(): Int {
    var frameRate by remember { mutableIntStateOf(60) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            frameRate = (30..60).random()
        }
    }
    return frameRate
}
