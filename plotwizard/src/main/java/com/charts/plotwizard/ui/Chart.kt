package com.charts.plotwizard.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
 * @param chartListData chart list data for the chart
 * @param chartStyle chart style configuration for the chart
 * @param animationType Animation type. Default is AnimationType.None
 * @param modifier
 */
@Composable
fun Chart(
    modifier: Modifier = Modifier,
    chartListData: List<ChartEntry>,
    chartStyle: ChartStyle = DefaultStyle(),
    animationType: AnimationType = AnimationType.None,
) {
    val animateProgress = remember {
        if (animationType == AnimationType.None) {
            Animatable(1f)
        } else {
            Animatable(0f)
        }
    }
    LaunchedEffect(key1 = chartListData, block = {
        animateProgress.animateTo(1F, animationType.animation)
    })
    val chartData = remember { ChartData(chartListData,chartStyle,animateProgress,animationType.animation) }
    when(chartData.getChartType()){
        ChartType.RangeBar-> RangeChart(data = chartData, modifier = modifier)
        ChartType.Pie-> PieChart(data = chartData, modifier = modifier)
        ChartType.Line-> LineChart(data = chartData, modifier = modifier)
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


