package com.charts.plotwizard.animation

import androidx.compose.animation.core.*

sealed class AnimationType(val animation: AnimationSpec<Float>){

    abstract val animationInitialProgress:Animatable<Float,AnimationVector1D>

    object None : AnimationType(tween(0)) {
        override val animationInitialProgress: Animatable<Float, AnimationVector1D>
            get() = Animatable(1f)
    }

    data class Linear(val duration : Int = 1000) : AnimationType(tween(duration)) {
        override val animationInitialProgress: Animatable<Float, AnimationVector1D>
            get() = Animatable(0f)
    }

    data class Bouncy(val dampingRatio :Float = 10F ): AnimationType(animation = spring(dampingRatio)) {
        override val animationInitialProgress: Animatable<Float, AnimationVector1D>
            get() = Animatable(0f)
    }
}



