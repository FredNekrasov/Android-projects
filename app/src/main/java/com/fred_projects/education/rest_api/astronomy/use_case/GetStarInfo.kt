package com.fred_projects.education.rest_api.astronomy.use_case

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.model.repository.IStarInfoRepository
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import kotlinx.coroutines.flow.*

class GetStarInfo(private val repository: IStarInfoRepository) {
    operator fun invoke(ra: String, dec: String, radius: Float?): StateFlow<Pair<Resource, List<StarInfo>?>> {
        if (ra.isBlank()) return MutableStateFlow((Resource.ERROR to null))
        if (dec.isBlank()) return MutableStateFlow((Resource.ERROR to null))
        if (radius == null) return MutableStateFlow((Resource.ERROR to null))
        return repository.getStarInfo(ra, dec, radius)
    }
}