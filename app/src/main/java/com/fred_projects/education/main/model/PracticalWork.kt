package com.fred_projects.education.main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Practical_Works")
data class PracticalWork(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "Practical Work")
    val pwName: String,
    val student: String,
    val variantNumber: Int,
    val levelNumber: Int,
    val date: String,
    val mark: Int,
    val image: String
)