package com.charts.plotwizard

import androidx.compose.ui.graphics.Color
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.ui.theme.Pink80
import com.charts.plotwizard.ui.theme.Purple80
import com.charts.plotwizard.ui.theme.PurpleGrey80

fun getMockRangeList() = listOf(
    ChartEntry.RangeBar(0F, 5F),
    ChartEntry.RangeBar(-10F, 7F),
    ChartEntry.RangeBar(0F, 23F),
    ChartEntry.RangeBar(-30F, 24F),
    ChartEntry.RangeBar(-12F, 12F),
    ChartEntry.RangeBar(0F, 9F),
    ChartEntry.RangeBar(0F, 14F)
)

fun getMockPieList()= listOf(
    ChartEntry.PieChartEntry(10, "A", Color.Yellow),
    ChartEntry.PieChartEntry(27, "B",Color.Green),
    ChartEntry.PieChartEntry(80, "C",Color.Magenta),
)