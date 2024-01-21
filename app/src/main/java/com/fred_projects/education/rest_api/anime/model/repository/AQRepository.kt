package com.fred_projects.education.rest_api.anime.model.repository

import com.fred_projects.database.dao.IAQDao
import com.fred_projects.education.rest_api.Resource
import com.fred_projects.education.rest_api.anime.model.service.AnimeQuotes
import com.fred_projects.education.rest_api.anime.model.service.IAQService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AQRepository(
    private val api: IAQService,
    private val dao: IAQDao
) : IAQRepository {
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun getAQ(anime: String): StateFlow<Pair<Resource, List<AnimeQuotes>?>> {
        val msf = MutableStateFlow<Pair<Resource, List<AnimeQuotes>?>>(Resource.LOADING to null)
        scope.launch {
            val result = dao.getListAQ(anime)
            msf.emit((Resource.LOADING to result.map { it.toAnimeQuotes() }))
            try {
                val res = api.getQuotes(anime).execute()
                if (res.isSuccessful) {
                    val body = res.body()
                    if (body == null) msf.emit((Resource.SOMETHING_WRONG to result.map { it.toAnimeQuotes() }))
                    else {
                        dao.deleteAQ(anime)
                        body.forEach {
                            dao.insertAQ(it.toAnimeQuotesEntity())
                        }
                        val quotes = body.map { it.toAnimeQuotesEntity() }
                        msf.emit((Resource.SUCCESS to quotes.map { it.toAnimeQuotes() }))
                    }
                } else msf.emit((Resource.SOMETHING_WRONG to result.map { it.toAnimeQuotes() }))
            } catch (e: Exception) {
                msf.emit((Resource.SOMETHING_WRONG to result.map { it.toAnimeQuotes() }))
            } catch (e: HttpException) {
                msf.emit((Resource.NO_INTERNET to result.map { it.toAnimeQuotes() }))
            } catch (e: IOException) {
                msf.emit((Resource.SOMETHING_WRONG to result.map { it.toAnimeQuotes() }))
            }
        }
        return msf
    }
}