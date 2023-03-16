package com.charts.sample.plotwizard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.charts.plotwizard.component.RangeBarGraph
import com.charts.plotwizard.model.generateRandomTemperatureList
import com.charts.sample.plotwizard.ui.theme.PlotWizardTheme
import com.weather.temeprature.bar.model.ChartDataSet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlotWizardTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    RangeBarGraph(data = ChartDataSet( generateRandomTemperatureList()))
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