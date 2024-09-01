package com.rishav.composeperformanceprofiler.profiler


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DebugOverlay(
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.7f))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = """
                Recompositions: $recompositionCount
                Render Time: $renderTimeMs ms
                Overdraw Count: ${overdrawReport.overdrawCount}
                Frame Rate: $frameRate FPS
                View Depth: ${viewHierarchyReport.depth}
                Network Requests: ${networkReport.requestCount}, Avg Time: ${networkReport.avgRequestTimeMs} ms
                Memory Usage: ${memoryReport.totalMemoryUsageKb} KB
                CPU Usage: ${cpuReport.cpuUsagePercentage} %
                Session Duration: ${sessionReport.sessionDurationMs} ms
            """.trimIndent(),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
