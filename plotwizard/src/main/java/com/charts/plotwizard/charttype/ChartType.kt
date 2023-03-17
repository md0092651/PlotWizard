package com.charts.plotwizard.charttype

sealed class ChartType {
    object Pie : ChartType()
    object VerticalBar : ChartType()
    object HorizontalBar : ChartType()
    object RangeBar : ChartType()
    object Donut : ChartType()
    object Pyramid : ChartType()
    object Spiral : ChartType()
    object Empty:ChartType()
}





