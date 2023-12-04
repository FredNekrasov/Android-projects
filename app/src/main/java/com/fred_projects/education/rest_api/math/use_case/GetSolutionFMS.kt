package com.fred_projects.education.rest_api.math.use_case

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.math.model.repository.IMSRepository
import com.fred_projects.education.rest_api.math.model.service.MineMath
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetSolutionFMS(private val msRepository: IMSRepository) {
    operator fun invoke(expression: String): StateFlow<Pair<Resource, MineMath?>> {
        if (expression.isBlank() || expression.isEmpty()) return MutableStateFlow(Resource.ERROR to null)
        return msRepository.getResult(expression)
    }
}