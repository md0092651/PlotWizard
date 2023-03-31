package com.charts.plotwizard.ui

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle

class PieChartPainter(val data: ChartData,val animationProgress: Float) : ChartPainter {

    val chartStyle = data.getChartStyle() as ChartStyle.PieChartStyle

    override fun drawPoint(drawScope: DrawScope) = drawScope.run {
        var startAngle = chartStyle.startAngle
        val total = data.list.sumOf { (it as ChartEntry.PieChartEntry).value }
        data.list.forEach { chartEntry ->
            val entryValue = (chartEntry as ChartEntry.PieChartEntry).value
            val sweepAngle = (360 * entryValue.toFloat() / total.toFloat()) * animationProgress
            drawArc(
                color = chartEntry.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = chartStyle.strokeWidth.toPx(),
                    cap = chartStyle.strokeCap
                )
            )
            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    val textPadding = 8.dp.toPx()
                    val arcHeight = size.height - (chartStyle.strokeWidth / 2).value
                    val arcWidth = size.width - (chartStyle.strokeWidth / 2).value
                    val path = Path().apply {
                        addArc(0f, textPadding, arcWidth, arcHeight, startAngle, sweepAngle)
                    }
                    drawTextOnPath(
                        chartEntry.label,
                        path,
                        0f,
                        0f,
                        Paint().apply {
                            textSize = chartStyle.textSize.toPx()
                            textAlign = Paint.Align.CENTER
                            color = chartStyle.textColor.toArgb()
                        }
                    )
                }
            }
            startAngle += sweepAngle
        }
    }
}