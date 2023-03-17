package com.charts.plotwizard.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

sealed class AnimationType(val animation: AnimationSpec<Float>){
    object None : AnimationType(tween(0))
    data class Linear(val duration : Int = 1000) : AnimationType(tween(duration))
    data class Bouncy(val dampingRatio :Float = 10F ): AnimationType(animation = spring(dampingRatio))
}



