package com.charts.plotwizard.ui

import androidx.compose.ui.graphics.drawscope.DrawScope

interface ChartPainter {
    fun drawPoint(
        drawScope: DrawScope,
    )
}
