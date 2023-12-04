package com.fred_projects.education.rest_api.astronomy.model.repository

import com.fred_projects.database.dao.IAstronomyDao
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.model.service.IAstronomyService
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class StarInfoRepository(
    private val api: IAstronomyService,
    private val dao: IAstronomyDao
) : IStarInfoRepository {
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun getStarInfo(ra: String, dec: String, radius: Float): StateFlow<Pair<Resource, List<StarInfo>?>> {
        val msf = MutableStateFlow<Pair<Resource, List<StarInfo>?>>(Resource.LOADING to null)
        scope.launch {
            val starListInfo = dao.getStarListInfo(ra, dec, radius).map { it.toStarInfo() }
            msf.emit((Resource.LOADING to starListInfo))
            try {
                val remoteStarListInfo = api.getMoreInfo(ra, dec, radius) ?: return@launch msf.emit((Resource.SOMETHING_WRONG to starListInfo))
                dao.deleteStarInfo(remoteStarListInfo.map { it.key })
                dao.insertStarInfo(remoteStarListInfo.map { it.value.toStarInfoEntity(dec, ra, radius, it.key) })
            } catch (e: HttpException) {
                return@launch msf.emit((Resource.NO_INTERNET to starListInfo))
            } catch (e: IOException) {
                return@launch msf.emit((Resource.SOMETHING_WRONG to starListInfo))
            } catch (e: Exception) {
                return@launch msf.emit((Resource.SOMETHING_WRONG to starListInfo))
            } catch (e: JsonSyntaxException) {
                return@launch msf.emit((Resource.SOMETHING_WRONG to starListInfo))
            }
            val newStarListInfo = dao.getStarListInfo(ra, dec, radius).map { it.toStarInfo() }
            msf.emit((Resource.SUCCESS to newStarListInfo))
        }
        return msf
    }
}