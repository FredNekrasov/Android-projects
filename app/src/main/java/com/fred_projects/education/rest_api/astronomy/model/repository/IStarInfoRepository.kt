package com.fred_projects.education.rest_api.astronomy.model.repository

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import kotlinx.coroutines.flow.StateFlow

interface IStarInfoRepository {
    fun getStarInfo(ra: String, dec: String, radius: Float): StateFlow<Pair<Resource, List<StarInfo>?>>
}