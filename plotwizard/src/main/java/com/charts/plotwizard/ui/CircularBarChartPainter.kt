package com.charts.plotwizard.ui

import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle

class CircularBarChartPainter(val data: ChartData,val animationProgress: Float) : ChartPainter{
    val chartStyle = data.getChartStyle() as ChartStyle.PieChartStyle

    override fun drawPoint(drawScope: DrawScope) = drawScope.run {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val centerX = canvasWidth / 2
        val centerY = canvasHeight / 2
        val radiusIncrement = 70
        var currentRadius = radiusIncrement.toFloat()
        var currentAngle = chartStyle.startAngle


        val total = data.getHighestValue()

        data.list.forEach { chartEntry ->
            val entryValue = (chartEntry as ChartEntry.CircularBarChartEntry).value
            val sweepAngle = (270 * entryValue / total) * animationProgress
            val arcWidth = radiusIncrement / 1.2
            drawArc(
                color = Color.LightGray.copy(alpha = 0.2F),
                startAngle = currentAngle,
                sweepAngle = 270F,
                useCenter = false,
                topLeft = Offset(centerX - currentRadius, centerY - currentRadius),
                size = Size(currentRadius * 2, currentRadius * 2),
                style = Stroke(width = arcWidth.toFloat()),
                alpha = 1.0f,
            )

            drawArc(
                color = chartEntry.valueColor,
                startAngle = currentAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(centerX - currentRadius, centerY - currentRadius),
                size = Size(currentRadius * 2, currentRadius * 2),
                style = Stroke(width = arcWidth.toFloat()),
                alpha = 1.0f,
            )


            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        chartEntry.valueLabel,
                        centerX - 10 ,
                        (centerY - currentRadius + arcWidth/4).toFloat(), // - currentRadius / 2,
                        Paint().apply {
                            textSize = 10.dp.toPx()//chartStyle.chartValueTextSize.toPx()
                            textAlign = Paint.Align.RIGHT
                            color = android.graphics.Color.BLACK//chartStyle.chartValueTextColor.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
            currentRadius += radiusIncrement
        }
    }

}
