package org.welledge.project.domain.model


data class Equation(
    val equation: String = "x*y",
    val xRange: ClosedFloatingPointRange<Double> = -10.0..10.0,
    val yRange: ClosedFloatingPointRange<Double> = -10.0..10.0,
    val steps: Int = 80
)