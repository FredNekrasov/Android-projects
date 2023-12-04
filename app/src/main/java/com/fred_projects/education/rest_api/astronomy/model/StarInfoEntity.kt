package com.fred_projects.education.rest_api.astronomy.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fred_projects.education.rest_api.astronomy.model.service.Converters
import com.fred_projects.education.rest_api.astronomy.model.service.Host
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo

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
    fun toStarInfo(): StarInfo {
        return StarInfo(
            dec,
            host,
            name,
            references,
            ra,
            radius
        )
    }
}