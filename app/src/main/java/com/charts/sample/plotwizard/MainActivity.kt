package com.charts.sample.plotwizard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.charts.plotwizard.animation.AnimationType
import com.charts.plotwizard.getMockRangeList
import com.charts.plotwizard.chartstyle.ChartStyle
import com.charts.plotwizard.getMockLineList
import com.charts.plotwizard.getMockPieList
import com.charts.plotwizard.ui.Chart
import com.charts.sample.plotwizard.ui.theme.Pink40
import com.charts.sample.plotwizard.ui.theme.Pink80
import com.charts.sample.plotwizard.ui.theme.PlotWizardTheme
import com.charts.sample.plotwizard.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlotWizardTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column() {
                        Column(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                            Chart(
                                chartListData = getMockRangeList(),
                                animationType = AnimationType.Bouncy(10F),
                                chartStyle = ChartStyle.BarChartStyle(
                                    chartBrush = listOf(Pink40, Purple80, Pink40),
                                    barCornerRadius = 20F,
                                    chartValueTextColor = Color.Black
                                )
                            )
                        }
                        Column(modifier = Modifier.fillMaxWidth().height(240.dp).background(color = Color.LightGray)) {
                            Chart(
                                chartListData = getMockPieList(),
                                animationType = AnimationType.Linear(2000)
                            )
                        }

                        Column(modifier = Modifier.fillMaxWidth().height(240.dp).background(color = Color.LightGray.copy(alpha = 0.2F))) {
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlotWizardTheme {
        Greeting("Android")
    }
}