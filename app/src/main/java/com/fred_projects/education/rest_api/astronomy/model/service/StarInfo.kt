package com.fred_projects.education.rest_api.astronomy.model.service

data class StarInfo(
    val dec: String,
    val host: List<Host>,
    val name: String,
    val references: String,
    val ra: String,
    val radius: Float
)