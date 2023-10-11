package com.charts.plotwizard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.chartstyle.ChartStyle.DefaultStyle
import com.charts.plotwizard.charttype.ChartType
import kotlinx.coroutines.launch

/**
 * Composable function to create a chart using Jetpack Compose
 * @param chartListData chart list data for the chart
 * @param chartStyle chart style configuration for the chart
 * @param animationType Animation type. Default is AnimationType.None
 * @param modifier
 */
@Composable
fun Chart(
    modifier: Modifier = Modifier,
    chartListData: List<ChartEntry>,
    chartStyle: ChartStyle = DefaultStyle(),
    animationType: AnimationType = AnimationType.None,
) {
    val animateProgress = remember { animationType.animationInitialProgress }

    LaunchedEffect(key1 = chartListData, block = {
        animateProgress.animateTo(1F, animationType.animation)
    })

    val chartData = remember { ChartData(chartListData, chartStyle) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        val coroutineScope = rememberCoroutineScope()
        Spacer(
            modifier = getModifier(chartData.getChartType(), chartStyle)
                .clickable {
                    coroutineScope.launch {
                        animationType.animationInitialProgress.snapTo(0f)
                        animationType.animationInitialProgress.animateTo(1f, animationType.animation)
                    }
                }
                .drawWithCache {
                    onDrawBehind {
                        when (chartData.getChartType()) {
                            ChartType.RangeBar -> RangeChartPainter(chartData, animateProgress.value).drawPoint(this)
                            ChartType.Pie -> PieChartPainter(chartData, animateProgress.value).drawPoint(this)
                            ChartType.Line -> LineChartPainter(chartData, animateProgress.value).drawPoint(this)
                            ChartType.CircularBar -> CircularBarChartPainter(chartData, animateProgress.value).drawPoint(this)
                            ChartType.SingleHorizontalBar -> Unit // SingleHorizontalRangeBar(painter = painter).drawPoint(this)
                            else -> Unit
                        }
                    }
                },
        )
    }
}

fun getModifier(chartType: ChartType, chartStyle: ChartStyle): Modifier {
    return when (chartType) {
        ChartType.CircularBar -> Modifier.size((chartStyle as ChartStyle.PieChartStyle).chartSize)
        ChartType.Empty -> Modifier.fillMaxSize()
        ChartType.Line -> Modifier.fillMaxSize()
        ChartType.Pie -> Modifier.size((chartStyle as ChartStyle.PieChartStyle).chartSize)
        ChartType.RangeBar -> Modifier.fillMaxSize()
        ChartType.SingleHorizontalBar -> Modifier.fillMaxSize()
    }
}
