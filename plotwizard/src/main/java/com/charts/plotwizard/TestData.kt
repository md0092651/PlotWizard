package com.charts.plotwizard

import androidx.compose.ui.graphics.Color
import com.charts.plotwizard.chartdata.ChartEntry
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
    ChartEntry.PieChartEntry(80, "C",Color.Cyan),
    ChartEntry.PieChartEntry(30, "D", Pink80),
)

fun getMockLineList() = listOf(
    ChartEntry.LineChartEntry(1, 100F,"A"),
    ChartEntry.LineChartEntry(2, 200F,"B"),
    ChartEntry.LineChartEntry(3, 300F,"C"),
    ChartEntry.LineChartEntry(4, 100F,"D"),
    ChartEntry.LineChartEntry(5, 600F,"E"),
    ChartEntry.LineChartEntry(6, 50F,"F"),
    ChartEntry.LineChartEntry(7, 750F,"G")
)