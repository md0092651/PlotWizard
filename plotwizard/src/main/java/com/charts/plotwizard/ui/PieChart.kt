package com.charts.plotwizard.ui

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle

@Composable
internal fun PieChart(data: ChartData, modifier: Modifier, animationType: AnimationType) {
    val chartStyle = remember { data.getChartStyle() as ChartStyle.PieChartStyle }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        val animateProgress = remember { Animatable(if (animationType == AnimationType.None) 1f else 0F) }

        LaunchedEffect(
            key1 = data,
            block = { animateProgress.animateTo(1F, animationType.animation) }
        )

        Canvas(
            modifier = Modifier.size(chartStyle.chartSize),
        ) {
            var startAngle = chartStyle.startAngle
            val total = data.list.sumOf { (it as ChartEntry.PieChartEntry).value }
            data.list.forEach { chartEntry ->
                val entryValue = (chartEntry as ChartEntry.PieChartEntry).value
                val sweepAngle = (360 * entryValue.toFloat() / total.toFloat()) * animateProgress.value
                drawArc(
                    color = chartEntry.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(
                        width = chartStyle.strokeWidth.toPx(),
                        cap = chartStyle.strokeCap
                    )
                )
                drawContext.canvas.nativeCanvas.apply {
                    drawIntoCanvas {
                        val textPadding = 8.dp.toPx()
                        val arcHeight = size.height - (chartStyle.strokeWidth / 2).value
                        val arcWidth = size.width - (chartStyle.strokeWidth / 2).value
                        val path = Path().apply {
                            addArc(0f, textPadding, arcWidth, arcHeight, startAngle, sweepAngle)
                        }
                        drawTextOnPath(
                            chartEntry.label,
                            path,
                            0f,
                            0f,
                            Paint().apply {
                                textSize = chartStyle.textSize.toPx()
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }
                startAngle += sweepAngle
            }
        }
    }
}