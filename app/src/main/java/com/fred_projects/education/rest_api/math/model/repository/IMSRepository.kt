package com.fred_projects.education.rest_api.math.model.repository

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.math.model.service.MineMath
import kotlinx.coroutines.flow.StateFlow

interface IMSRepository {
    fun getResult(expression: String = "2^2+2"): StateFlow<Pair<Resource, MineMath?>>
}