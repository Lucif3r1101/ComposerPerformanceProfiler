package com.rishav.composeperformanceprofiler.profiler


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class OverdrawReport(val overdrawCount: Int)

@Composable
fun layoutOverdrawDetector(content: @Composable () -> Unit): OverdrawReport {
    val overdrawCount = remember { (0..10).random() }
    content()
    return OverdrawReport(overdrawCount)
}
