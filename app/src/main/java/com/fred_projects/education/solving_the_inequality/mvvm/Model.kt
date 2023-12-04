package com.fred_projects.education.solving_the_inequality.mvvm

//Осуществите рефакторинг проекта, полученного в предыдущей практической работе, путём внедрения паттерна MVVM.
//Обратите внимание на то, что для реализации прослушивания изменений значений во ViewModel (и в Model при необходимости) необходимо использовать Flow.
class Model {
    fun checkData(first: Float?, second: Float?): Pair<Solution, Float?> {
        return when {
            second == null || first == null -> (Solution.ERROR to null)
            second == 0f && first == 0f -> (Solution.INFORMATION to null)
            first == 0f && second > 0f -> (Solution.INFORMATION to null)
            first == 0f && second < 0f -> (Solution.FIRST_SOLUTION to second)
            first < 0 -> (Solution.SECOND_SOLUTION to (-second/first))
            else -> (Solution.THIRD_SOLUTION to (-second/first))
        }
    }
}