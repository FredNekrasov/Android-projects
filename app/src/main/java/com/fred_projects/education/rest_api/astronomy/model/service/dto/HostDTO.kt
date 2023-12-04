package com.fred_projects.education.rest_api.astronomy.model.service.dto

import com.fred_projects.education.rest_api.astronomy.model.service.Host

data class HostDTO(val value: String){
    fun toHost(): Host {
        return Host(value)
    }
}