package org.welledge.project.util


import net.objecthunter.exp4j.ExpressionBuilder

object ExpressionEvaluator {
    fun evaluate(expression: String, x: Double, y: Double): Double {
        return try {
            val exp = ExpressionBuilder(expression)
                .variables("x", "y")
                .build()
                .setVariable("x", x)
                .setVariable("y", y)
            exp.evaluate()
        } catch (e: Exception) {
            0.0
        }
    }
}
