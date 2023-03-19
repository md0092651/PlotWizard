package com.charts.plotwizard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle
import kotlinx.coroutines.launch

@Composable
internal fun SpiralChart(data: ChartData, modifier: Modifier) {
    val chartStyle = remember { data.getChartStyle() as ChartStyle.PieChartStyle }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {

        val coroutineScope = rememberCoroutineScope()

        Spacer(
            modifier = Modifier
                .size(chartStyle.chartSize)
                .clickable {
                    coroutineScope.launch {
                        data.animateProgress.snapTo(0f)
                        data.animateProgress.animateTo(1f, data.animation)
                    }
                }
                .drawWithCache {
                    onDrawBehind {

                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        val centerX = canvasWidth / 2
                        val centerY = canvasHeight / 2
                        val radiusIncrement = 70
                        var currentRadius = radiusIncrement.toFloat()
                        var currentAngle = chartStyle.startAngle


                        val total = data.getHighestValue()

                        data.list.forEach { chartEntry ->
                            val entryValue = (chartEntry as ChartEntry.SpiralChartEntry).value
                            val sweepAngle = (360 * entryValue / total) * data.animateProgress.value
                            val arcWidth = radiusIncrement /1.2
                            drawArc(
                                color = Color.LightGray.copy(alpha = 0.2F),
                                startAngle = 0F,
                                sweepAngle = 360F,
                                useCenter = false,
                                topLeft = Offset(centerX - currentRadius, centerY - currentRadius),
                                size = Size(currentRadius * 2, currentRadius * 2),
                                style = Stroke(width = arcWidth.toFloat()),
                                alpha = 1.0f,
                            )

                            drawArc(
                                color = chartEntry.valueColor,
                                startAngle = currentAngle,
                                sweepAngle = sweepAngle,
                                useCenter = false,
                                topLeft = Offset(centerX - currentRadius, centerY - currentRadius),
                                size = Size(currentRadius * 2, currentRadius * 2),
                                style = Stroke(width = arcWidth.toFloat()),
                                alpha = 1.0f,
                            )
                            currentRadius += radiusIncrement
                        }
                    }
                }
        )
    }
}