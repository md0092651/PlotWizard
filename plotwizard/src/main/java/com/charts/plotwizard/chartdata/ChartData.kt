package com.charts.plotwizard.chartdata

import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.charttype.ChartType

class ChartData(val list: List<ChartEntry>, private val chartStyle: ChartStyle) {

    val numberOfBars = list.size

    internal fun getChartType(): ChartType {
        return when {
            list.all { it is ChartEntry.RangeBar } -> ChartType.RangeBar
            list.all { it is ChartEntry.PieChartEntry } -> ChartType.Pie
            list.all { it is ChartEntry.LineChartEntry } -> ChartType.Line
            else -> ChartType.Empty
        }
    }

    internal fun getChartStyle(): ChartStyle {
        return if(chartStyle == ChartStyle.DefaultStyle()){
            when {
                list.all { it is ChartEntry.RangeBar } -> ChartStyle.BarChartStyle()
                list.all { it is ChartEntry.PieChartEntry } -> ChartStyle.PieChartStyle()
                list.all { it is ChartEntry.LineChartEntry } -> ChartStyle.LineChartStyle()
                else -> ChartStyle.BarChartStyle()
            }
        }else {
            chartStyle
        }
    }

    internal fun getHighestValue(): Float {
        return when (getChartType()) {
            ChartType.RangeBar-> list.maxOf { (it as ChartEntry.RangeBar).maximum }
            ChartType.Line-> list.maxOf { (it as ChartEntry.LineChartEntry).yValue }
            else -> 0F
        }
    }

    internal fun getHighestValueAtIndex(position:Int): Float {
        return when (getChartType()) {
            ChartType.RangeBar -> (list[position] as ChartEntry.RangeBar).maximum
            ChartType.Line -> (list[position] as ChartEntry.LineChartEntry).yValue
            else -> 0F
        }
    }

    internal fun getLowestValueAtIndex(position:Int): Float {
        return when (getChartType()) {
            ChartType.RangeBar -> (list[position] as ChartEntry.RangeBar).minimum
            ChartType.Line -> (list[position] as ChartEntry.LineChartEntry).yValue
            else -> 0F
        }
    }

    internal fun getMinimumValue(): Float {
        return when (getChartType()) {
            ChartType.RangeBar -> list.minOf { (it as ChartEntry.RangeBar).minimum }
            ChartType.Line -> list.minOf { (it as ChartEntry.LineChartEntry).yValue }
            else -> 0F
        }
    }
}