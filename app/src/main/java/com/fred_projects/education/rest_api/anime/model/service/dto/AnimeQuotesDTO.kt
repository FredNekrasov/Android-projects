package com.fred_projects.education.rest_api.anime.model.service.dto

import com.fred_projects.education.rest_api.anime.model.AnimeQuotesEntity

data class AnimeQuotesDTO(
    val anime: String,
    val character: String,
    val quote: String
){
    fun toAnimeQuotesEntity(): AnimeQuotesEntity = AnimeQuotesEntity(anime, character, quote)
}