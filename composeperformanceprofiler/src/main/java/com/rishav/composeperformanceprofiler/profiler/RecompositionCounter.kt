package com.rishav.composeperformanceprofiler.profiler


import androidx.compose.runtime.*

@Composable
fun recompositionCounter(content: @Composable () -> Unit): Int {
    var recompositionCount by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { recompositionCount++ }
    content()
    return recompositionCount
}
