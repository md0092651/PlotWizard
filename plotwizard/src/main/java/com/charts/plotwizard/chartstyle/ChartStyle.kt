package com.charts.plotwizard.chartstyle

import androidx.compose.ui.graphics.Color
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.ui.theme.Purple40
import com.charts.plotwizard.ui.theme.Purple80


sealed class ChartStyle{

    data class BarChartStyle(
        val xAxisLineColor: Color = Color.Black,
        val yAxisLineColor: Color = Color.Black,
        val xAxisLabelColor: Color = Color.Black,
        val yAxisLabelColor: Color = Color.Black,
        val xLabel :String = "",
        val yLabel :String = "",
        val chartBrush: List<Color> = listOf(Purple80, Purple40),
        val generateRandomColor:Boolean = false,
        val hideXYLine:Boolean = false
    ): ChartStyle()

    data class DefaultStyle(
        val xAxisLineColor: Color = Color.Black,
        val yAxisLineColor: Color = Color.Black,
        val xAxisLabelColor: Color = Color.Black,
        val yAxisLabelColor: Color = Color.Black,
        val xLabel :String = "",
        val yLabel :String = "",
        val chartBrush: List<Color> = listOf(Purple80, Purple40),
        val generateRandomColor:Boolean = false,
        val hideXYLine:Boolean = false
    ) : ChartStyle()
}

