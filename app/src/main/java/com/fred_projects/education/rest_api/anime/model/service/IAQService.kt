package com.fred_projects.education.rest_api.anime.model.service

import com.fred_projects.education.rest_api.anime.model.service.dto.AnimeQuotesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IAQService {
    @GET("/api/quotes/anime/")
    fun getQuotes(@Query("title") title: String): Call<List<AnimeQuotesDTO>>
    companion object {
        const val BASE_URL = "https://animechan.xyz"
    }
}