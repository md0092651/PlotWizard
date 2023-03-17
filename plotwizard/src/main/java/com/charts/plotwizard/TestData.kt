package com.charts.plotwizard.chartdata

fun generateRandomHistogramList(): List<ChartEntry.RangeBar> {
    return listOf(
        ChartEntry.RangeBar(0F, 1F),
        ChartEntry.RangeBar(-10F, 7F),
        ChartEntry.RangeBar(0F, 23F),
        ChartEntry.RangeBar(-30F, 24F),
        ChartEntry.RangeBar(-12F, 12F),
        ChartEntry.RangeBar(0F, 9F),
        ChartEntry.RangeBar(0F, 14F)
    )
}