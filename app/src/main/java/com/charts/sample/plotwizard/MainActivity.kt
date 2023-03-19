package com.charts.sample.plotwizard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.getMockLineList
import com.charts.plotwizard.getMockPieList
import com.charts.plotwizard.getMockRangeList
import com.charts.plotwizard.getMockSpiralChartList
import com.charts.plotwizard.ui.Chart
import com.charts.plotwizard.ui.theme.C4
import com.charts.sample.plotwizard.ui.theme.C2
import com.charts.sample.plotwizard.ui.theme.PlotWizardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlotWizardTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column() {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Chart(
                                chartListData = getMockRangeList(),
                                animationType = AnimationType.Bouncy(10F),
                                chartStyle = ChartStyle.BarChartStyle(
                                    chartBrush = listOf(C2, C4),
                                    barCornerRadius = 20F,
                                    chartValueTextColor = Color.Black
                                )
                            )
                        }
                        Row() {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp)
                                    .weight(1F)
                            ) {
                                Chart(
                                    chartListData = getMockSpiralChartList(),
                                    chartStyle = ChartStyle.PieChartStyle(chartSize = 150.dp, strokeWidth = 20.dp),
                                    animationType = AnimationType.Linear(2000)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp)
                                    .weight(1F)
                            ) {
                                Chart(
                                    chartListData = getMockPieList(),
                                    chartStyle = ChartStyle.PieChartStyle(chartSize = 150.dp, strokeWidth = 20.dp),
                                    animationType = AnimationType.Linear(2000)
                                )
                            }
                        }


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)
                                .height(240.dp)
                        ) {
                            Chart(
                                chartListData = getMockLineList(),
                                animationType = AnimationType.Bouncy(20F)
                            )
                        }
                    }
                }
            }
        }
    }
}