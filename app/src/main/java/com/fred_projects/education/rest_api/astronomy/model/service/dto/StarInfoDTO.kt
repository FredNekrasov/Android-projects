package com.fred_projects.education.rest_api.astronomy.model.service.dto

import com.fred_projects.education.rest_api.astronomy.model.StarInfoEntity

data class StarInfoDTO(
    val host: List<HostDTO>,
    val references: List<String>,
){
    fun toStarInfoEntity(dec: String, ra: String, radius: Float, name: String): StarInfoEntity {
        return StarInfoEntity(
            dec,
            host.map { it.toHost() },
            name,
            references.toString(),
            ra,
            radius
        )
    }
}