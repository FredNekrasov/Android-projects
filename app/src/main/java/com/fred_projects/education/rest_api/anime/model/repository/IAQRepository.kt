package com.fred_projects.education.rest_api.anime.model.repository

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes
import kotlinx.coroutines.flow.StateFlow

interface IAQRepository {
    fun getAQ(anime: String = "hajime no ippo"): StateFlow<Pair<Resource, List<AnimeQuotes>?>>
}