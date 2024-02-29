package com.fred_projects.education.rest_api.astronomy.model.service

import com.fred_projects.education.rest_api.astronomy.model.service.dto.StarInfoDTO
import retrofit2.http.*

interface IAstronomyService {
    @GET("/sne/catalog/")
    suspend fun getMoreInfo(
        @Query("ra") ra: String,
        @Query("dec") dec: String,
        @Query("radius") radius: Float
    ): Map<String, StarInfoDTO>?
    companion object {
        const val BASE_URL = "https://api.astrocats.space"
    }
}