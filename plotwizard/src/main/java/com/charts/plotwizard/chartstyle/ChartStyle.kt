package com.charts.plotwizard.chartstyle

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.charts.plotwizard.ui.theme.Purple40
import com.charts.plotwizard.ui.theme.Purple80

sealed class ChartStyle{

    data class BarChartStyle(
        val barThickness :Int = 50,
        val padding : Float = 100F,
        val barCornerRadius:Float = 25f,
        val chartValueTextSize: Dp = 10.dp,
        val chartValueTextColor: Color = Purple80,
        val chartBrush: List<Color> = listOf(Purple80, Purple40),
        val generateRandomColor:Boolean = false,

    ): ChartStyle()

    data class PieChartStyle(
        val chartSize:Dp = 200.dp,
        val startAngle:Float = 270F,
        val strokeWidth:Dp = 30.dp,
        val strokeCap: StrokeCap = StrokeCap.Round,
        val textSize: TextUnit = 16.sp,
        val textColor: Color = Color.White
    ):ChartStyle()

    data class LineChartStyle(
        val chartSize:Dp = 100.dp,
        val startAngle:Float = 0F,
        val strokeWidth:Dp = 30.dp,
        val strokeCap: StrokeCap = StrokeCap.Round,
        val textSize: TextUnit = 16.sp,
        val hideGridLine:Boolean = false,
        val gridStyle: GridStyle = GridStyle()
    ):ChartStyle()

    data class DefaultStyle(
        val barThickness :Int = 50,
        val padding : Float = 100F,
        val barCornerRadius:Float = 25f,
        val xAxisLineColor: Color = Color.Black,
        val yAxisLineColor: Color = Color.Black,
        val xAxisLabelColor: Color = Color.Black,
        val yAxisLabelColor: Color = Color.Black,
        val xLabel :String = "",
        val yLabel :String = "",
        val chartValueTextSize: Dp = 10.dp,
        val chartValueTextColor: Color = Purple80,
        val chartBrush: List<Color> = listOf(Purple80, Purple40),
        val generateRandomColor:Boolean = false,
        val hideXYLine:Boolean = false
    ) : ChartStyle()
}



data class GridStyle(
    val axisLineColor: Color = Color.Black.copy(alpha = 0.1f),
    val axisLabelColor: Color = Color.Black.copy(alpha = 0.1f),
    val xLabel :String = "",
    val yLabel :String = "",
    val strokeWidth:Dp = 2.dp,
    val verticalLineCount:Int= 8,
    val horizontalLineCount:Int= 8,
)

