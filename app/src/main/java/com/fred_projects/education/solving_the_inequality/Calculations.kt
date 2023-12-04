package com.fred_projects.education.solving_the_inequality

//Разработайте программу, работающую под управлением Android на Jetpack Compose. Проверьте, что программа корректно работает с различными размерами экрана, а также при повороте экрана.
//Программа решения неравенства вида ax + b < 0
class Calculations {
    fun checkData(first: Float?, second: Float?, error: String, advice: String): String {
        return when{
            second == null || first == null -> error
            second == 0f && first == 0f -> advice
            first == 0f && second > 0f -> advice
            first == 0f && second < 0f -> "$second < 0\nx = (− ∞ , + ∞)"
            first < 0 -> "x > ${-second/first}\nx = (${-second/first}, + ∞)"
            else -> "x < ${-second/first}\nx = (− ∞ , ${-second/first})"
        }
    }
    companion object{
        const val RESULT = "Result"
        const val FIRST_NUMBER = "first number"
        const val SECOND_NUMBER = "second number"
        const val FORMULA = "formula"
    }
}