package com.charts.plotwizard.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.charts.plotwizard.chartstyle.GridStyle

class GridLinePainter(val gridStyle: GridStyle) : ChartPainter{
    override fun drawPoint(drawScope: DrawScope) = drawScope.run {
        drawRect(gridStyle.axisLineColor, style = Stroke(gridStyle.strokeWidth.toPx()))

        val verticalLines = gridStyle.verticalLineCount
        val verticalSize = size.width / (verticalLines + 1)
        repeat(verticalLines) { i ->
            val startX = verticalSize * (i + 1)
            drawLine(
                gridStyle.axisLineColor,
                start = Offset(startX, 0f),
                end = Offset(startX, size.height),
                strokeWidth = gridStyle.strokeWidth.toPx()
            )
        }
        val horizontalLines = gridStyle.horizontalLineCount
        val sectionSize = size.height / (horizontalLines + 1)
        repeat(horizontalLines) { i ->
            val startY = sectionSize * (i + 1)
            drawLine(
                gridStyle.axisLineColor,
                start = Offset(0f, startY),
                end = Offset(size.width, startY),
                strokeWidth = gridStyle.strokeWidth.toPx()
            )
        }
    }
}