package com.fred_projects.education.rest_api.math.model.entity

import androidx.room.*
import com.fred_projects.education.rest_api.math.model.service.MineMath

@Entity
data class MathEntity(
    val expression: String,
    val result: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){
    fun toMineMath() = MineMath(expression, result)
}