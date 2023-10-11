package com.charts.plotwizard.ui

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.unit.dp

class SingleHorizontalRangeBar(
    val sunRiseHours: Int = 8,
    val sunSetHours: Int = 16,
    val currentHours: Float = 2f,
    val painter: VectorPainter,
) : ChartPainter {

    override fun drawPoint(drawScope: DrawScope): Unit = drawScope.run {
        val canvasWidth = size.width
        val brush = Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.2f), Color.Black.copy(alpha = 0.2f)))
        val dayBrush = Brush.verticalGradient(listOf(Color.Yellow.copy(alpha = 0.8f), Color.Yellow.copy(alpha = 0.8f)))

        val startOffSet = (sunRiseHours.toFloat() / 24) * canvasWidth
        val dayWidth = ((sunSetHours - sunRiseHours).toFloat() / 24) * canvasWidth
        val sunOffSet = ((currentHours) / 24) * canvasWidth

        drawRoundRect(
            brush = brush,
            topLeft = Offset(
                x = 0F,
                y = 0F,
            ),
            size = Size(height = 50f, width = canvasWidth),
            cornerRadius = CornerRadius(x = 30f, y = 30f),
        )

        drawRoundRect(
            brush = dayBrush,
            topLeft = Offset(
                x = startOffSet,
                y = 0f,
            ),
            size = Size(height = 50f, width = dayWidth),
            cornerRadius = CornerRadius(x = 30f, y = 30f),
        )

        drawContext.canvas.nativeCanvas.apply {
            drawIntoCanvas {
                translate(sunOffSet, 0f) {
                    with(painter) {
                        draw(
                            size = Size(20.dp.toPx(), 20.dp.toPx()),
                            alpha = 1f,
                        )
                    }
                }
            }
        }
    }
}
