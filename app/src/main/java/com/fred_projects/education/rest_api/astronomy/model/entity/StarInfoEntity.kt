package com.fred_projects.education.rest_api.astronomy.model.entity

import androidx.room.*
import com.fred_projects.education.rest_api.astronomy.model.service.*

@Entity
data class StarInfoEntity(
    val dec: String,
    val host: List<Host>,
    val name: String,
    val references: String,
    val ra: String,
    val radius: Float,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
){
    fun toStarInfo() = StarInfo(dec, host, name, references, ra, radius)
}