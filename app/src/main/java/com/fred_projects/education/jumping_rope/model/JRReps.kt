package com.fred_projects.education.jumping_rope.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "JRReps")
data class JRReps(
    val count: Int,
    val date: String = LocalDate.now().toString(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)