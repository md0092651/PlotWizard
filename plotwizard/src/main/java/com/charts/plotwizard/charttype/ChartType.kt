package com.charts.plotwizard.charttype

sealed class ChartType {
    object Pie : ChartType()
    object RangeBar : ChartType()
    object Line : ChartType()
    object Spiral : ChartType()
    object Empty:ChartType()
}





