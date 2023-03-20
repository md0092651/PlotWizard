package com.charts.plotwizard.charttype

sealed class ChartType {
    object Pie : ChartType()
    object RangeBar : ChartType()
    object Line : ChartType()
    object CircularBar : ChartType()
    object Empty:ChartType()
}





