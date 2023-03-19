# PlotWizard ![](https://jitpack.io/v/md0092651/PlotWizard.svg)

This is a simple chats library made with Jetpack compose


## Features

- [x] Line Chart
- [x] Pie Chart
- [x] Range Chart

## Todo

- [ ] Spiral Chart
- [ ] Area Chart
- [ ] Column Chart
- [ ] Group Chart
- [ ] Gauge Chart

## In Progress

- [ ] Spiral Chart
- [ ] Add XY blocks to Line and range chart

## Done

- [x] Line Chart
- [x] Pie Chart
- [x] Range Chart


## Installation
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
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
        <img src="screenshot/Step1.png" alt="Compass screenshot" width="400"/>
        <br>
        <em>Step1</em>
      </td>
    </tr>
  </table>

https://user-images.githubusercontent.com/3413729/226190218-5e3a27ac-800a-434f-8687-b9ebe100b81c.mp4


## License

This project is licensed under the [MIT License](LICENSE).

