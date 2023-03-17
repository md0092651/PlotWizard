package com.charts.plotwizard.chartdata

import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.charttype.ChartType

internal class ChartData(val list: List<ChartEntry>,val chartStyle: ChartStyle) {

    val numberOfBars = list.size

    internal fun getChartType(): ChartType {
        return when {
            list.all { it is ChartEntry.RangeBar } -> ChartType.RangeBar
            else -> ChartType.RangeBar
        }
    }

    internal fun getChartStyle(): ChartStyle {
        return if(chartStyle == ChartStyle.DefaultStyle()){
            when {
                list.all { it is ChartEntry.RangeBar } -> ChartStyle.BarChartStyle()
                else -> ChartStyle.DefaultStyle()
            }
        }else {
            chartStyle
        }
    }

    internal fun getHighestValue(): Float {
        return when (getChartType()) {
            ChartType.RangeBar-> list.maxOf { (it as ChartEntry.RangeBar).maximum }
            else -> 0F
        }
    }

    internal fun getHighestValueAtIndex(position:Int): Float {
        return when (getChartType()) {
            ChartType.RangeBar -> (list[position] as ChartEntry.RangeBar).maximum
            else -> 0F
        }
    }

    internal fun getLowestValueAtIndex(position:Int): Float {
        return when (getChartType()) {
            ChartType.RangeBar -> (list[position] as ChartEntry.RangeBar).minimum
            else -> 0F
        }
    }

    internal fun getMinimumValue(): Float {
        return when (getChartType()) {
            ChartType.RangeBar -> list.minOf { (it as ChartEntry.RangeBar).minimum }
            else -> 0F
        }
    }
}