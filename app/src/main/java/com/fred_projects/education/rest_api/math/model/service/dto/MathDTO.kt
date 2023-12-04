package com.fred_projects.education.rest_api.math.model.service.dto

import com.fred_projects.education.rest_api.math.model.MathEntity
import com.fred_projects.education.rest_api.math.model.service.MineMath

data class MathDTO(
    val expression: String,
    val result: String
){
    fun toMathEntity() = MathEntity(expression, result)
    fun toMineMath() = MineMath(expression, result)
}