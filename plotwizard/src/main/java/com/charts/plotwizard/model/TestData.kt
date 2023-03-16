package com.charts.plotwizard.model

import com.weather.temeprature.bar.model.ChartEntry

fun generateRandomTemperatureList(): List<ChartEntry.Histogram> {
    return listOf(
        ChartEntry.Histogram(0F, 1F),
        ChartEntry.Histogram(-10F, 7F),
        ChartEntry.Histogram(0F, 23F),
        ChartEntry.Histogram(-30F, 24F),
        ChartEntry.Histogram(-12F, 12F),
        ChartEntry.Histogram(0F, 9F),
        ChartEntry.Histogram(0F, 14F)
    )
}