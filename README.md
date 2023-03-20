# PlotWizard ![](https://jitpack.io/v/md0092651/PlotWizard.svg)

This is a simple chats library made with Jetpack compose


## Features

- [x] Line Chart
- [x] Pie Chart
- [x] Range Chart
- [x] CircularBar Chart

## Todo

- [ ] Column Chart
- [ ] Group Chart
- [ ] Gauge Chart
- [ ] Heap Map
- [ ] Pyramid Chart
- [ ] CandleStick Chart

## In Progress

- [ ] Area Chart
- [ ] Add XY blocks to Line and range chart

## Done

- [x] Line Chart
- [x] Pie Chart
- [x] CircularBar Chart


## Installation
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			
			maven { url 'https://jitpack.io' }
		}
	}
```
build.gradle (app)
```groovy
dependencies {
    implementation 'com.github.md0092651:PlotWizard:0.0.2'
}
```
## Usage

To implement basic Range chart
```kotlin
Chart(chartListData = getMockRangeList()) // Pass your list of type ChartEntry.RangeBar
``` 
To customize the animation and chart style :

```kotlin
 Chart(chartListData = getMockRangeList(),
    animationType = AnimationType.Bouncy(10F),
    chartStyle = ChartStyle.BarChartStyle(
        chartBrush = listOf(Pink40, Purple80),
        barCornerRadius = 20F,
        chartValueTextColor = Color.Black
    )
)
``` 


## Development

This app is developed using Kotlin and Jetpack Compose.

## Contributing

Contributions to this project are welcome. If you find a bug or would like to suggest a new feature,
please open an issue on this repository.

## Current Progress

<p align="center">
  <table>
    <tr>
      <td>
        <img src="screenshot/Step1.png" width=70% height=40%/>
        <br>
        <em>Screenshot</em>
      </td>
    <td>
<br>
       <img src="screenshot/video.gif" width=80% height=60%>
<br>
        <em>GIF</em>
      </td>
    </tr>
  </table>

## License

This project is licensed under the [MIT License](LICENSE).

