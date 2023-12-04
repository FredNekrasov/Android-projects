package com.fred_projects.education.rest_api.math.model.service

import com.fred_projects.education.rest_api.math.model.service.dto.MathDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMathService {
    @GET("/api/v2/simplify/{expression}")
    fun getResult(@Path("expression") expression: String): Call<MathDTO>
    companion object {
        const val BASE_URL = "https://newton.now.sh/"
    }
}