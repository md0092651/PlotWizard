package com.charts.plotwizard.chartdata

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.charts.plotwizard.ui.theme.Purple40
import com.charts.plotwizard.ui.theme.Purple80

sealed class ChartEntry {

    data class RangeBar(
        val minimum: Float = 0F,
        val maximum: Float = 0F,
        val label: String = ""
    ) : ChartEntry()

    data class ColourfulRangeBar(
        val minimum: Float = 0F,
        val maximum: Float = 0F,
        val label: String = "",
        val brush: List<Color> = listOf(Purple80, Purple40),
    ) : ChartEntry()

    data class PieChartEntry(
        val label: String,
        val value: Float
    ) : ChartEntry()
}




