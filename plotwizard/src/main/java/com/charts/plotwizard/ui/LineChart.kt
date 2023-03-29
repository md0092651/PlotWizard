package com.charts.plotwizard.ui

import android.graphics.PointF
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.ui.theme.Pink80
import kotlinx.coroutines.launch

@Composable
internal fun LineChart(data: ChartData, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val coroutineScope = rememberCoroutineScope()
        val style : ChartStyle.LineChartStyle = data.getChartStyle() as ChartStyle.LineChartStyle
        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .align(Alignment.Center)
                .clickable {
                    coroutineScope.launch {
                        data.animateProgress.snapTo(0f)
                        data.animateProgress.animateTo(1f, data.animation)
                    }
                }
                .drawWithCache {
                    val path = generateSmoothPath(data, size)
                    val filledPath = generateSmoothPath(data, size)

                    filledPath.relativeLineTo(0f, size.height)
                    filledPath.lineTo(0f, size.height)
                    filledPath.close()

                    onDrawBehind {

                        if(!style.hideGridLine){
                            GridLinePainter(style.gridStyle).drawPoint(this)
                        }

                        clipRect(right = size.width *  data.animateProgress.value) {
                            drawPath(path, Pink80, style = Stroke(2.dp.toPx()))
                            drawPath(
                                filledPath,
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Pink80.copy(alpha = 0.4f),
                                        Color.Transparent
                                    )
                                ),
                                style = Fill
                            )
                        }
                    }
                })
    }
}


fun generateSmoothPath(data: ChartData, size: Size): Path {
    val path = Path()
    val numberEntries = data.numberOfBars - 1
    val xWidth = size.width / numberEntries

    val range = data.getHighestValue() - data.getMinimumValue()
    val heightPxPerAmount = size.height / range

    var lastXValue = 0f
    var lastYValue = size.height
    data.list.forEachIndexed { i, entry ->
        if (i == 0) {
            path.moveTo(
                0f,
                size.height - ((entry as ChartEntry.LineChartEntry).yValue - data.getMinimumValue()) * heightPxPerAmount
            )
        }
        val xIncrement = i * xWidth
        val yIncrement = size.height - ((entry as ChartEntry.LineChartEntry).yValue - data.getMinimumValue()) *
                heightPxPerAmount
        val firstPoint = PointF((xIncrement + lastXValue) / 2f, lastYValue)
        val secondPoint = PointF((xIncrement + lastXValue) / 2f, yIncrement)
        path.cubicTo(
            firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y,
            xIncrement, yIncrement
        )
        lastXValue = xIncrement
        lastYValue = yIncrement
    }
    return path
}