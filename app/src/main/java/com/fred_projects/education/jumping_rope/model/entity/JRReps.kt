package com.fred_projects.education.jumping_rope.model.entity

import androidx.room.*
import java.time.LocalDate

@Entity(tableName = "JRReps")
data class JRReps(
    val count: Int,
    val date: String = LocalDate.now().toString(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)