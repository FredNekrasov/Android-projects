package com.fred_projects.education.rest_api.anime.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes

@Entity
data class AnimeQuotesEntity(
    val anime: String,
    val character: String,
    val quote: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){
    fun toAnimeQuotes(): AnimeQuotes = AnimeQuotes(anime, character, quote)
}