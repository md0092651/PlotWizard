package com.charts.plotwizard.component

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.ui.theme.Purple40
import com.charts.plotwizard.ui.theme.Purple80
import com.weather.temeprature.bar.model.ChartDataSet
import kotlin.math.abs

@Composable
fun RangeBarGraph(
    data: ChartDataSet,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val animateProgress = remember { Animatable(0f) }

        LaunchedEffect(key1 = data, block = {
            animateProgress.animateTo(1F, tween(3000))
        })

        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val padding = 100F

            val xIncrement = (canvasWidth - 2 * padding) / (data.numberOfBars-1)
            val yIncrement = ((canvasHeight - 2 * padding) / (abs(data.getMinimumValue()) + abs(data.getHighestValue())))*animateProgress.value

            val referenceBarThickness = 50

            for (i in 0 until data.numberOfBars) {

                val yUpper = padding + yIncrement * (data.getHighestValue() - data.getHighestValueAtIndex(i))
                val yLower = padding + yIncrement * (data.getHighestValue() - data.getLowestValueAtIndex(i))


                val h = (yUpper - yLower)*animateProgress.value
                val w = referenceBarThickness.toFloat()
                val brush = Brush.verticalGradient(
                    listOf(
                        Purple80,
                        Purple40
                    )
                )


                drawRoundRect(
                    brush = brush,
                    //color = Color.Red,
                    topLeft = Offset(
                        x = (padding + i * xIncrement) - referenceBarThickness / 2, y = padding + yIncrement * (data.getHighestValue() - data
                            .getLowestValueAtIndex(i))
                    ),
                    size = Size(height = h, width = w),
                    cornerRadius = CornerRadius(x = 25f, y = 25f),
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawIntoCanvas {
                        drawText(
                            data.getLowestValueAtIndex(i).toString(),
                            (padding + i * xIncrement) ,
                            yLower + referenceBarThickness/2,
                           Paint().apply {
                                textSize = 10.dp.toPx()
                                textAlign = Paint.Align.CENTER
                                color = Purple80.toArgb()
                                isFakeBoldText = true
                            }
                        )
                    }

                    drawIntoCanvas {
                        drawText(
                             data.getLowestValueAtIndex(i).toString(),
                             (padding + i * xIncrement) ,
                           yUpper - referenceBarThickness/2,
                            Paint().apply {
                                textSize = 10.dp.toPx()
                                textAlign = Paint.Align.CENTER
                                color = Purple80.toArgb()
                                isFakeBoldText = true
                            }
                        )
                    }
                }
            }
        }
    }
}