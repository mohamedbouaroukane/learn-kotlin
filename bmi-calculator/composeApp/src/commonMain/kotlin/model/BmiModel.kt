package model

data class BmiModel(
    val gender: Gender = Gender.MALE,
    val heightCm: Double = 170.0,
    val weightKg: Double = 70.0,
    val bmiValue: Double = 0.0,
    val bmiCategory: BmiCategory = BmiCategory.NORMAL
)

enum class Gender {
    MALE, FEMALE
}

enum class BmiCategory {
    UNDERWEIGHT, NORMAL, OVERWEIGHT, OBESE;

    companion object {
        fun fromBmi(bmi: Double): BmiCategory = when {
            bmi < 18.5 -> UNDERWEIGHT
            bmi < 25.0 -> NORMAL
            bmi < 30.0 -> OVERWEIGHT
            else -> OBESE
        }
    }
}