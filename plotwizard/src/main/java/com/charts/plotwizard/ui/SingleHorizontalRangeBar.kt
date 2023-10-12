package com.charts.plotwizard.ui

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import com.charts.plotwizard.chartdata.ChartData
import com.charts.plotwizard.chartdata.ChartEntry
import com.charts.plotwizard.chartstyle.ChartStyle

class SingleHorizontalRangeBar(
    val data: ChartData,
    val animationProgress: Float,
) : ChartPainter {

    private val chartStyle = data.getChartStyle() as ChartStyle.RiseSetStyle

    override fun drawPoint(drawScope: DrawScope): Unit = drawScope.run {
        val entryValue = (data.list.first() as ChartEntry.RiseSetBarChartEntry)
        val canvasWidth = size.width

        val startOffSet = (entryValue.riseHours / 24) * canvasWidth
        val dayWidth = ((entryValue.setHours - entryValue.riseHours) / 24) * canvasWidth
        val sunOffSet = (((entryValue.currentHours) / 24) * canvasWidth) * animationProgress

        val isCurrentTimeDayLight = entryValue.currentHours in entryValue.riseHours..entryValue.setHours

        drawRoundRect(
            brush = chartStyle.nightBrush,
            topLeft = Offset(
                x = 0F,
                y = -chartStyle.barHeight.toPx() / 4,
            ),
            size = Size(height = chartStyle.barHeight.toPx(), width = canvasWidth),
            cornerRadius = CornerRadius(x = 30f, y = 30f),
        )

        drawRoundRect(
            brush = chartStyle.dayBrush,
            topLeft = Offset(
                x = startOffSet,
                y = -chartStyle.barHeight.toPx() / 4,
            ),
            size = Size(height = chartStyle.barHeight.toPx(), width = dayWidth),
            cornerRadius = CornerRadius(x = 30f, y = 30f),
        )

        drawContext.canvas.nativeCanvas.apply {
            drawIntoCanvas {
                translate(sunOffSet, -chartStyle.barHeight.toPx() / 4) {
                    with(entryValue.painter) {
                        draw(
                            size = Size(chartStyle.iconSize.toPx(), chartStyle.iconSize.toPx()),
                            alpha = 1f,
                            colorFilter = if (isCurrentTimeDayLight) {
                                ColorFilter.tint(chartStyle.dayLightIconTint)
                            } else {
                                ColorFilter.tint(chartStyle.nightIconTint)
                            },
                        )
                    }
                }
            }
        }
    }
}
