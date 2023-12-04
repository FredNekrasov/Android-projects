package com.fred_projects.test_astronomy_service

import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.astronomy.model.StarInfoEntity
import com.fred_projects.education.rest_api.astronomy.model.repository.IStarInfoRepository
import com.fred_projects.education.rest_api.astronomy.model.service.Host
import com.fred_projects.education.rest_api.astronomy.model.service.StarInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import retrofit2.HttpException
import java.io.IOException

class FSIRepository : IStarInfoRepository {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val list = mutableListOf<StarInfoEntity>()
    override fun getStarInfo(
        ra: String,
        dec: String,
        radius: Float,
    ): StateFlow<Pair<Resource, List<StarInfo>?>> {
        val msf = MutableStateFlow<Pair<Resource, List<StarInfo>?>>(Resource.LOADING to null)
        scope.launch {
            val starListInfo = list.filter { it.ra == ra && it.dec == dec && it.radius == radius }.map { it.toStarInfo() }
            msf.emit((Resource.LOADING to starListInfo))
            try {
                val remoteStarListInfo = listOf(StarInfoEntity(dec, listOf(Host("Milky Way")), "qwerty", "49", ra, radius))
                list.removeIf{ it.name in remoteStarListInfo.map { i-> i.name } }
                list.addAll(remoteStarListInfo)
            } catch (e: HttpException){
                msf.emit(getRes(starListInfo))
            } catch (e: IOException) {
                msf.emit(getRes(starListInfo))
            }
            val newStarListInfo = list.filter { it.ra == ra && it.dec == dec && it.radius == radius }.map { it.toStarInfo() }
            msf.emit((Resource.SUCCESS to newStarListInfo))
            yield()
        }
        return msf
    }
    private fun getRes(value: List<StarInfo>?) = if (value != null) (Resource.SUCCESS to value) else (Resource.ERROR to null)
}