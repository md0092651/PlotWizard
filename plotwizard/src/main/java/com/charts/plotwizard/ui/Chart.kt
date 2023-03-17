package com.charts.plotwizard.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.chartstyle.ChartStyle.DefaultStyle
import com.charts.plotwizard.charttype.ChartType

/**
 * Composable function to create a chart using Jetpack Compose
 * @param chartData chart data for the chart
 * @param barChartStyle chart style configuration for the chart
 * @param animate boolean indicating whether to animate the chart
 * @param animationType type of animation to use for the chart
 */
@Composable
fun Chart(
    chartListData: List<ChartEntry>,
    chartStyle: ChartStyle = DefaultStyle(),
    animationType: AnimationType = AnimationType.None,
    modifier: Modifier = Modifier
) {
    val chartData = remember { ChartData(chartListData,chartStyle) }
    when(chartData.getChartType()){
        ChartType.RangeBar-> RangeChart(data = chartData, modifier = modifier,animationType)
        else -> Unit
    }
}

interface ChartPainter {
    fun drawPoint(
        drawScope: DrawScope,
        canvas: Canvas,
        center: Offset
    )
}

class BarChartPainter() : ChartPainter{
    override fun drawPoint(drawScope: DrawScope, canvas: Canvas, center: Offset) {
        TODO("Not yet implemented")
    }
}


