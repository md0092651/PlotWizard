package com.charts.plotwizard.ui

import android.graphics.PointF
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.ui.theme.Pink80

class LineChartPainter(val data: ChartData, val animationProgress: Float): ChartPainter{
    val style = data.getChartStyle() as ChartStyle.LineChartStyle

    override fun drawPoint(drawScope: DrawScope) = drawScope.run {
        val filledPath = generateSmoothPath(data, size)

        filledPath.relativeLineTo(0f, size.height)
        filledPath.lineTo(0f, size.height)
        filledPath.close()

        if(!style.hideGridLine){
            GridLinePainter(style.gridStyle).drawPoint(this)
        }

        clipRect(right = size.width * animationProgress) {
            drawPath(generateSmoothPath(data, size), Pink80, style = Stroke(2.dp.toPx()))
            drawPath(
                filledPath,
                brush = Brush.verticalGradient(
                    listOf(
                        Pink80.copy(alpha = 0.4f),
                        Color.Transparent
                    )
                ),
                style = Fill
            )
        }
    }

}

fun generateSmoothPath(data: ChartData, size: Size): Path {
    val path = Path()
    val numberEntries = data.numberOfBars - 1
    val xWidth = size.width / numberEntries

    val range = data.getHighestValue() - data.getMinimumValue()
    val heightPxPerAmount = size.height / range

    var lastXValue = 0f
    var lastYValue = size.height
    data.list.forEachIndexed { i, entry ->
        if (i == 0) {
            path.moveTo(
                0f,
                size.height - ((entry as ChartEntry.LineChartEntry).yValue - data.getMinimumValue()) * heightPxPerAmount
            )
        }
        val xIncrement = i * xWidth
        val yIncrement = size.height - ((entry as ChartEntry.LineChartEntry).yValue - data.getMinimumValue()) *
                heightPxPerAmount
        val firstPoint = PointF((xIncrement + lastXValue) / 2f, lastYValue)
        val secondPoint = PointF((xIncrement + lastXValue) / 2f, yIncrement)
        path.cubicTo(
            firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y,
            xIncrement, yIncrement
        )
        lastXValue = xIncrement
        lastYValue = yIncrement
    }
    return path
}