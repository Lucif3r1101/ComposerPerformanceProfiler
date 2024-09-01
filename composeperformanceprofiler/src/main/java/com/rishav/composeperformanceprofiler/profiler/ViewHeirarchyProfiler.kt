package com.rishav.composeperformanceprofiler.profiler


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class ViewHierarchyReport(val depth: Int)

@Composable
fun viewHierarchyProfiler(content: @Composable () -> Unit): ViewHierarchyReport {
    val depth = remember { (1..5).random() }
    content()
    return ViewHierarchyReport(depth)
}
