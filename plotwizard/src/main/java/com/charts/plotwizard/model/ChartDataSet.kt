package com.weather.temeprature.bar.model

class ChartDataSet(private val list: List<ChartEntry>) {

    val numberOfBars = list.size

    private fun getChartType(): Chart {
        return when {
            list.all { it is ChartEntry.Column } -> Chart.COLUMCHART
            list.all { it is ChartEntry.Histogram } -> Chart.HISTOGRAM
            else -> Chart.UNKNOWN
        }
    }

    fun getHighestValue(): Float {
        return when (getChartType()) {
            Chart.HISTOGRAM -> list.maxOf { (it as ChartEntry.Histogram).maximum }
            Chart.COLUMCHART -> list.maxOf { (it as ChartEntry.Column).maximum }
            Chart.UNKNOWN -> 0F
        }
    }

    fun getHighestValueAtIndex(position:Int): Float {
        return when (getChartType()) {
            Chart.HISTOGRAM -> (list[position] as ChartEntry.Histogram).maximum
            Chart.COLUMCHART -> (list[position] as ChartEntry.Column).maximum
            Chart.UNKNOWN -> 0F
        }
    }

    fun getLowestValueAtIndex(position:Int): Float {
        return when (getChartType()) {
            Chart.HISTOGRAM -> (list[position] as ChartEntry.Histogram).minimum
            Chart.COLUMCHART -> 0F
            Chart.UNKNOWN -> 0F
        }
    }

    fun getMinimumValue(): Float {
        return when (getChartType()) {
            Chart.HISTOGRAM -> list.minOf { (it as ChartEntry.Histogram).minimum }
            Chart.COLUMCHART -> 0F
            Chart.UNKNOWN -> 0F
        }
    }

    enum class Chart{
        HISTOGRAM,
        COLUMCHART,
        UNKNOWN
    }
}