package com.rishav.composeperformanceprofiler.profiler


import android.annotation.SuppressLint
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import kotlin.system.measureNanoTime

//@Composable
//fun renderTimeMeasurer(content: @Composable () -> Unit): Long {
//    val renderTime = remember { measureNanoTime { content() } }
//    return renderTime / 1_000_000
//}


@Composable
fun renderTimeMeasurer(content: @Composable () -> Unit): Long {
    val view = LocalView.current
    val startTime = remember { System.nanoTime() }
    var renderTime by remember { mutableStateOf<Long?>(null) }

    DisposableEffect(view) {
        val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val endTime = System.nanoTime()
                renderTime = (endTime - startTime) / 1_000_000
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    content()

    return renderTime ?: 0L
}

