package com.charts.plotwizard

import androidx.compose.ui.graphics.Color
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.ui.theme.C1
import com.charts.plotwizard.ui.theme.C2
import com.charts.plotwizard.ui.theme.C3
import com.charts.plotwizard.ui.theme.Pink80

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
    ChartEntry.PieChartEntry(40, "A", Color.Black),
    ChartEntry.PieChartEntry(27, "B",Color.Red),
    ChartEntry.PieChartEntry(80, "C", C2),
    ChartEntry.PieChartEntry(30, "D", Pink80),
)

fun getMockLineList() = listOf(
    ChartEntry.LineChartEntry(1, 100F,"A"),
    ChartEntry.LineChartEntry(2, 200F,"B"),
    ChartEntry.LineChartEntry(3, 270F,"C"),
    ChartEntry.LineChartEntry(4, 390F,"D"),
    ChartEntry.LineChartEntry(5, 439F,"E"),
    ChartEntry.LineChartEntry(6, 230F,"E"),
    ChartEntry.LineChartEntry(7, 780F,"E"),
    ChartEntry.LineChartEntry(8, 659F,"E"),
    ChartEntry.LineChartEntry(9, 880F,"E"),
    ChartEntry.LineChartEntry(10, 919F,"E"),
    ChartEntry.LineChartEntry(11, 1000F,"E"),
)

fun getMockSpiralChartList() = listOf(
    ChartEntry.SpiralChartEntry(value = 100F,"A", C1,200),
    ChartEntry.SpiralChartEntry(value = 80F,"A", C2,200),
    ChartEntry.SpiralChartEntry(value = 150F,"A", C3,200),
)