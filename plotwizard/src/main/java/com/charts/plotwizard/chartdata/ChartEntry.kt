package com.charts.plotwizard.chartdata

import androidx.compose.ui.graphics.Color
import com.charts.plotwizard.ui.theme.Purple80

sealed class ChartEntry {
    data class RangeBar(
        val minimum: Float = 0F,
        val maximum: Float = 0F,
        val label: String = ""
    ) : ChartEntry()

    data class PieChartEntry(
        val value: Int,
        val label: String,
        val color: Color = Purple80
    ) : ChartEntry()

    data class LineChartEntry(
        val xValue:Int,
        val yValue:Float,
        val label: String,
    ):ChartEntry()

    data class CircularBarChartEntry(
        val value :Float,
        val valueLabel :String,
        val valueColor :Color,
        val maxValue :Int
    ):ChartEntry()

}




