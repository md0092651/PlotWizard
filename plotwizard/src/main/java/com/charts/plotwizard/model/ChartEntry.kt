package com.weather.temeprature.bar.model

sealed class ChartEntry {

    data class Histogram(
        val minimum : Float = 0F,
        val maximum : Float = 0F,
        val unit : String? = "°C"
    ) :ChartEntry()

    data class Column(
        val maximum : Float = 0F,
        val unit : String?
    ) :ChartEntry()

}




