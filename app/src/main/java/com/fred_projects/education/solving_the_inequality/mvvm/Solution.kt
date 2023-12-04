package com.fred_projects.education.solving_the_inequality.mvvm

enum class Solution {
    ERROR {
        override fun getSolution(number: Float?): String {
            return "1"
        }
    },
    INFORMATION {
        override fun getSolution(number: Float?): String {
            return "2"
        }
    },
    FIRST_SOLUTION {
        override fun getSolution(number: Float?): String {
            return "$number < 0\nx = (− ∞ , + ∞)"
        }
    },
    SECOND_SOLUTION {
        override fun getSolution(number: Float?): String {
            return "x > $number\nx = (− ∞ , $number)"
        }
    },
    THIRD_SOLUTION {
        override fun getSolution(number: Float?): String {
            return if (number == -0.0f) "x < 0\nx = (− ∞ , 0)"
            else "x < $number\nx = (− ∞ , $number)"
        }
    };
    abstract fun getSolution(number: Float?): String
}