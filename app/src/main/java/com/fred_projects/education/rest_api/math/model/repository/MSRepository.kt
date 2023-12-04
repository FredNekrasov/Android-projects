package com.fred_projects.education.rest_api.math.model.repository

import com.fred_projects.database.dao.IMathDao
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.math.model.MathEntity
import com.fred_projects.education.rest_api.math.model.service.IMathService
import com.fred_projects.education.rest_api.math.model.service.MineMath
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MSRepository(private val api: IMathService, private val mathDao: IMathDao) : IMSRepository {
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun getResult(expression: String): StateFlow<Pair<Resource, MineMath?>> {
        val msf = MutableStateFlow<Pair<Resource, MineMath?>>(Resource.LOADING to null)
        scope.launch {
            val result = mathDao.getMathInfo(expression)
            msf.emit((Resource.LOADING to result?.toMineMath()))
            try {
                val res = api.getResult(expression).execute()
                if (res.isSuccessful) {
                    val body = res.body()
                    if (body == null) msf.emit((Resource.SOMETHING_WRONG to result?.toMineMath()))
                    else {
                        val entity = MathEntity(expression, body.result)
                        mathDao.deleteMathInfo(expression)
                        mathDao.insertOrUpdateRecord(entity)
                        msf.emit(Resource.SUCCESS to entity.toMineMath())
                    }
                } else msf.emit((Resource.SOMETHING_WRONG to result?.toMineMath()))
            } catch (e: Exception) {
                msf.emit((Resource.SOMETHING_WRONG to result?.toMineMath()))
            } catch (e: HttpException) {
                msf.emit((Resource.NO_INTERNET to result?.toMineMath()))
            } catch (e: IOException) {
                msf.emit((Resource.SOMETHING_WRONG to result?.toMineMath()))
            }
        }
        return msf
    }
}