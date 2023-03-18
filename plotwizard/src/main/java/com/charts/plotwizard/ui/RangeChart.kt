package com.charts.plotwizard.ui

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.ui.theme.Purple80
import kotlin.math.abs

@Composable
internal fun RangeChart(
    data: ChartData,
    modifier: Modifier = Modifier,
    animationType: AnimationType
) {
    Box(modifier = modifier.fillMaxSize()) {

        val chartStyle = remember {
            data.getChartStyle() as ChartStyle.BarChartStyle
        }

        val animateProgress = remember {
            if (animationType == AnimationType.None) {
                Animatable(1f)
            } else {
                Animatable(0f)
            }
        }

        LaunchedEffect(key1 = data, block = {
            animateProgress.animateTo(1F, animationType.animation)
        })

        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val padding = chartStyle.padding

            val xIncrement = (canvasWidth - 2 * padding) / (data.numberOfBars - 1)
            val yIncrement = ((canvasHeight - 2 * padding) / (abs(data.getMinimumValue()) + abs(data.getHighestValue()))) * animateProgress.value

            val referenceBarThickness = chartStyle.barThickness

            for (i in 0 until data.numberOfBars) {

                val yUpper = padding + yIncrement * (data.getHighestValue() - data.getHighestValueAtIndex(i))
                val yLower = padding + yIncrement * (data.getHighestValue() - data.getLowestValueAtIndex(i))

                val h = (yUpper - yLower) * animateProgress.value
                val w = referenceBarThickness.toFloat()
                val brush = Brush.verticalGradient(chartStyle.chartBrush)

                drawRoundRect(
                    brush = brush,
                    topLeft = Offset(
                        x = (padding + i * xIncrement) - referenceBarThickness / 2, y = padding + yIncrement * (data.getHighestValue() - data
                            .getLowestValueAtIndex(i))
                    ),
                    size = Size(height = h, width = w),
                    cornerRadius = CornerRadius(x = chartStyle.barCornerRadius, y = chartStyle.barCornerRadius),
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawIntoCanvas {
                        drawText(
                            data.getLowestValueAtIndex(i).toString(),
                            (padding + i * xIncrement),
                            yLower + referenceBarThickness,
                            Paint().apply {
                                textSize = chartStyle.chartValueTextSize.toPx()
                                textAlign = Paint.Align.CENTER
                                color = chartStyle.chartValueTextColor.toArgb()
                                isFakeBoldText = true
                            }
                        )
                    }

                    drawIntoCanvas {
                        drawText(
                            data.getHighestValueAtIndex(i).toString(),
                            (padding + i * xIncrement),
                            yUpper - referenceBarThickness / 2,
                            Paint().apply {
                                textSize = chartStyle.chartValueTextSize.toPx()
                                textAlign = Paint.Align.CENTER
                                color = chartStyle.chartValueTextColor.toArgb()
                                isFakeBoldText = true
                            }
                        )
                    }
                }
            }
        }
    }
}





